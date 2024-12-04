/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class ServiceMetricMethods {

    private List<ServiceMetricMethodWrapper> backend_apis;

    public ServiceMetricMethods() {
        backend_apis = new ArrayList<>();
    }

    /**
     * @return the backend_apis
     */
    public List<ServiceMetricMethodWrapper> getBackend_apis() {
        return backend_apis;
    }

    /**
     * @param backend_apis the backend_apis to set
     */
    public void setBackend_apis(List<ServiceMetricMethodWrapper> backend_apis) {
        this.backend_apis = backend_apis;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.backend_apis.forEach((ServiceMetricMethodWrapper bew) -> {
            builder.append(bew.toString());
            builder.append(",");
        });
        return builder.toString();
    }

}
