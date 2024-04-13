package com.muchiri.jakarta.mvc.controller;

import com.muchiri.jakarta.mvc.model.Greeting;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

/**
 *
 * @author muchiri
 */
@Path("hello")
@Controller
public class HelloController {

    @Inject
    private Models models;

    @GET
    @View("hello.xhtml")
    public void hello() {
        models.put("greeting", new Greeting("Kennedy"));
    }
}
