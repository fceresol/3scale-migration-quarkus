/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.backends;

/**
 *
 * @author fceresol
 */
public class BackendMetricWrapper {
    private BackendMetric metric;

    /**
     * @return the metric
     */
    public BackendMetric getMetric() {
        return metric;
    }

    /**
     * @param metric the metric to set
     */
    public void setMetric(BackendMetric metric) {
        this.metric = metric;
    }
    
    @Override
    public String toString()
    {
        return metric.toString();
    }
}
