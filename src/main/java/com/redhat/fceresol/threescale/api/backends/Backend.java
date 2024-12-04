package com.redhat.fceresol.threescale.api.backends;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;


import jakarta.json.JsonObject;

public class Backend{
    private int id;
    private String name;
    private String system_name;
    private String description;
    private String private_endpoint;
    @JsonIgnore
    private List<BackendMetric> metrics;
    @JsonIgnore
    private List<BackendMappingRule> mapping_rules;

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getSystem_name()
    {
        return system_name;
    }

    public String getDescription()
    {
        return description;
    }

    public String getPrivate_endpoint()
    {
        return private_endpoint;
    }

    public List<BackendMetric> getMetrics()
    {
        return metrics;
    }

    public List<BackendMappingRule> getMapping_rules()
    {
        return mapping_rules;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setName(String name)
    {
        this.name= name;
    }

    public void setSystem_name(String system_name)
    {
        this.system_name= system_name;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setPrivate_endpoint(String private_endpoint)
    {
        this.private_endpoint = private_endpoint;
    }

    public void setMetrics(List<BackendMetric>metrics)
    {
        this.metrics = metrics;
    }

    public void setMapping_rules(List<BackendMappingRule>mapping_rules)
    {
        this.mapping_rules = mapping_rules;
    }


    public void addMetric(BackendMetric metric)
    {
        metric.setParentBackend(this);
        this.metrics.add(metric);
    }

    
    public void addMapping_rule(BackendMappingRule mapping_rule)
    {
        mapping_rule.setParentBackend(this);
        this.mapping_rules.add(mapping_rule);
    }

    
    public Backend()
    {
        this.id = 0;
        this.name = null;
        this.system_name = null;
        this.description = null;
        this.private_endpoint = null;
        this.metrics = new ArrayList<>();
        this.mapping_rules = new ArrayList<>();
    }

    public Backend (JsonObject object)
    {
        this();
        this.id = object.getInt("id", 0);
        this.name = object.getString("name", null);
        this.system_name = object.getString("system_name", null);
        this.description = object.getString("description", null);
        this.private_endpoint = object.getString("private_endpoint", null);
        this.metrics = new ArrayList<>();
        this.mapping_rules = new ArrayList<>();
        
    }
    
    public Backend(int id, String name, String system_name, String description, String private_endpoint,
            List<BackendMetric> metrics, List<BackendMappingRule> mapping_rules) {
        this.id = id;
        this.name = name;
        this.system_name = system_name;
        this.description = description;
        this.private_endpoint = private_endpoint;
        this.metrics = metrics;
        this.mapping_rules = mapping_rules;
    }

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder("{Backend:{id: " );
        builder.append(id);
        builder.append(",name: ");
        builder.append(name);
        builder.append(",system_name: ");
        builder.append(system_name);
        builder.append(",description: ");
        builder.append(description);
        builder.append(",private_endpoint: ");
        builder.append(private_endpoint);
        builder.append(",metrics: [");
        // TODO metrics + mapping rules missing
        metrics.forEach((BackendMetric metric) -> { builder.append(metric.toString());});
        builder.append("], mapping_rules: [");
        mapping_rules.forEach((BackendMappingRule mapping_rule) -> { builder.append(mapping_rule.toString());});
        builder.append("]}");
       return builder.toString();
    }

}
