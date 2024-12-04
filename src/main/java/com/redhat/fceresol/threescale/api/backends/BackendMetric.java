/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.backends;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author fceresol
 */
public class BackendMetric {

    private int id;
    private String system_name;
    private String friendly_name;
    private String description;
    private String unit;
    @JsonIgnore
    private Backend parentBackend;

    void setParentBackend(Backend backend) {
        this.parentBackend = backend;
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
     * @return the parentBackend
     */
    public Backend getParentBackend() {
        return parentBackend;
    }

    @Override
    public String toString() {
        return "BackendMetric{" + "id=" + id + ", system_name=" + system_name + ", friendly_name=" + friendly_name + ", description=" + description + ", unit=" + unit + ", parentBackend=" + parentBackend.getId() + '}';
    }

}
