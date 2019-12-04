/*
 * Para uso interno.
 * Proyecto de aprendizaje y pruebas
 */
package es.nom.juanfranciscoruiz.mybatisdemo.app;

import es.nom.juanfranciscoruiz.mybatisdemo.mappers.CustomerMapper;
import es.nom.juanfranciscoruiz.mybatisdemo.model.Customer;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

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
        InputStream inputStream = null;
        try {
            String resource = "mybatis-config.xml";
            inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            SqlSession session = sqlSessionFactory.openSession();
            try {
                CustomerMapper mapper = session.getMapper(CustomerMapper.class);

                System.out.println("");
                for (int i = 1; i < 1000; i++) {
                    Customer c = new Customer();
                    c.setAddress(String.valueOf(i * i));
                    c.setName(new UUID(new Random().nextLong(), new Random().nextLong()).toString());
                    c.setWebsite(String.valueOf(Math.pow(i, i)));
                    c.setCredit_limit(BigDecimal.valueOf(new Double(i * 2)));
                    System.out.println(c.toString());
                    mapper.insertCustomer(c);
                }

                session.commit();

                System.out.println("");
                System.out.println("Despues del insert...");
                System.out.println("");

                muestraTodosLosClientes(mapper);

            } finally {
                session.close();
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                inputStream.close();
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void muestraUnCliente(CustomerMapper mapper, Long id) {
        List<Customer> customers = mapper.selectCustomerById(id);
        if (customers != null && customers.size() > 0) {
            for (Customer c : customers) {
                System.out.println(c.toString());
            }
        }

    }

    void muestraTodosLosClientes(CustomerMapper mapper) {
        List<Customer> allcustomers = mapper.selectAllCustomers();
        if (allcustomers != null && allcustomers.size() > 0) {
            for (Customer c : allcustomers) {
                System.out.println(c.toString());
            }
        }
    }
}
