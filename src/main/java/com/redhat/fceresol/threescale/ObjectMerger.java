/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale;

import com.redhat.fceresol.config.MigratorConfig;
import com.redhat.fceresol.exceptions.LoadException;
import com.redhat.fceresol.threescale.api.accounts.Account;
import com.redhat.fceresol.threescale.api.accounts.AccountWrapper;
import com.redhat.fceresol.threescale.api.accounts.User;
import com.redhat.fceresol.threescale.api.backends.Backend;
import com.redhat.fceresol.threescale.api.backends.BackendWrapper;
import com.redhat.fceresol.threescale.api.clients.DestThreescaleClient;
import com.redhat.fceresol.threescale.api.clients.SourceThreescaleClient;
import com.redhat.fceresol.threescale.api.services.Service;
import com.redhat.fceresol.threescale.api.services.ServiceBackendUsage;
import com.redhat.fceresol.threescale.api.services.ServiceBackendUsageWrapper;
import com.redhat.fceresol.threescale.api.services.ServiceMetric;
import com.redhat.fceresol.threescale.api.services.ServiceMetricWrapper;
import com.redhat.fceresol.threescale.api.services.ServiceMetrics;
import com.redhat.fceresol.threescale.api.services.ServiceWrapper;
import com.redhat.fceresol.threescale.api.services.applications.Application;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlan;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlanWrapper;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.Proxy;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyMappingRule;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyMappingRuleWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyMappingRules;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyOIDCConfig;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyOIDCConfigWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyWrapper;
import io.quarkus.logging.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.jboss.resteasy.reactive.RestResponse;

/**
 *
 * @author fceresol
 */
public class ObjectMerger {

    private Map<Integer, Integer> serviceMap;
    private Map<Integer, Integer> backendMap;
    private Map<Integer, Integer> planMap;
    private Map<Integer, Integer> accountMap;
    private SourceThreescaleClient sourceCLient;
    private DestThreescaleClient destCLient;
    private Map<Integer, String> csvMapping;
    private MigratorConfig config;
    private ConfigLoader sourceConfigLoader;
    private ConfigLoader destConfigLoader;

    public ObjectMerger() {
        this.serviceMap = new HashMap<>();
        this.backendMap = new HashMap<>();
        this.accountMap = new HashMap<>();
        this.planMap = new HashMap<>();
        this.sourceCLient = null;
        this.destCLient = null;
        this.csvMapping = null;
    }

    public ObjectMerger(SourceThreescaleClient sourceCLient, DestThreescaleClient destClient, Map<Integer, String> csvMapping, MigratorConfig config) {
        this();
        this.sourceCLient = sourceCLient;
        this.destCLient = destClient;
        this.csvMapping = csvMapping;
        this.config = config;
    }

    public void loadAll() throws LoadException {
        this.sourceConfigLoader = new ConfigLoader(sourceCLient, config, config.exportToken());
        this.destConfigLoader = new ConfigLoader(destCLient, config, config.importToken());

        Log.info("loading source configurations....");
        this.sourceConfigLoader.loadConfigurations(csvMapping.keySet().stream().collect(Collectors.toCollection(ArrayList::new)));

        Log.info("loading dest configurations...");

        this.destConfigLoader.loadConfigurations();

        this.mapOldToNew();

    }

    private void mapOldToNew() {
        Map<Integer, Backend> sourceBackends = this.sourceConfigLoader.getBackends();
        Map<Integer, Backend> destBackends = this.destConfigLoader.getBackends();

        for (Integer sourceID : sourceBackends.keySet()) {
            this.backendMap.put(sourceID, null);
            for (Integer destID : destBackends.keySet()) {
                if (destBackends.get(destID).getSystem_name().equals(sourceBackends.get(sourceID).getSystem_name())) {
                    this.backendMap.put(sourceID, destID);
                    break;
                }
            }
        }

        Map<Integer, Service> sourceServices = this.sourceConfigLoader.getServices();
        Map<Integer, Service> destServices = this.destConfigLoader.getServices();

        for (Integer sourceID : sourceServices.keySet()) {
            this.serviceMap.put(sourceID, null);
            for (Integer destID : destServices.keySet()) {
                if (destServices.get(destID).getSystem_name().equals(sourceServices.get(sourceID).getSystem_name())) {
                    this.serviceMap.put(sourceID, destID);
                    break;
                }
            }
        }

        Map<Integer, Account> sourceAccounts = this.sourceConfigLoader.getAccounts();
        Map<Integer, Account> destAccounts = this.destConfigLoader.getAccounts();

        for (Integer sourceID : sourceAccounts.keySet()) {
            this.accountMap.put(sourceID, null);
            for (Integer destID : destAccounts.keySet()) {
                if (destAccounts.get(destID).getOrg_name().equals(sourceAccounts.get(sourceID).getOrg_name())) {
                    this.accountMap.put(sourceID, destID);
                    break;
                }
            }
        }
    }

