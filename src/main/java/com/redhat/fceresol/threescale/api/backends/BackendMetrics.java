/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.backends;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class BackendMetrics {
    private List<BackendMetricWrapper> metrics;
    public BackendMetrics()
    {
        metrics = new ArrayList<>();
    }

    /**
     * @return the metrics
     */
    public List<BackendMetricWrapper> getMetrics() {
        return metrics;
    }

    /**
     * @param metrics the metrics to set
     */
    public void setMetrics(List<BackendMetricWrapper> metrics) {
        this.metrics = metrics;
    }
    
        @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        this.metrics.forEach((BackendMetricWrapper bemw) -> {builder.append(bemw.toString()); builder.append(",");} );
        return builder.toString();
    }
}

