/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.redhat.fceresol.threescale.api.backends.Backend;

/**
 *
 * @author fceresol
 */
public class ServiceBackendUsage {

    private int id;
    private String path;
    private int service_id;
    private int backend_id;
    @JsonIgnore
    private Backend backend;
    @JsonIgnore
    private Service service;

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
     * @return the path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path the path to set
     */
    public void setPath(String path) {
        this.path = path;
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
     * @return the backend_id
     */
    public int getBackend_id() {
        return backend_id;
    }

    /**
     * @param backend_id the backend_id to set
     */
    public void setBackend_id(int backend_id) {
        this.backend_id = backend_id;
    }

    /**
     * @return the backend
     */
    public Backend getBackend() {
        return backend;
    }

    /**
     * @param backend the backend to set
     */
    public void setBackend(Backend backend) {
        this.backend = backend;
    }

    /**
     * @return the service
     */
    public Service getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(Service service) {
        this.service = service;
    }
}
