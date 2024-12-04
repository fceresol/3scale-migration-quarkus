/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.backends;

/**
 *
 * @author fceresol
 */
public class BackendWrapper {
    private Backend backend_api;

    /**
     * @return the backend_api
     */
    public Backend getBackend_api() {
        return backend_api;
    }

    /**
     * @param backend_api the backend_api to set
     */
    public void setBackend_api(Backend backend_api) {
        this.backend_api = backend_api;
    }
    
    public String toString()
    {
        return this.backend_api.toString();
    }
}
