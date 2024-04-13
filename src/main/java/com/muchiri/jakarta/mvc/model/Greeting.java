package com.muchiri.jakarta.mvc.model;

import jakarta.enterprise.context.RequestScoped;

/**
 *
 * @author muchiri
 */
public class Greeting {

    String name;

    public Greeting() {
    }

    public Greeting(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
