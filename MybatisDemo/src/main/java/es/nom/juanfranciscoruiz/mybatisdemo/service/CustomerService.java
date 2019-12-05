/*
 * Para uso interno.
 * Proyecto de aprendizaje y pruebas
 */
package es.nom.juanfranciscoruiz.mybatisdemo.service;

import es.nom.juanfranciscoruiz.mybatisdemo.model.Customer;
import java.util.List;

/**
 *
 * @author hamfree
 */
public interface CustomerService {
    public List<Customer> selectCustomerById(Long id_customer);

    public List<Customer> selectAllCustomers();

    public void insertCostumer(Customer customer);
}
