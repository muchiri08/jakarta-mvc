package com.muchiri.jakarta.mvc.controller.forms;

import jakarta.mvc.binding.MvcBinding;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.FormParam;
import java.io.Serializable;

/**
 *
 * @author muchiri
 */
public class LoginForm implements Serializable {

    @MvcBinding
    @FormParam("username")
    @NotBlank(message = "field required")
    String username;
    @MvcBinding
    @FormParam("password")
    @NotBlank(message = "field required")
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
