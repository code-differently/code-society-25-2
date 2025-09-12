/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codedifferently.lesson16.danielcustomobject;

import java.util.List;

/**
 *
 * @author vscode
 */
public class Home {

    
    private HomeType homeType;
    private List<String> rooms;
    private Integer numberOfStories;
    private Double price;
    private String neighborhood;

    public Home(HomeType homeType,List<String> rooms,Integer numberOfStories,Double price,String neighborhood) {
        this.homeType = homeType;
        this.rooms = rooms;
        this.numberOfStories = numberOfStories;
        this.price = price;
        this.neighborhood = neighborhood;
    }


}
