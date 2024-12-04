/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale;

import com.redhat.fceresol.config.MigratorConfig;
import com.redhat.fceresol.exceptions.LoadException;
import com.redhat.fceresol.threescale.api.accounts.Account;
import com.redhat.fceresol.threescale.api.accounts.AccountWrapper;
import com.redhat.fceresol.threescale.api.accounts.Accounts;
import com.redhat.fceresol.threescale.api.accounts.User;
import com.redhat.fceresol.threescale.api.accounts.UserWrapper;
import com.redhat.fceresol.threescale.api.accounts.Users;
import com.redhat.fceresol.threescale.api.backends.Backend;
import com.redhat.fceresol.threescale.api.backends.BackendMappingRule;
import com.redhat.fceresol.threescale.api.backends.BackendMappingRuleWrapper;
import com.redhat.fceresol.threescale.api.backends.BackendMappingRules;
import com.redhat.fceresol.threescale.api.backends.BackendMethods;
import com.redhat.fceresol.threescale.api.backends.BackendMetric;
import com.redhat.fceresol.threescale.api.backends.BackendMetricWrapper;
import com.redhat.fceresol.threescale.api.backends.BackendMetrics;
import com.redhat.fceresol.threescale.api.backends.BackendWrapper;
import com.redhat.fceresol.threescale.api.backends.Backends;
import com.redhat.fceresol.threescale.api.clients.AbstractThreescaleClient;
import com.redhat.fceresol.threescale.api.services.Service;
import com.redhat.fceresol.threescale.api.services.ServiceBackendUsage;
import com.redhat.fceresol.threescale.api.services.ServiceBackendUsageWrapper;
import com.redhat.fceresol.threescale.api.services.ServiceMetric;
import com.redhat.fceresol.threescale.api.services.ServiceMetricWrapper;
import com.redhat.fceresol.threescale.api.services.ServiceMetrics;
import com.redhat.fceresol.threescale.api.services.ServiceWrapper;
import com.redhat.fceresol.threescale.api.services.Services;
import com.redhat.fceresol.threescale.api.services.applications.Application;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlan;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlanWrapper;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlans;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationWrapper;
import com.redhat.fceresol.threescale.api.services.applications.Applications;
import com.redhat.fceresol.threescale.api.services.proxies.Proxy;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyMappingRule;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyMappingRules;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyOIDCConfigWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyWrapper;
import io.quarkus.logging.Log;
import jakarta.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jboss.resteasy.reactive.RestResponse;

/**
 * TODO error handling
 *
 * @author fceresol
 */
public class ConfigLoader {

    private AbstractThreescaleClient client;
    private HashMap<Integer, Backend> backends;
    private HashMap<Integer, Service> services;
    private HashMap<Integer, Account> accounts;
    private MigratorConfig config;
    private String apiToken;

    public ConfigLoader() {
        this(null, null, null);
    }

    public ConfigLoader(AbstractThreescaleClient client, MigratorConfig config, String apiToken) {
        this.client = client;
        this.config = config;
        this.apiToken = apiToken;
        this.backends = new HashMap<>();
        this.services = new HashMap<>();
        this.accounts = new HashMap<>();
    }

    public void loadConfigurations() throws LoadException {
        this.loadBackends();
        this.loadServices();
        this.loadAccounts();
        Log.info("loaded:");
        Log.info("services: " + this.services.keySet().size());
        Log.info("backends: " + this.backends.keySet().size());
        Log.info("accounts: " + this.accounts.keySet().size());

    }
    
    public void loadConfigurations(List<Integer> serviceIDs) throws LoadException {
        this.loadServices(serviceIDs);
        List<Integer> usedBackendsIDs = this.getBackendFromUsages();
        
        this.loadBackends(usedBackendsIDs);


        Log.info("loaded:");
        Log.info("services: " + this.services.keySet().size());
        Log.info("backends: " + this.backends.keySet().size());
        Log.info("accounts: " + this.accounts.keySet().size());

    }

    public void loadAccounts() throws LoadException {
        RestResponse<Accounts> accounts = client.getAccounts(this.apiToken, 1, 500);
        for(AccountWrapper accountw: accounts.getEntity().getAccounts())
        {
            Account account = this.loadAccount(accountw.getAccount());
            
            this.accounts.put(account.getId(), account);
        }
        
    }

    public void loadBackends() throws LoadException {
        
               
        RestResponse<Backends> resd = client.getBackends(this.apiToken, 1, 500);

        //TODO handle status code
        Backends res = resd.getEntity();

        for (BackendWrapper bew : res.getBackend_apis()) {

            Backend be = this.loadBackend(bew.getBackend_api());
            
            this.backends.put(be.getId(), be);
        }
    }

