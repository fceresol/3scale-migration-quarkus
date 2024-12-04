package com.redhat.fceresol.threescale.api.clients;

import com.fasterxml.jackson.databind.JsonNode;
import com.redhat.fceresol.threescale.api.accounts.AccountWrapper;
import com.redhat.fceresol.threescale.api.accounts.Accounts;
import com.redhat.fceresol.threescale.api.accounts.Users;
import com.redhat.fceresol.threescale.api.backends.BackendMethods;
import com.redhat.fceresol.threescale.api.backends.BackendMetrics;
import com.redhat.fceresol.threescale.api.backends.Backends;
import com.redhat.fceresol.threescale.api.backends.BackendMappingRules;
import com.redhat.fceresol.threescale.api.backends.BackendMetricWrapper;
import com.redhat.fceresol.threescale.api.backends.BackendWrapper;
import com.redhat.fceresol.threescale.api.services.ServiceBackendUsageWrapper;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlans;
import com.redhat.fceresol.threescale.api.services.applications.Applications;
import com.redhat.fceresol.threescale.api.services.ServiceMetricMethods;
import com.redhat.fceresol.threescale.api.services.ServiceMetricWrapper;
import com.redhat.fceresol.threescale.api.services.ServiceMetrics;
import com.redhat.fceresol.threescale.api.services.ServiceWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.Proxy;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyMappingRules;
import com.redhat.fceresol.threescale.api.services.Services;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationPlanWrapper;
import com.redhat.fceresol.threescale.api.services.applications.ApplicationWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyMappingRuleWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyOIDCConfigWrapper;
import com.redhat.fceresol.threescale.api.services.proxies.ProxyWrapper;
import jakarta.json.JsonObject;
import jakarta.ws.rs.FormParam;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import java.util.List;
import org.jboss.resteasy.reactive.RestResponse;

public abstract interface AbstractThreescaleClient {

    /* backend api*/
    @GET
    @Path("/backend_apis.json")
    public RestResponse<Backends> getBackends(
            @QueryParam("access_token") String access_token,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page
    );

    @GET
    @Path("/backend_apis/{backend_api_id}.json")
    public RestResponse<BackendWrapper> getBackendByID(
            @QueryParam("access_token") String access_token,
            @PathParam("backend_api_id") Integer backend_api_id
    );

    @GET
    @Path("/backend_apis/{backend_api_id}/mapping_rules.json")
    public RestResponse<BackendMappingRules> getBackendMappingRules(
            @PathParam("backend_api_id") Integer backend_api_id,
            @QueryParam("access_token") String access_token,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page
    );

    @GET
    @Path("/backend_apis/{backend_api_id}/metrics.json")
    public RestResponse<BackendMetrics> getBackendMetrics(
            @PathParam("backend_api_id") Integer backend_api_id,
            @QueryParam("access_token") String access_token,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page);

    @GET
    @Path("/backend_apis/{backend_api_id}/metrics/{metric_id}/methods.json")
    public RestResponse<BackendMethods> getBackendMethods(
            @PathParam("backend_api_id") Integer backend_api_id,
            @PathParam("metric_id") Integer metric_id,
            @QueryParam("access_token") String access_token,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page);

    @POST
    @Path("/backend_apis.json")
    public RestResponse<BackendWrapper> createBackend(
            @FormParam("access_token") String access_token,
            @FormParam("name") String name,
            @FormParam("system_name") String system_name,
            @FormParam("description") String description,
            @FormParam("private_endpoint") String private_endpoint
    );

    @POST
    @Path("/backend_apis/{backend_api_id}/mapping_rules.json")
    public RestResponse<BackendMappingRules> createBackendMappingRule(
            @PathParam("backend_api_id") Integer backend_api_id,
            @FormParam("access_token") String access_token,
            @FormParam("http_method") String http_method,
            @FormParam("delta") Integer delta,
            @FormParam("metric_id") Integer metric_id,
            @FormParam("position") Integer position,
            @FormParam("last") Boolean last
    );

    @POST
    @Path("/backend_apis/{backend_api_id}/metrics.json")
    public RestResponse<BackendMetricWrapper> createBackendMetric(
            @PathParam("backend_api_id") Integer backend_api_id,
            @FormParam("access_token") String access_token,
            @FormParam("friendly_name") String friendly_name,
            @FormParam("system_name") String system_name,
            @FormParam("unit") String unit,
            @FormParam("access_token") String description
    );

