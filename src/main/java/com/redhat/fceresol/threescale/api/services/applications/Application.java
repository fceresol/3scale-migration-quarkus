/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services.applications;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author fceresol
 */
public class Application {
        private int id;
        private int plan_id;
        private String user_key;
        private String application_id;
        private String application_key;
        private String name;
        private String description;
        private int account_id;
        private String redirect_url;
        @JsonIgnore
        private ApplicationPlan parentPlan;

     public Application()
     {
         this.redirect_url="";
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
     * @return the plan_id
     */
    public int getPlan_id() {
        return plan_id;
    }

    /**
     * @param plan_id the plan_id to set
     */
    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    /**
     * @return the user_key
     */
    public String getUser_key() {
        return user_key;
    }

    /**
     * @param user_key the user_key to set
     */
    public void setUser_key(String user_key) {
        this.user_key = user_key;
    }

    /**
     * @return the application_id
     */
    public String getApplication_id() {
        return application_id;
    }

    /**
     * @param application_id the application_id to set
     */
    public void setApplication_id(String application_id) {
        this.application_id = application_id;
    }

    /**
     * @return the application_key
     */
    public String getApplication_key() {
        return application_key;
    }

    /**
     * @param application_key the application_key to set
     */
    public void setApplication_key(String application_key) {
        this.application_key = application_key;
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
     * @return the parentPlan
     */
    public ApplicationPlan getParentPlan() {
        return parentPlan;
    }

    /**
     * @param parentPlan the parentPlan to set
     */
    public void setParentPlan(ApplicationPlan parentPlan) {
        this.parentPlan = parentPlan;
    }

    /**
     * @return the account_id
     */
    public int getAccount_id() {
        return account_id;
    }

    /**
     * @param account_id the account_id to set
     */
    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    /**
     * @return the redirect_url
     */
    public String getRedirect_url() {
        return redirect_url;
    }

    /**
     * @param redirect_url the redirect_url to set
     */
    public void setRedirect_url(String redirect_url) {
        this.redirect_url = redirect_url;
    }


}
