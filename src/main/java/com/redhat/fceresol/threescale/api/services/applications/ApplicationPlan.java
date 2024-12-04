/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services.applications;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redhat.fceresol.threescale.api.services.Service;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class ApplicationPlan {

    private int id;
    private String name;
    private boolean approval_required;
    private String state;
    private float setup_fee;
    private float cost_per_month;
    private int trial_period_days;
    private int cancellation_period;
    private boolean isDefault;
    private boolean custom;
    private String system_name;
    @JsonIgnore
    private Service parentService;
    @JsonIgnore
    private List<Application> applications;

    public ApplicationPlan()
    {
        this.applications = new ArrayList<>();
        
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
     * @return the approval_required
     */
    public boolean isApproval_required() {
        return approval_required;
    }

    /**
     * @param approval_required the approval_required to set
     */
    public void setApproval_required(boolean approval_required) {
        this.approval_required = approval_required;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the setup_fee
     */
    public float getSetup_fee() {
        return setup_fee;
    }

    /**
     * @param setup_fee the setup_fee to set
     */
    public void setSetup_fee(float setup_fee) {
        this.setup_fee = setup_fee;
    }

    /**
     * @return the cost_per_month
     */
    public float getCost_per_month() {
        return cost_per_month;
    }

    /**
     * @param cost_per_month the cost_per_month to set
     */
    public void setCost_per_month(float cost_per_month) {
        this.cost_per_month = cost_per_month;
    }

    /**
     * @return the trial_period_days
     */
    public int getTrial_period_days() {
        return trial_period_days;
    }

    /**
     * @param trial_period_days the trial_period_days to set
     */
    public void setTrial_period_days(int trial_period_days) {
        this.trial_period_days = trial_period_days;
    }

    /**
     * @return the cancellation_period
     */
    public int getCancellation_period() {
        return cancellation_period;
    }

    /**
     * @param cancellation_period the cancellation_period to set
     */
    public void setCancellation_period(int cancellation_period) {
        this.cancellation_period = cancellation_period;
    }

    /**
     * @return the isDefault
     */
    public boolean isDefault() {
        return isDefault;
    }

    /**
     * @param isDefault the isDefault to set
     */
    public void setDefault(boolean isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * @return the custom
     */
    public boolean isCustom() {
        return custom;
    }

    /**
     * @param custom the custom to set
     */
    public void setCustom(boolean custom) {
        this.custom = custom;
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
     * @return the parentService
     */
    public Service getParentService() {
        return parentService;
    }

    public void setParentService(Service service) {
        this.parentService = service;
        
    }
    
     /**
     * @return the parentService
     */
    public void setApplications(List<Application> applications) {
        this.applications = applications;
    }

    public List<Application> getApplications() {
        return this.applications;
    }
    
    public void addApplication(Application application) {
        application.setParentPlan(this);
        this.applications.add(application);
    }
    

}