    public void createMissingBackends() {
        Map<Integer, Backend> sourceBackends = this.sourceConfigLoader.getBackends();
        for (Integer sourceID : this.backendMap.keySet()) {
            if (this.backendMap.get(sourceID) == null) {
                int newID = createBackend(sourceBackends.get(sourceID));
                this.backendMap.put(sourceID, newID);
            }
        }
    }

    private int createBackend(Backend sourceBackend) {

        RestResponse<BackendWrapper> createBackend = this.destCLient.createBackend(config.importToken(), sourceBackend.getName(), sourceBackend.getSystem_name(), sourceBackend.getDescription(), sourceBackend.getPrivate_endpoint());

        Backend be = createBackend.getEntity().getBackend_api();

        //TODO Mapping Rules and metrics
        /*if(sourceBackend.getMetrics().size() == 1)
        {
            Log.info("source backend has only default metric, no metrics will be created");
        }
        else
        {
            
        }*/
        return be.getId();
    }

    public void createMissingServices() {
        Map<Integer, Service> sourceServices = this.sourceConfigLoader.getServices();
        for (Integer sourceID : this.serviceMap.keySet()) {
            if (this.serviceMap.get(sourceID) == null) {
                int newID = createService(sourceServices.get(sourceID));
                this.serviceMap.put(sourceID, newID);
            }
        }
    }

    private int createService(Service sourceService) {
        Map<Integer, Integer> serviceMetricMap = new HashMap<>();
        if (!config.forceDeploymentOption().equals("")) {
            sourceService.setDeployment_option(config.forceDeploymentOption());
        }
        Log.info("create service: " + sourceService.getName());
        RestResponse<ServiceWrapper> createService = this.destCLient.createService(config.importToken(), sourceService.getName(), sourceService.getDescription(), sourceService.getDeployment_option(), sourceService.getBackend_version(), sourceService.getSystem_name());
        Service service = createService.getEntity().getService();
        Log.info("create ServiceBackendUsage: " + sourceService.getName());
        for (ServiceBackendUsage usage : sourceService.getBackend_usage()) {
            RestResponse<ServiceBackendUsageWrapper> busw = this.destCLient.createServiceBackendUsages(config.importToken(), service.getId(), this.backendMap.get(usage.getBackend_id()), usage.getPath());
            service.addBackend_usage(busw.getEntity().getBackend_usage());
        }
        Log.info("create ServiceMetrics: " + sourceService.getName());
        RestResponse<ServiceMetrics> destMetrics = this.destCLient.getServiceMetrics(service.getId(), config.importToken());
        for (ServiceMetric sourceMetric : sourceService.getMetrics()) {

            serviceMetricMap.put(sourceMetric.getId(), null);
            for (ServiceMetricWrapper destMetricW : destMetrics.getEntity().getMetrics()) {
                ServiceMetric destMetric = destMetricW.getMetric();
                if (sourceMetric.getSystem_name().equals(destMetric.getSystem_name())) {
                    serviceMetricMap.put(sourceMetric.getId(), destMetric.getId());
                }
            }
            if (serviceMetricMap.get(sourceMetric.getId()) == null) {
                RestResponse<ServiceMetricWrapper> smw = this.destCLient.createServiceMetric(
                        config.importToken(),
                        service.getId(),
                        sourceMetric.getFriendly_name(),
                        sourceMetric.getSystem_name(),
                        sourceMetric.getUnit(),
                        sourceMetric.getDescription()
                );
                serviceMetricMap.put(sourceMetric.getId(), smw.getEntity().getMetric().getId());
            }
        }

        Proxy sourceProxy = sourceService.getProxyConfigs();
        Log.info("update ServiceProxy: " + sourceService.getName());

        RestResponse<ProxyWrapper> resProx = this.destCLient.updateServiceProxy(
                config.importToken(),
                service.getId(),
                sourceProxy.getEndpoint(),
                sourceProxy.getCredentials_location(),
                sourceProxy.getAuth_app_key(),
                sourceProxy.getAuth_app_id(),
                sourceProxy.getAuth_user_key(),
                sourceProxy.getError_auth_failed(),
                sourceProxy.getError_status_auth_failed(),
                sourceProxy.getError_headers_auth_failed(),
                sourceProxy.getError_auth_missing(),
                sourceProxy.getError_status_auth_missing(),
                sourceProxy.getError_headers_auth_missing(),
                sourceProxy.getError_no_match(),
                Integer.toString(sourceProxy.getError_status_no_match()),
                sourceProxy.getError_headers_no_match(),
                sourceProxy.getError_status_limits_exceeded(),
                sourceProxy.getError_headers_limits_exceeded(),
                sourceProxy.getError_limits_exceeded(),
                sourceProxy.getOidc_issuer_endpoint(),
                sourceProxy.getOidc_issuer_type(),
                sourceProxy.getSandbox_endpoint(),
                sourceProxy.getJwt_claim_with_client_id(),
                sourceProxy.getJwt_claim_with_client_id_type());

        Proxy proxy = resProx.getEntity().getProxy();
        ProxyOIDCConfig sourceOidcConfig = sourceProxy.getOidcConfig();
        Log.info("update ServiceProxy OIDCConfigs: " + sourceService.getName());
        RestResponse<ProxyOIDCConfigWrapper> resPOIDC = this.destCLient.updateServiceProxyOIDCConfigs(
                config.importToken(),
                service.getId(),
                sourceOidcConfig.isStandard_flow_enabled(),
                sourceOidcConfig.isImplicit_flow_enabled(),
                sourceOidcConfig.isService_accounts_enabled(),
                sourceOidcConfig.isDirect_access_grants_enabled());

        proxy.setOidcConfig(resPOIDC.getEntity().getOidc_configuration());
        Log.info("update ServiceProxy Policies: " + sourceService.getName());
        this.destCLient.updateServiceProxyPolicies(
                config.importToken(),
                service.getId(), sourceProxy.getPolicies_config());
        //proxy.setPolicies_config(sourceProxy.getPolicies_config());
        Log.info("create ServiceProxyMappingRule: " + sourceService.getName());
        for (ProxyMappingRule pmr : sourceProxy.getMappingRules()) {
            // avoid duplicates
            RestResponse<ProxyMappingRules> actualRules = this.destCLient.getServiceProxyMappingRules(config.importToken(),
                    service.getId());
            ProxyMappingRule found = null;
            for (ProxyMappingRuleWrapper rulew : actualRules.getEntity().getMapping_rules()) {
                ProxyMappingRule rule = rulew.getMapping_rule();
                if (pmr.getHttp_method().equals(rule.getHttp_method()) && pmr.getPattern().equals(rule.getPattern())) {
                    found = rule;
                    break;
                }
            }
            if (found == null) {
                RestResponse<ProxyMappingRuleWrapper> mappingRule = this.destCLient.createServiceProxyMappingRule(
                        config.importToken(),
                        service.getId(),
                        pmr.getHttp_method(),
                        pmr.getPattern(),
                        pmr.getDelta(),
                        serviceMetricMap.get(pmr.getMetric_id()),
                        pmr.getPosition(),
                        pmr.isLast()
                );
                found = mappingRule.getEntity().getMapping_rule();
            }
            proxy.addMappingRule(found);
        }

        Log.info("create Service ApplicationPlan: " + sourceService.getName());
        for (ApplicationPlan plan : sourceService.getPlans()) {
            String state_event= plan.getState().equals("hidden") ? "hide":"publish";
            RestResponse<ApplicationPlanWrapper> appw = this.destCLient.createServiceApplicationPlans(
                    config.importToken(),
                    service.getId(),
                    plan.getName(),
                    plan.getSystem_name(),
                    state_event
            );
                   /* 
                    plan.isApproval_required(),
                    plan.getCost_per_month(),
                    plan.getSetup_fee(),
                    plan.getTrial_period_days(),*/
                    
            ApplicationPlan destPlan = appw.getEntity().getApplication_plan();

            if (plan.isDefault()) {
                this.destCLient.setDefaultApplicationPlan(
                        config.importToken(),
                        service.getId(),
                        destPlan.getId());
            }
            planMap.put(plan.getId(), destPlan.getId());
            Log.info("create Service Application: " + sourceService.getName());
            for (Application app : plan.getApplications()) {
                RestResponse<ApplicationWrapper> applw = this.destCLient.createServiceApplication(
                        config.importToken(),
                        this.accountMap.get(app.getAccount_id()),
                        this.planMap.get(app.getPlan_id()),
                        app.getName(),
                        app.getDescription(),
                        app.getUser_key(),
                        app.getApplication_id(),
                        app.getApplication_key(),
                        app.getRedirect_url()
                );
                destPlan.addApplication(applw.getEntity().getApplication());

            }
            service.addPlan(plan);

        }

        // promoting proxy configurations
        this.destCLient.deployServiceProxy(config.importToken(), service.getId());

        return service.getId();

    }

