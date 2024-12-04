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
public class BackendMappingRules {
    private List<BackendMappingRuleWrapper> mapping_rules;
    
    public BackendMappingRules()
    {
        this.mapping_rules = new ArrayList<>();
    }
    /**
     * @return the mapping_rules
     */
    public List<BackendMappingRuleWrapper> getMapping_rules() {
        return mapping_rules;
    }

    /**
     * @param mapping_rules the mapping_rules to set
     */
    public void setMapping_rules(List<BackendMappingRuleWrapper> mapping_rules) {
        this.mapping_rules = mapping_rules;
    }
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        this.mapping_rules.forEach((BackendMappingRuleWrapper bemrw) -> {builder.append(bemrw.toString()); builder.append(",");} );
        return builder.toString();
    }
}
