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
public class BackendMappingRule {

    
    private int id;
    private int metric_id;
    private String pattern;
    private String http_method;
    private int delta;
    private int position;
    private boolean last;
    @JsonIgnore
    private BackendMetric metric;
    @JsonIgnore
    private Backend parentBackend;

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
     * @return the metric_id
     */
    public int getMetric_id() {
        return metric_id;
    }

    /**
     * @param metric_id the metric_id to set
     */
    public void setMetric_id(int metric_id) {
        this.metric_id = metric_id;
    }

    /**
     * @return the pattern
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * @param pattern the pattern to set
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * @return the http_method
     */
    public String getHttp_method() {
        return http_method;
    }

    /**
     * @param http_method the http_method to set
     */
    public void setHttp_method(String http_method) {
        this.http_method = http_method;
    }

    /**
     * @return the delta
     */
    public int getDelta() {
        return delta;
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(int delta) {
        this.delta = delta;
    }

    /**
     * @return the position
     */
    public int getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(int position) {
        this.position = position;
    }

    /**
     * @return the last
     */
    public boolean isLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(boolean last) {
        this.last = last;
    }

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

    
    void setParentBackend(Backend parentBackend) {
        this.parentBackend = parentBackend;
    }

    /**
     * @return the parentBackend
     */
    public Backend getParentBackend() {
        return parentBackend;
    }
    
}
