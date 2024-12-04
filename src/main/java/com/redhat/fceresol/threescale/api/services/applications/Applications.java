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
public class Applications {

    private List<ApplicationWrapper> applications;

    public Applications() {
        applications = new ArrayList<>();
    }

    /**
     * @return the applications
     */
    public List<ApplicationWrapper> getApplications() {
        return applications;
    }

    /**
     * @param applications the applications to set
     */
    public void setApplications(List<ApplicationWrapper> applications) {
        this.applications = applications;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        this.applications.forEach((ApplicationWrapper aw) -> {
            builder.append(aw.toString());
            builder.append(",");
        });
        return builder.toString();
    }

}
