package com.muchiri.jakarta.mvc.controller;

import jakarta.inject.Named;
import jakarta.mvc.RedirectScoped;
import java.io.Serializable;

/**
 *
 * @author muchiri
 */
@Named("flashMessage")
@RedirectScoped
public class AlertMessage implements Serializable {

    private int code;
    private String message;
    private Type type;

    public AlertMessage() {
    }

    public AlertMessage(String message, Type type) {
        this.message = message;
        this.type = type;
    }

    public AlertMessage(int code, String message, Type type) {
        this.code = code;
        this.message = message;
        this.type = type;
    }

    public void notify(Type type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        success, warning, danger, info;
    }
}
