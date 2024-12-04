/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services.proxies;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author fceresol
 */
public class ProxyOIDCConfig {

    private boolean standard_flow_enabled;
    private boolean implicit_flow_enabled;
    private boolean service_accounts_enabled;
    private boolean direct_access_grants_enabled;
    @JsonIgnore
    private Proxy parentProxy;

    /**
     * @return the standard_flow_enabled
     */
    public boolean isStandard_flow_enabled() {
        return standard_flow_enabled;
    }

    /**
     * @param standard_flow_enabled the standard_flow_enabled to set
     */
    public void setStandard_flow_enabled(boolean standard_flow_enabled) {
        this.standard_flow_enabled = standard_flow_enabled;
    }

    /**
     * @return the implicit_flow_enabled
     */
    public boolean isImplicit_flow_enabled() {
        return implicit_flow_enabled;
    }

    /**
     * @param implicit_flow_enabled the implicit_flow_enabled to set
     */
    public void setImplicit_flow_enabled(boolean implicit_flow_enabled) {
        this.implicit_flow_enabled = implicit_flow_enabled;
    }

    /**
     * @return the service_accounts_enabled
     */
    public boolean isService_accounts_enabled() {
        return service_accounts_enabled;
    }

    /**
     * @param service_accounts_enabled the service_accounts_enabled to set
     */
    public void setService_accounts_enabled(boolean service_accounts_enabled) {
        this.service_accounts_enabled = service_accounts_enabled;
    }

    /**
     * @return the direct_access_grants_enabled
     */
    public boolean isDirect_access_grants_enabled() {
        return direct_access_grants_enabled;
    }

    /**
     * @param direct_access_grants_enabled the direct_access_grants_enabled to set
     */
    public void setDirect_access_grants_enabled(boolean direct_access_grants_enabled) {
        this.direct_access_grants_enabled = direct_access_grants_enabled;
    }

    /**
     * @return the parentProxy
     */
    public Proxy getParentProxy() {
        return parentProxy;
    }

    /**
     * @param parentProxy the parentProxy to set
     */
    public void setParentProxy(Proxy parentProxy) {
        this.parentProxy = parentProxy;
    }
}
