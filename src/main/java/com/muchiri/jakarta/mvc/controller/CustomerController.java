package com.muchiri.jakarta.mvc.controller;

import com.muchiri.jakarta.mvc.controller.forms.CustomerForm;
import com.muchiri.jakarta.mvc.service.CustomerService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.security.CsrfProtected;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author muchiri
 */
@Path("customers")
@Controller
@RequestScoped
public class CustomerController {
    
    @Inject
    private BindingResult validationResult;
    @Inject
    private Models models;
    @Inject
    private AlertMessage alert;
    @Inject
    private CustomerService customerService;
    
    @GET
    @View("customers")
    public void customers() {
    }
    
    @GET
    @Path("new")
    @View("addcustomer")
    public void addCustomerView() {
        customerService.findAll().stream().forEach(System.out::println);
    }
    
    @POST
    @Path("new")
    @CsrfProtected
    public Response addCustomer(@BeanParam @Valid CustomerForm custForm) {
        System.out.format("Name: %s, Email: %s %n", custForm.getName(), custForm.getEmail());
        if (validationResult.isFailed()) {
            Map<String, String> errors = new HashMap<>();
            validationResult.getAllErrors().forEach(e -> {
                System.out.format("param: %s, message: %s %n", e.getParamName(), e.getMessage());
                errors.put(e.getParamName(), e.getMessage());
            });
            
            models.put("errors", errors);
            models.put("form", custForm);
            return Response.status(Response.Status.BAD_REQUEST).entity("addcustomer").build();
        }
        //processing skipped
        customerService.save(custForm);
        //AlertMessage alert = new AlertMessage();
        alert.notify(AlertMessage.Type.success, "Customer created successfully.");
        return Response.status(Response.Status.CREATED).entity("redirect:customers").build();
    }
}
