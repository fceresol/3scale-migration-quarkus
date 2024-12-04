/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services.proxies;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class ProxyMappingRules {

    private List<ProxyMappingRuleWrapper> mapping_rules;

    public ProxyMappingRules() {
        mapping_rules = new ArrayList<>();
    }

    

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.mapping_rules.forEach((ProxyMappingRuleWrapper pmrw) -> {
            builder.append(pmrw.toString());
            builder.append(",");
        });
        return builder.toString();
    }

    /**
     * @return the mapping_rules
     */
    public List<ProxyMappingRuleWrapper> getMapping_rules() {
        return mapping_rules;
    }

    /**
     * @param mapping_rules the mapping_rules to set
     */
    public void setMapping_rules(List<ProxyMappingRuleWrapper> mapping_rules) {
        this.mapping_rules = mapping_rules;
    }

}
