/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.redhat.fceresol.exceptions;

/**
 *
 * @author fceresol
 */
public class LoadException extends Exception {

    /**
     * Creates a new instance of <code>LoadException</code> without detail
     * message.
     */
    public LoadException() {
    }

    /**
     * Constructs an instance of <code>LoadException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LoadException(String msg) {
        super(msg);
    }
}
