package com.muchiri.jakarta.mvc.controller;

import com.muchiri.jakarta.mvc.controller.forms.LoginForm;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.security.CsrfProtected;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author muchiri
 */
@Path("")
@Controller
@RequestScoped
public class LoginController {

    @Inject
    private BindingResult validationResult;
    @Inject
    private Models models;
    @Context
    private HttpServletRequest request;
    @Context
    private HttpServletResponse response;
    @Inject
    private SecurityContext securityContext;

    @GET
    @Path("login")
    @View("login")
    public void login() {
    }

    @POST
    @Path("login")
    @CsrfProtected
    public Response login(@BeanParam @Valid LoginForm form) {
        if (validationResult.isFailed()) {
            Map<String, String> errors = new HashMap<>();
            validationResult.getAllErrors().forEach(e -> {
                System.out.format("param: %s, message: %s %n", e.getParamName(), e.getMessage());
                errors.put(e.getParamName(), e.getMessage());
            });

            models.put("errors", errors);
            models.put("form", form);
            return Response.status(Response.Status.BAD_REQUEST).entity("login").build();
        }

        switch (processAuthentication(form.getUsername(), form.getPassword())) {
            case SEND_CONTINUE:
                break;
            case SEND_FAILURE:
                models.put("auth", "Invalid credentials");
                models.put("form", form);
                return Response.status(Response.Status.BAD_REQUEST).entity("login").build();
            case SUCCESS:
                return Response.ok("redirect:customers").build();
        }
        System.err.println("something went wrong");
        return Response.status(Response.Status.BAD_REQUEST).entity("login").build();
    }

    @GET
    @Path("logout")
    public Response logout() {
        try {
            request.logout();
            return Response.ok("redirect:login").build();
        } catch (ServletException ex) {
            System.err.println(ex);
            return Response.serverError().build();
        }
    }

    private AuthenticationStatus processAuthentication(String username, String password) {
        return securityContext.authenticate(
                request,
                response,
                AuthenticationParameters.withParams().credential(new UsernamePasswordCredential(username, password))
        );
    }
}
