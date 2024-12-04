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
public class Accounts {
    private List<AccountWrapper> accounts;
    public Accounts()
    {
        this.accounts = new ArrayList<>();
    }

    /**
     * @return the accounts
     */
    public List<AccountWrapper> getAccounts() {
        return accounts;
    }

    /**
     * @param accounts the accounts to set
     */
    public void setAccounts(List<AccountWrapper> accounts) {
        this.accounts = accounts;
    }
    
}
