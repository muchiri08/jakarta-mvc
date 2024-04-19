package com.muchiri.jakarta.mvc.model;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import java.time.LocalDateTime;

/**
 *
 * @author muchiri
 */
@Entity
@Access(AccessType.FIELD)
@NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer id;
    public String name;
    public String email;
    public LocalDateTime createdAt;

    @Override
    public String toString() {
        return "Customer{" + "id=" + id + ", name=" + name + ", email=" + email + ", createdAt=" + createdAt + '}';
    }

}
