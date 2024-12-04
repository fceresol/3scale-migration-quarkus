/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class ServiceMetric {

    private int id;
    private String name;
    private String system_name;
    private String friendly_name;
    private String description;
    private String unit;
    @JsonIgnore
    private List<ServiceMethods> methods;
    @JsonIgnore
    private Service ParentService;
    
    public ServiceMetric()
    {
        this.methods=new ArrayList<>();
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
     * @return the friendly_name
     */
    public String getFriendly_name() {
        return friendly_name;
    }

    /**
     * @param friendly_name the friendly_name to set
     */
    public void setFriendly_name(String friendly_name) {
        this.friendly_name = friendly_name;
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
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the methods
     */
    public List<ServiceMethods> getMethods() {
        return methods;
    }

    /**
     * @param methods the methods to set
     */
    public void setMethods(List<ServiceMethods> methods) {
        this.methods = methods;
    }

    /**
     * @return the ParentService
     */
    public Service getParentService() {
        return ParentService;
    }

    /**
     * @param ParentService the ParentService to set
     */
    public void setParentService(Service ParentService) {
        this.ParentService = ParentService;
    }
}
