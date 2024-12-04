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
public class Services {

    private List<ServiceWrapper> services;

    public Services() {
        services = new ArrayList<>();
    }

    /**
     * @return the services
     */
    public List<ServiceWrapper> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<ServiceWrapper> services) {
        this.services = services;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.services.forEach((ServiceWrapper sw) -> {
            builder.append(sw.toString());
            builder.append(",");
        });
        return builder.toString();
    }

}
