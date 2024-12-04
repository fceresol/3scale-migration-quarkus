package com.redhat.fceresol.threescale.api.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redhat.fceresol.threescale.api.services.applications.Application;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlan;
import com.redhat.fceresol.threescale.api.services.proxies.Proxy;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private int id;
    private String name;
    private String system_name;
    private String backend_version;
    private String deployment_option;
    private String description;
    @JsonIgnore
    private List<ServiceMetric> metrics;
    @JsonIgnore
    private List<ApplicationPlan> plans;
    @JsonIgnore
    private List<ServiceBackendUsage> backend_usage;
    @JsonIgnore
    private Proxy proxyConfigs;

    public List<ServiceBackendUsage> getBackend_usage() {
        return backend_usage;
    }

    public void setBackend_usage(List<ServiceBackendUsage> backend_usage) {
        this.backend_usage = backend_usage;
    }

    public void addBackend_usage(ServiceBackendUsage backendUsage) {
        backendUsage.setService(this);
        this.backend_usage.add(backendUsage);

    }

    public Proxy getProxyConfigs() {
        return proxyConfigs;
    }

    public void setProxyConfigs(Proxy proxyConfigs) {
        proxyConfigs.setParentService(this);
        this.proxyConfigs = proxyConfigs;
    }

    public Service() {
        this.id = 0;
        this.metrics = new ArrayList<>();
        this.plans = new ArrayList<>();
        this.backend_usage = new ArrayList<>();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the system_name
     */
    public String getSystem_name() {
        return system_name;
    }

    /**
     * @param system_name the system_name to set
     */
    public void setSystem_name(String system_name) {
        this.system_name = system_name;
    }

    /**
     * @return the backend_version
     */
    public String getBackend_version() {
        return backend_version;
    }

    /**
     * @param backend_version the backend_version to set
     */
    public void setBackend_version(String backend_version) {
        this.backend_version = backend_version;
    }

    /**
     * @return the deployment_option
     */
    public String getDeployment_option() {
        return deployment_option;
    }

    /**
     * @param deployment_option the deployment_option to set
     */
    public void setDeployment_option(String deployment_option) {
        this.deployment_option = deployment_option;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the metrics
     */
    public List<ServiceMetric> getMetrics() {
        return metrics;
    }

    /**
     * @param metrics the metrics to set
     */
    public void setMetrics(List<ServiceMetric> metrics) {
        this.metrics = metrics;
    }

    public void addMetric(ServiceMetric metric) {
        metric.setParentService(this);
        this.metrics.add(metric);
    }

    /**
     * @return the plans
     */
    public List<ApplicationPlan> getPlans() {
        return plans;
    }

    /**
     * @param plans the plans to set
     */
    public void setPlans(List<ApplicationPlan> plans) {
        this.plans = plans;
    }

    /**
     * @param plans the plans to set
     */
    public void addPlan(ApplicationPlan plan) {
        plan.setParentService(this);
        this.plans.add(plan);
    }

    /**
     * @return the applications
     */
    public List<Application> getApplications() {
        ArrayList<Application> apps = new ArrayList<>();
        this.plans.forEach((ApplicationPlan plan) -> {
            apps.addAll(plan.getApplications());
                    });
        return apps;
    }

}
