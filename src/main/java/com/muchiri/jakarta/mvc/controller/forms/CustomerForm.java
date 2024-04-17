package com.muchiri.jakarta.mvc.controller.forms;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

/**
 *
 * @author muchiri
 */
public class CustomerForm implements Serializable {

    @MvcBinding
    @FormParam("name")
    @NotBlank(message = "field is required")
    String name;

    @MvcBinding
    @FormParam("email")
    @NotBlank(message = "field is required")
    @Email(message = "invalid email address")
    String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void clear() {
        this.name = "";
        this.email = "";
    }

}