    public BackendMappingRules loadBackendMappingRules(int backendID) throws LoadException {
        RestResponse<BackendMappingRules> mappingRules = client.getBackendMappingRules(backendID, this.apiToken, 1, 500);
        return mappingRules.getEntity();
    }

    public BackendMetrics loadBackendMetrics(int backendID) throws LoadException {
        RestResponse<BackendMetrics> metrics = client.getBackendMetrics(backendID, this.apiToken, 1, 500);
        return metrics.getEntity();
    }

    public BackendMethods loadBackendMethods(int backendID, int metricID) throws LoadException {
        RestResponse<BackendMethods> methods = client.getBackendMethods(backendID, metricID, this.apiToken, 1, 500);
        return methods.getEntity();
    }

    public void loadServices() throws LoadException {
        RestResponse<Services> svcs = this.client.getServices(this.apiToken, 1, 500);
        for (ServiceWrapper sew : svcs.getEntity().getServices()) {

            Service svc = this.loadService(sew.getService());

            this.services.put(svc.getId(), svc);
        }
    }

    /**
     * @return the client
     */
    public AbstractThreescaleClient getClient() {
        return this.client;
    }

    /**
     * @param client the client to set
     */
    public void setClient(AbstractThreescaleClient client) {
        this.client = client;
    }

    /**
     * @return the backends
     */
    public HashMap<Integer, Backend> getBackends() {
        return this.backends;
    }

    /**
     * @param backends the backends to set
     */
    public void setBackends(HashMap<Integer, Backend> backends) {
        this.backends = backends;
    }

    /**
     * @return the services
     */
    public HashMap<Integer, Service> getServices() {
        return this.services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(HashMap<Integer, Service> services) {
        this.services = services;
    }

    /**
     * @return the accounts
     */
    public HashMap<Integer, Account> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(HashMap<Integer, Account> accounts) {
        this.accounts = accounts;
    }

    private ServiceMetrics loadServiceMetrics(int serviceID) {
        RestResponse<ServiceMetrics> response = this.client.getServiceMetrics(serviceID, this.apiToken);
        ServiceMetrics metrics = response.getEntity();
        //ignored methods
        return metrics;

    }

    private ApplicationPlans loadApplicationPlans(int serviceID) {
        RestResponse<ApplicationPlans> response = this.client.getServiceApplicationPlans(this.apiToken, serviceID);
        ApplicationPlans appPlans = response.getEntity();
        for (ApplicationPlanWrapper apw : appPlans.getPlans()) {
            ApplicationPlan plan = apw.getApplication_plan();
            RestResponse<Applications> applications = this.client.getServiceApplicationByPlan(this.apiToken, plan.getId(), 1, 500);

            for (ApplicationWrapper aw : applications.getEntity().getApplications()) {
                Application app = aw.getApplication();

                plan.addApplication(app);
            }

        }

        return appPlans;

    }

    /**
     * @return the config
     */
    public MigratorConfig getConfig() {
        return config;
    }

    /**
     * @param config the config to set
     */
    public void setConfig(MigratorConfig config) {
        this.config = config;
    }

    /**
     * @return the apiToken
     */
    public String getApiToken() {
        return apiToken;
    }

    /**
     * @param apiToken the apiToken to set
     */
    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
    }

    private List<ServiceBackendUsageWrapper> loadBackendUsages(int serviceID) {
        RestResponse<List<ServiceBackendUsageWrapper>> response = this.client.getServiceBackendUsages(apiToken, serviceID);
        System.out.println(response.toResponse().toString());
        return response.getEntity();

    }

    private Proxy loadProxyConfigs(int serviceID) {
        RestResponse<ProxyWrapper> response = this.client.getServiceProxy(this.apiToken, serviceID);
        Proxy proxy = response.getEntity().getProxy();


        RestResponse<ProxyOIDCConfigWrapper> oidcConfigResponse = this.client.getServiceProxyOIDCConfigs(apiToken, serviceID);

        proxy.setOidcConfig(oidcConfigResponse.getEntity().getOidc_configuration());

        List<ProxyMappingRule> proxyMappingRules = this.loadProxyMappingRules(serviceID);

        proxyMappingRules.forEach((rule) -> {
            proxy.addMappingRule(rule);
        });
        return proxy;

    }

    private List<ProxyMappingRule> loadProxyMappingRules(int serviceID) {

        RestResponse<ProxyMappingRules> rules = this.client.getServiceProxyMappingRules(apiToken, serviceID);

        ArrayList<ProxyMappingRule> ret = new ArrayList<>();

        rules.getEntity().getMapping_rules().forEach((pmrw) -> {
            ret.add(pmrw.getMapping_rule());
        });
        return ret;
    }

