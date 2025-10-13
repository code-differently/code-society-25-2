/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.codedifferently.lesson25.repository;

import com.codedifferently.lesson25.models.LibraryUserModel;
import org.springframework.data.repository.CrudRepository;

/**
 * @author vscode
 */
public interface LibraryUserRepository extends CrudRepository<LibraryUserModel, String> {}