    public void createMissingAccounts() {
        Map<Integer, Account> sourceAccounts = this.sourceConfigLoader.getAccounts();
        for (Integer sourceID : this.accountMap.keySet()) {
            if (this.accountMap.get(sourceID) == null) {
                int newID = createAccount(sourceAccounts.get(sourceID));
                this.accountMap.put(sourceID, newID);
            }
        }
    }

    private int createAccount(Account sourceAccount) {
        User admin = null;
        for (User user : sourceAccount.getUsers()) {
            if (user.getRole().equals("admin")) {
                admin = user;
                break;
            }
        }
        RestResponse<AccountWrapper> destAccount;
        if (admin != null) {
            destAccount = this.destCLient.createAccount(
                    config.importToken(),
                    sourceAccount.getOrg_name(),
                    admin.getUsername(),
                    admin.getEmail(),
                    admin.getUsername() + "_password");
        } else {
            Log.warn("the account for org " + sourceAccount.getOrg_name() + " has no administrators, creating with a dummy one");
            destAccount = this.destCLient.createAccount(
                    config.importToken(),
                    sourceAccount.getOrg_name(),
                    sourceAccount.getOrg_name() + "_admin",
                    sourceAccount.getOrg_name() + "_admin@example.com",
                    sourceAccount.getOrg_name() + "_admin_password");
        }

        return destAccount.getEntity().getAccount().getId();

    }

}
