/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */
package com.redhat.fceresol.exceptions;

/**
 *
 * @author fceresol
 */
public class ObjectCreationException extends Exception {

    /**
     * Creates a new instance of <code>ObjectCreationException</code> without detail
     * message.
     */
    public ObjectCreationException() {
    }

    /**
     * Constructs an instance of <code>ObjectCreationException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public ObjectCreationException(String msg) {
        super(msg);
    }
}