    private void loadServices(List<Integer> serviceIDs) {
        serviceIDs.forEach((Integer serviceID) -> {
            RestResponse<ServiceWrapper> response = ConfigLoader.this.client.getServiceByID(apiToken, serviceID);
            //since is an ID based search the result must contain at most 1 service
            if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
                Log.warn("no services found for ID: " + serviceID);
            } else {
                Service svc = response.getEntity().getService();
                ConfigLoader.this.services.put(serviceID, ConfigLoader.this.loadService(svc));
            }
        });
    }
    
    private void loadAccounts(List<Integer> accountIDs) {
        accountIDs.forEach((Integer accountID) -> {
            RestResponse<AccountWrapper> response = ConfigLoader.this.client.getAccountByID(apiToken, accountID);
            //since is an ID based search the result must contain at most 1 service
            if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
                Log.warn("no accounts found for ID: " + accountID);
            } else {
                Account acc = response.getEntity().getAccount();
                ConfigLoader.this.accounts.put(accountID, ConfigLoader.this.loadAccount(acc));
            }
        });
    }
    

    private List<Integer> getBackendFromUsages() {
        ArrayList<Integer> ret = new ArrayList<>();
        services.keySet().forEach((Integer serviceID) ->{
            Service service = services.get(serviceID);
            service.getBackend_usage().forEach((ServiceBackendUsage usage) -> {
                ret.add(usage.getBackend_id());
            });
        });
        return ret;
    }

    private void loadBackends(List<Integer> usedBackendsIDs) throws LoadException {
        for(Integer backendID : usedBackendsIDs)
        {
            RestResponse<BackendWrapper> response = ConfigLoader.this.client.getBackendByID(apiToken, backendID);
            //since is an ID based search the result must contain at most 1 service
            if (response.getStatus() == Status.NOT_FOUND.getStatusCode()) {
                Log.warn("no backends found for ID: " + backendID);
            } else {
                Backend be = response.getEntity().getBackend_api();
                ConfigLoader.this.backends.put(backendID, ConfigLoader.this.loadBackend(be));
            }
        }
    }

    private Service loadService(Service service) {
        ServiceMetrics metrics = this.loadServiceMetrics(service.getId());

            for (ServiceMetricWrapper metricWrapper : metrics.getMetrics()) {
                ServiceMetric metric = metricWrapper.getMetric();
                //ServiceMeth methods = this.loadBackendMethods(be.getId(), metric.getId());
                //metric.addMethod(metric)
                service.addMetric(metric);
            }

            ApplicationPlans appPlans = this.loadApplicationPlans(service.getId());

            for (ApplicationPlanWrapper appPlanWrapper : appPlans.getPlans()) {
                ApplicationPlan appPlan = appPlanWrapper.getApplication_plan();
                //BackendMethods methods = this.loadBackendMethods(be.getId(), metric.getId());
                //metric.addMethod(metric)
                service.addPlan(appPlan);
            }

            List<ServiceBackendUsageWrapper> backendUsages = this.loadBackendUsages(service.getId());
            Log.info("ServiceBackendUsage found: " + backendUsages.size());
            Proxy proxyConfigs = this.loadProxyConfigs(service.getId());
            backendUsages.forEach((beusage) -> service.addBackend_usage(beusage.getBackend_usage()));

            service.setProxyConfigs(proxyConfigs);
            
            return service;
    }

    private Backend loadBackend(Backend backend_api) throws LoadException {
        BackendMetrics metrics = this.loadBackendMetrics(backend_api.getId());

            for (BackendMetricWrapper metricWrapper : metrics.getMetrics()) {
                BackendMetric metric = metricWrapper.getMetric();
                //BackendMethods methods = this.loadBackendMethods(be.getId(), metric.getId());
                //metric.addMethod(metric)
                backend_api.addMetric(metric);
            }
            BackendMappingRules bmr = this.loadBackendMappingRules(backend_api.getId());
            for (BackendMappingRuleWrapper mappingRuleWrapper : bmr.getMapping_rules()) {
                BackendMappingRule rule = mappingRuleWrapper.getMapping_rule();
                //BackendMethods methods = this.loadBackendMethods(be.getId(), metric.getId());
                //metric.addMethod(metric)
                backend_api.addMapping_rule(rule);
            }
            return backend_api;
    }

    private Account loadAccount(Account account) {
        RestResponse<Users> users = client.getAccountUsers(apiToken, account.getId(), 1,500);
            for(UserWrapper uw : users.getEntity().getUsers())
            {
                User u = uw.getUser();
                account.addUser(u);
            }
            return account;
    }

}
