/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.redhat.fceresol.threescale.api.accounts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fceresol
 */
public class Users {
    private List<UserWrapper> users;
    public Users()
    {
        this.users = new ArrayList<>();
                
    }

    /**
     * @return the users
     */
    public List<UserWrapper> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<UserWrapper> users) {
        this.users = users;
    }
    
}