    @POST
    @Path("/backend_apis/{backend_api_id}/metrics/{metric_id}/methods.json")
    public RestResponse<BackendMethods> createBackendMethod(
            @PathParam("backend_api_id") Integer backend_api_id,
            @PathParam("metric_id") Integer metric_id,
            @FormParam("access_token") String access_token,
            @FormParam("friendly_name") String friendly_name,
            @FormParam("system_name") String system_name,
            @FormParam("unit") String unit,
            @FormParam("access_token") String description
    );

    /*service api*/
 /*data retrieval*/
    @GET
    @Path("/services.json")
    public RestResponse<Services> getServices(
            @QueryParam("access_token") String access_token,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page
    );

    @GET
    @Path("/services/{serviceID}.json")
    public RestResponse<ServiceWrapper> getServiceByID(
            @QueryParam("access_token") String access_token,
            @PathParam("serviceID") Integer serviceID
    );

    @GET
    @Path("/services/{service_id}/metrics.json")
    public RestResponse<ServiceMetrics> getServiceMetrics(
            @PathParam("service_id") Integer service_id,
            @QueryParam("access_token") String access_token
    );

    @GET
    @Path("/services/{service_id}/metrics/{metric_id}/methods.json")
    public RestResponse<ServiceMetricMethods> getServiceMetricMethods(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @PathParam("metric_id") Integer metric_id
    );

    @GET
    @Path("/services/{service_id}/proxy.json")
    public RestResponse<ProxyWrapper> getServiceProxy(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id
    );

    @GET
    @Path("/services/{service_id}/proxy/mapping_rules.json")
    public RestResponse<ProxyMappingRules> getServiceProxyMappingRules(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id
    );

    @GET
    @Path("/services/{service_id}/proxy/oidc_configuration.json")
    public RestResponse<ProxyOIDCConfigWrapper> getServiceProxyOIDCConfigs(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id
    );

    @GET
    @Path("/services/{service_id}/proxy/policies.json")
    public RestResponse<JsonNode> getServiceProxyPolicies(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id
    );

    @GET
    @Path("/services/{service_id}/backend_usages.json")
    public RestResponse<List<ServiceBackendUsageWrapper>> getServiceBackendUsages(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id
    );

    @GET
    @Path("/services/{service_id}/application_plans.json")
    public RestResponse<ApplicationPlans> getServiceApplicationPlans(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id
    );

    @GET
    @Path("/applications.json")
    public RestResponse<Applications> getServiceApplicationByPlan(
            @QueryParam("access_token") String access_token,
            @QueryParam("plan_id") Integer plan_id,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page
    );

    @GET
    @Path("/applications.json")
    public RestResponse<Applications> getServiceApplicationByService(
            @QueryParam("access_token") String access_token,
            @QueryParam("service_id") Integer service_id,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page
    );

    /*data creation*/
    @POST
    @Path("/services.json")
    public RestResponse<ServiceWrapper> createService(
            @FormParam("access_token") String access_token,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("deployment_option") String deployment_option,
            @FormParam("backend_version") String backend_version,
            @FormParam("system_name") String system_name
    );

    @POST
    @Path("/services/{service_id}/metrics.json")
    public RestResponse<ServiceMetricWrapper> createServiceMetric(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @FormParam("friendly_name") String friendly_name,
            @FormParam("system_name") String ystem_name,
            @FormParam("unit") String unit,
            @FormParam("description") String description
    );

    @POST
    @Path("/services/{service_id}/metrics/{metric_id}/methods.json")
    public RestResponse<ServiceMetricMethods> createServiceMetricMethods(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @PathParam("metric_id") Integer metric_id,
            @FormParam("friendly_name") String friendly_name,
            @FormParam("system_name") String system_name,
            @FormParam("unit") String unit,
            @FormParam("description") String description
    );

    @PATCH
    @Path("/services/{service_id}/proxy.json")
    public RestResponse<ProxyWrapper> updateServiceProxy(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @FormParam("endpoint") String endpoint,
            @FormParam("credentials_location") String credentials_location,
            @FormParam("auth_app_key") String auth_app_key,
            @FormParam("auth_app_id") String auth_app_id,
            @FormParam("auth_user_key") String auth_user_key,
            @FormParam("error_auth_failed") String error_auth_failed,
            @FormParam("error_status_auth_failed") Integer error_status_auth_failed,
            @FormParam("error_headers_auth_failed") String error_headers_auth_failed,
            @FormParam("error_auth_missing") String error_auth_missing,
            @FormParam("error_status_auth_missing") Integer error_status_auth_missing,
            @FormParam("error_headers_auth_missing") String error_headers_auth_missing,
            @FormParam("error_no_match") String error_no_match,
            @FormParam("error_status_no_match") String error_status_no_match,
            @FormParam("error_headers_no_match") String error_headers_no_match,
            @FormParam("error_status_limits_exceeded") Integer error_status_limits_exceeded,
            @FormParam("error_headers_limits_exceeded") String error_headers_limits_exceeded,
            @FormParam("error_limits_exceeded") String error_limits_exceeded,
            @FormParam("oidc_issuer_endpoint") String oidc_issuer_endpoint,
            @FormParam("oidc_issuer_type") String oidc_issuer_type,
            @FormParam("sandbox_endpoint") String sandbox_endpoint,
            @FormParam("jwt_claim_with_client_id") String jwt_claim_with_client_id,
            @FormParam("jwt_claim_with_client_id_type") String jwt_claim_with_client_id_type
    );

