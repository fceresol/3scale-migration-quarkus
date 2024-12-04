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
public class ServiceMetrics {

    private List<ServiceMetricWrapper> metrics;

    public ServiceMetrics() {
        metrics = new ArrayList<>();
    }

    /**
     * @return the metrics
     */
    public List<ServiceMetricWrapper> getMetrics() {
        return metrics;
    }

    /**
     * @param metrics the metrics to set
     */
    public void setMetrics(List<ServiceMetricWrapper> metrics) {
        this.metrics = metrics;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.metrics.forEach((ServiceMetricWrapper smw) -> {
            builder.append(smw.toString());
            builder.append(",");
        });
        return builder.toString();
    }

}
