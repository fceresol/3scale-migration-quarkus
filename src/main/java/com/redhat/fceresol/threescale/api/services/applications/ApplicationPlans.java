/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.services.applications;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class ApplicationPlans {

    private List<ApplicationPlanWrapper> plans;

    public ApplicationPlans() {
        plans = new ArrayList<>();
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.getPlans().forEach((ApplicationPlanWrapper apw) -> {
            builder.append(apw.toString());
            builder.append(",");
        });
        return builder.toString();
    }

    /**
     * @return the plans
     */
    public List<ApplicationPlanWrapper> getPlans() {
        return plans;
    }

    /**
     * @param plans the plans to set
     */
    public void setPlans(List<ApplicationPlanWrapper> plans) {
        this.plans = plans;
    }

}
