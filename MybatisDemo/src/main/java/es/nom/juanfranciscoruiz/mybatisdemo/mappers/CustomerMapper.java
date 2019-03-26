/*
 * Para uso interno.
 * Proyecto de aprendizaje y pruebas
 */
package es.nom.juanfranciscoruiz.mybatisdemo.mappers;

import es.nom.juanfranciscoruiz.mybatisdemo.model.Customer;
import java.util.List;

/**
 *
 * @author hamfree
 */
public interface CustomerMapper {
    public List<Customer> selectCustomerById(Long id);

    public List<Customer> selectAllCustomers();

    public void insertCustomer(Customer c);
}
