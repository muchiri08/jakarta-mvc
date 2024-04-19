package com.muchiri.jakarta.mvc.service;

import com.muchiri.jakarta.mvc.controller.forms.CustomerForm;
import com.muchiri.jakarta.mvc.model.Customer;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author muchiri
 */
public class CustomerService {

    @PersistenceContext(unitName = "test")
    private EntityManager em;

    public void save(CustomerForm form) {
        try {
            Customer customer = new Customer();
            customer.name = form.getName();
            customer.email = form.getEmail();
            customer.createdAt = LocalDateTime.now();

            em.persist(customer);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException ex) {
            System.err.format("error saving customer: message => %s %n", ex.getMessage());
        }
    }

    public List<Customer> findAll() {
        try {
            TypedQuery tq = em.createNamedQuery("Customer.findAll", Customer.class);
            return tq.getResultList();
        } catch (PersistenceException ex) {
            System.err.format("error fetching customers: message => %s %n", ex.getMessage());
            return Collections.EMPTY_LIST;
        }
    }
}
