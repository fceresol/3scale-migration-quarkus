/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services.proxies;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.databind.JsonNode;
import com.redhat.fceresol.threescale.api.services.Service;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class Proxy {

    private int service_id;
    private String endpoint;
    private String credentials_location;
    private String auth_app_key;
    private String auth_app_id;
    private String auth_user_key;
    private String error_auth_failed;
    private String error_auth_missing;
    private int error_status_auth_failed;
    private String error_headers_auth_failed;
    private int error_status_auth_missing;
    private String error_headers_auth_missing;
    private String error_no_match;
    private int error_status_no_match;
    private String error_headers_no_match;
    private String error_limits_exceeded;
    private int error_status_limits_exceeded;
    private String error_headers_limits_exceeded;
    private String secret_token;
    private String sandbox_endpoint;
    @JsonRawValue
    private Object policies_config;
    private String deployment_option;
    private String oidc_issuer_endpoint;
    private String oidc_issuer_type;

    private String jwt_claim_with_client_id;
    private String jwt_claim_with_client_id_type;
    @JsonIgnore
    private Service parentService;
    @JsonIgnore
    private ProxyOIDCConfig oidcConfig;
    @JsonIgnore
     private List<ProxyMappingRule> mappingRules;

    public Proxy() {
        this.service_id = 0;
        this.endpoint = null;
        this.credentials_location = null;
        this.auth_app_key = null;
        this.auth_app_id = null;
        this.auth_user_key = null;
        this.error_auth_failed = null;
        this.error_auth_missing = null;
        this.error_status_auth_failed = 0;
        this.error_headers_auth_failed = null;
        this.error_status_auth_missing = 0;
        this.error_headers_auth_missing = null;
        this.error_no_match = null;
        this.error_status_no_match = 0;
        this.error_headers_no_match = null;
        this.error_limits_exceeded = null;
        this.error_status_limits_exceeded = 0;
        this.error_headers_limits_exceeded = null;
        this.secret_token = null;
        this.sandbox_endpoint = null;
        this.policies_config = null;
        this.deployment_option = null;
        this.oidc_issuer_endpoint = null;
        this.oidc_issuer_type = null;
        this.jwt_claim_with_client_id = null;
        this.jwt_claim_with_client_id_type = null;
        this.parentService = null;
        this.oidcConfig = null;
        this.mappingRules = new ArrayList<>();
    }

     
    /**
     * @return the service_id
     */
    public int getService_id() {
        return service_id;
    }

    /**
     * @param service_id the service_id to set
     */
    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    /**
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the credentials_location
     */
    public String getCredentials_location() {
        return credentials_location;
    }

    /**
     * @param credentials_location the credentials_location to set
     */
    public void setCredentials_location(String credentials_location) {
        this.credentials_location = credentials_location;
    }

    /**
     * @return the auth_app_key
     */
    public String getAuth_app_key() {
        return auth_app_key;
    }

    /**
     * @param auth_app_key the auth_app_key to set
     */
    public void setAuth_app_key(String auth_app_key) {
        this.auth_app_key = auth_app_key;
    }

    /**
     * @return the auth_app_id
     */
    public String getAuth_app_id() {
        return auth_app_id;
    }

    /**
     * @param auth_app_id the auth_app_id to set
     */
    public void setAuth_app_id(String auth_app_id) {
        this.auth_app_id = auth_app_id;
    }

    /**
     * @return the auth_user_key
     */
    public String getAuth_user_key() {
        return auth_user_key;
    }

    /**
     * @param auth_user_key the auth_user_key to set
     */
    public void setAuth_user_key(String auth_user_key) {
        this.auth_user_key = auth_user_key;
    }

    /**
     * @return the error_auth_failed
     */
    public String getError_auth_failed() {
        return error_auth_failed;
    }

    /**
     * @param error_auth_failed the error_auth_failed to set
     */
    public void setError_auth_failed(String error_auth_failed) {
        this.error_auth_failed = error_auth_failed;
    }

    /**
     * @return the error_auth_missing
     */
    public String getError_auth_missing() {
        return error_auth_missing;
    }

    /**
     * @param error_auth_missing the error_auth_missing to set
     */
    public void setError_auth_missing(String error_auth_missing) {
        this.error_auth_missing = error_auth_missing;
    }

    /**
     * @return the error_status_auth_failed
     */
    public int getError_status_auth_failed() {
        return error_status_auth_failed;
    }

    /**
     * @param error_status_auth_failed the error_status_auth_failed to set
     */
    public void setError_status_auth_failed(int error_status_auth_failed) {
        this.error_status_auth_failed = error_status_auth_failed;
    }

    /**
     * @return the error_headers_auth_failed
     */
    public String getError_headers_auth_failed() {
        return error_headers_auth_failed;
    }

    /**
     * @param error_headers_auth_failed the error_headers_auth_failed to set
     */
    public void setError_headers_auth_failed(String error_headers_auth_failed) {
        this.error_headers_auth_failed = error_headers_auth_failed;
    }

    /**
     * @return the error_status_auth_missing
     */
    public int getError_status_auth_missing() {
        return error_status_auth_missing;
    }

    /**
     * @param error_status_auth_missing the error_status_auth_missing to set
     */
    public void setError_status_auth_missing(int error_status_auth_missing) {
        this.error_status_auth_missing = error_status_auth_missing;
    }

    /**
     * @return the error_headers_auth_missing
     */
    public String getError_headers_auth_missing() {
        return error_headers_auth_missing;
    }

    /**
     * @param error_headers_auth_missing the error_headers_auth_missing to set
     */
    public void setError_headers_auth_missing(String error_headers_auth_missing) {
        this.error_headers_auth_missing = error_headers_auth_missing;
    }

    /**
     * @return the error_no_match
     */
    public String getError_no_match() {
        return error_no_match;
    }

    /**
     * @param error_no_match the error_no_match to set
     */
    public void setError_no_match(String error_no_match) {
        this.error_no_match = error_no_match;
    }

    /**
     * @return the error_status_no_match
     */
    public int getError_status_no_match() {
        return error_status_no_match;
    }

    /**
     * @param error_status_no_match the error_status_no_match to set
     */
    public void setError_status_no_match(int error_status_no_match) {
        this.error_status_no_match = error_status_no_match;
    }

    /**
     * @return the error_headers_no_match
     */
    public String getError_headers_no_match() {
        return error_headers_no_match;
    }

    /**
     * @param error_headers_no_match the error_headers_no_match to set
     */
    public void setError_headers_no_match(String error_headers_no_match) {
        this.error_headers_no_match = error_headers_no_match;
    }

    /**
     * @return the error_limits_exceeded
     */
    public String getError_limits_exceeded() {
        return error_limits_exceeded;
    }

    /**
     * @param error_limits_exceeded the error_limits_exceeded to set
     */
    public void setError_limits_exceeded(String error_limits_exceeded) {
        this.error_limits_exceeded = error_limits_exceeded;
    }

    /**
     * @return the error_status_limits_exceeded
     */
    public int getError_status_limits_exceeded() {
        return error_status_limits_exceeded;
    }

    /**
     * @param error_status_limits_exceeded the error_status_limits_exceeded to set
     */
    public void setError_status_limits_exceeded(int error_status_limits_exceeded) {
        this.error_status_limits_exceeded = error_status_limits_exceeded;
    }

    /**
     * @return the error_headers_limits_exceeded
     */
    public String getError_headers_limits_exceeded() {
        return error_headers_limits_exceeded;
    }

    /**
     * @param error_headers_limits_exceeded the error_headers_limits_exceeded to set
     */
    public void setError_headers_limits_exceeded(String error_headers_limits_exceeded) {
        this.error_headers_limits_exceeded = error_headers_limits_exceeded;
    }

    /**
     * @return the secret_token
     */
    public String getSecret_token() {
        return secret_token;
    }

    /**
     * @param secret_token the secret_token to set
     */
    public void setSecret_token(String secret_token) {
        this.secret_token = secret_token;
    }

    /**
     * @return the sandbox_endpoint
     */
    public String getSandbox_endpoint() {
        return sandbox_endpoint;
    }

    /**
     * @param sandbox_endpoint the sandbox_endpoint to set
     */
    public void setSandbox_endpoint(String sandbox_endpoint) {
        this.sandbox_endpoint = sandbox_endpoint;
    }

    /**
     * @return the policies_config
     */
    public String getPolicies_config() {
        return policies_config.toString();
    }

    /**
     * @param policies_config the policies_config to set
     */
    public void setPolicies_config(JsonNode policies_config) {
        this.policies_config = policies_config;
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
     * @return the oidc_issuer_endpoint
     */
    public String getOidc_issuer_endpoint() {
        return oidc_issuer_endpoint;
    }

    /**
     * @param oidc_issuer_endpoint the oidc_issuer_endpoint to set
     */
    public void setOidc_issuer_endpoint(String oidc_issuer_endpoint) {
        this.oidc_issuer_endpoint = oidc_issuer_endpoint;
    }

    /**
     * @return the oidc_issuer_type
     */
    public String getOidc_issuer_type() {
        return oidc_issuer_type;
    }

    /**
     * @param oidc_issuer_type the oidc_issuer_type to set
     */
    public void setOidc_issuer_type(String oidc_issuer_type) {
        this.oidc_issuer_type = oidc_issuer_type;
    }

    /**
     * @return the jwt_claim_with_client_id
     */
    public String getJwt_claim_with_client_id() {
        return jwt_claim_with_client_id;
    }

    /**
     * @param jwt_claim_with_client_id the jwt_claim_with_client_id to set
     */
    public void setJwt_claim_with_client_id(String jwt_claim_with_client_id) {
        this.jwt_claim_with_client_id = jwt_claim_with_client_id;
    }

    /**
     * @return the jwt_claim_with_client_id_type
     */
    public String getJwt_claim_with_client_id_type() {
        return jwt_claim_with_client_id_type;
    }

    /**
     * @param jwt_claim_with_client_id_type the jwt_claim_with_client_id_type to set
     */
    public void setJwt_claim_with_client_id_type(String jwt_claim_with_client_id_type) {
        this.jwt_claim_with_client_id_type = jwt_claim_with_client_id_type;
    }

    /**
     * @return the parentService
     */
    public Service getParentService() {
        return parentService;
    }

    /**
     * @param parentService the parentService to set
     */
    public void setParentService(Service parentService) {
        this.parentService = parentService;
    }

    /**
     * @return the oidcConfig
     */
    public ProxyOIDCConfig getOidcConfig() {
        return oidcConfig;
    }

    /**
     * @param oidcConfig the oidcConfig to set
     */
    public void setOidcConfig(ProxyOIDCConfig oidcConfig) {
        this.oidcConfig = oidcConfig;
    }

    /**
     * @return the mappingRules
     */
    public List<ProxyMappingRule> getMappingRules() {
        return mappingRules;
    }

    /**
     * @param mappingRules the mappingRules to set
     */
    public void setMappingRules(List<ProxyMappingRule> mappingRules) {
        this.mappingRules = mappingRules;
    }
    
    public void addMappingRule(ProxyMappingRule mappingRule)
    {
        mappingRule.setParentProxy(this);
        this.mappingRules.add(mappingRule);
    }
    
    
}
