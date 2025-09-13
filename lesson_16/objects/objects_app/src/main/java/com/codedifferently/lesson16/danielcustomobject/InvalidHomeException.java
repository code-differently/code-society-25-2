/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Exception.java to edit this template
 */

package com.codedifferently.lesson16.danielcustomobject;

/**
 * @author vscode
 */
public class InvalidHomeException extends IllegalArgumentException {

  /** Creates a new instance of <code>InvalidHomeException</code> without detail message. */
  public InvalidHomeException() {}

  /**
   * Constructs an instance of <code>InvalidHomeException</code> with the specified detail message.
   *
   * @param msg the detail message.
   */
  public InvalidHomeException(String msg) {
    super(msg);
  }
}
