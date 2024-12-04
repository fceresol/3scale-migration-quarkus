/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.backends;

/**
 *
 * @author fceresol
 */
public class BackendMappingRuleWrapper {
    private BackendMappingRule mapping_rule;

    /**
     * @return the mapping_rule
     */
    public BackendMappingRule getMapping_rule() {
        return mapping_rule;
    }

    /**
     * @param mapping_rule the mapping_rule to set
     */
    public void setMapping_rule(BackendMappingRule mapping_rule) {
        this.mapping_rule = mapping_rule;
    }
    
    @Override
    public String toString()
    {
        return this.mapping_rule.toString();
    }
}
