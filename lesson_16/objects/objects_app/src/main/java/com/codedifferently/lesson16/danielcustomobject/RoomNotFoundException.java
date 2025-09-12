/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package com.codedifferently.lesson16.danielcustomobject;

/**
 *
 * @author vscode
 */
public class RoomNotFoundException extends RuntimeException {

    /**
     * Creates a new instance of <code>RoomNotFoundException</code> without detail message.
     */
    public RoomNotFoundException() {
    }


    /**
     * Constructs an instance of <code>RoomNotFoundException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public RoomNotFoundException(String msg) {
        super(msg);
    }
}
