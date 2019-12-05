/*
 * Para uso interno.
 * Proyecto de aprendizaje y pruebas
 */
package es.nom.juanfranciscoruiz.mybatisdemo.app;

import es.nom.juanfranciscoruiz.mybatisdemo.model.Customer;
import es.nom.juanfranciscoruiz.mybatisdemo.service.CustomerService;
import es.nom.juanfranciscoruiz.mybatisdemo.service.CustomerServiceImpl;
import es.nom.juanfranciscoruiz.mybatisdemo.util.IO;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 *
 * @author hamfree
 */
public class App {

    public static void main(String args[]) {
        App app = new App();
        app.ejecuta();
        System.exit(0);
    }

    public void ejecuta() {
        String resource = "mybatis-config.xml";

        CustomerService customerService = new CustomerServiceImpl(resource);

        IO.prtln(false, 2, "Se van a insertar 100 objetos Customers...");
        for (int i = 1; i < 100; i++) {
            Customer c = new Customer();
            c.setAddress(String.valueOf(i * i));
            c.setName(new UUID(new Random().nextLong(), new Random().nextLong()).toString());
            c.setWebsite(String.valueOf(Math.pow(i, i)));
            c.setCredit_limit(BigDecimal.valueOf(new Double(i * 2)));
            customerService.insertCostumer(c);
        }

        IO.prtln(false, 2, "Despues del insert...");

        List<Customer> customers = customerService.selectAllCustomers();

        muestraTodosLosClientes(customers);

    }

    void muestraUnCliente(Customer customer) {
        if (customer != null) {
            IO.prtln(false, 2, customer.toString());
        }
    }

    void muestraTodosLosClientes(List<Customer> customers) {
        if (customers != null && customers.size() > 0) {
            for (Customer c : customers) {
                IO.prtln(false, 1, c.toString());
            }
        }
    }
}