    @POST
    @Path("/services/{service_id}/proxy/deploy.json")
    public RestResponse<Proxy> deployServiceProxy(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id
    );

    @POST
    @Path("/services/{service_id}/proxy/mapping_rules.json")
    public RestResponse<ProxyMappingRuleWrapper> createServiceProxyMappingRule(
            @FormParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @FormParam("http_method") String http_method,
            @FormParam("pattern") String pattern,
            @FormParam("delta") Integer delta,
            @FormParam("metric_id") Integer metric_id,
            @FormParam("position") Integer position,
            @FormParam("last") Boolean last
    );

    @PATCH
    @Path("/services/{service_id}/proxy/oidc_configuration.json")
    public RestResponse<ProxyOIDCConfigWrapper> updateServiceProxyOIDCConfigs(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @FormParam("standard_flow_enabled") Boolean standard_flow_enabled,
            @FormParam("implicit_flow_enabled") Boolean implicit_flow_enabled,
            @FormParam("service_accounts_enabled") Boolean service_accounts_enabled,
            @FormParam("direct_access_grants_enabled") Boolean direct_access_grants_enabled
    );

    @PUT
    @Path("/services/{service_id}/proxy/policies.json")
    public RestResponse<JsonObject> updateServiceProxyPolicies(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @FormParam("policies_config") String policies_config
    );

    @POST
    @Path("/services/{service_id}/backend_usages.json")
    public RestResponse<ServiceBackendUsageWrapper> createServiceBackendUsages(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @FormParam("backend_api_id") Integer backend_api_id,
            @FormParam("path") String path
    );

    @POST
    @Path("/services/{service_id}/application_plans.json")
    public RestResponse<ApplicationPlanWrapper> createServiceApplicationPlans(
            @QueryParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @FormParam("name") String name,
            @FormParam("approval_required") Boolean approval_required,
            @FormParam("cost_per_month") Float cost_per_month,
            @FormParam("setup_fee") Float setup_fee,
            @FormParam("trial_period_days") Integer trial_period_days,
            @FormParam("system_name") String system_name,
            @FormParam("state_event") String state_event);

    @PUT
    @Path("/services/{service_id}/application_plans/{id}/default.json") // Application Plan Set to Default
    public RestResponse<ApplicationPlanWrapper> setDefaultApplicationPlan(
            @FormParam("access_token") String access_token,
            @PathParam("service_id") Integer service_id,
            @PathParam("id") Integer plan_id
    );

    @POST
    @Path("/accounts/{account_id}/applications.json")
    public RestResponse<ApplicationWrapper> createServiceApplication(
            @FormParam("access_token") String access_token,
            @PathParam("account_id") Integer account_id,
            @FormParam("plan_id") Integer plan_id,
            @FormParam("name") String name,
            @FormParam("description") String description,
            @FormParam("user_key") String user_key,
            @FormParam("application_id") String application_id,
            @FormParam("application_key") String application_key,
            @FormParam("redirect_url") String redirect_url
    );

    @GET
    @Path("/accounts.json")
    public RestResponse<Accounts> getAccounts(
            @QueryParam("access_token") String access_token,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page
    );

    @GET
    @Path("/accounts/{account_id}/users.json")
    public RestResponse<Users> getAccountUsers(
            @QueryParam("access_token") String access_token,
            @PathParam("account_id") Integer account_id,
            @QueryParam("page") Integer page,
            @QueryParam("per_page") Integer per_page
    );
    @GET
    @Path("/accounts/{account_id}.json")
    public RestResponse<AccountWrapper> getAccountByID(
            @QueryParam("access_token") String access_token,
            @PathParam("account_id") Integer account_id
    );
    @POST
    @Path("/signup.json")
    public RestResponse<AccountWrapper> createAccount(
            @FormParam("access_token") String access_token,
            @FormParam("org_name") String org_name,
            @FormParam("username") String username,
            @FormParam("email") String email,
            @FormParam("password") String password
    );

}
