/*
 * Para uso interno.
 * Proyecto de aprendizaje y pruebas
 */
package es.nom.juanfranciscoruiz.mybatisdemo.service;

import es.nom.juanfranciscoruiz.mybatisdemo.app.App;
import es.nom.juanfranciscoruiz.mybatisdemo.mappers.CustomerMapper;
import es.nom.juanfranciscoruiz.mybatisdemo.model.Customer;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
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
public class CustomerServiceImpl implements CustomerService {

    private SqlSessionFactory sqlSessionFactory;
    private SqlSession session;
    private CustomerMapper mapper;

    public CustomerServiceImpl(String configuration) {
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(configuration);
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
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

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public SqlSession getSession() {
        return session;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }

    public CustomerMapper getMapper() {
        return mapper;
    }

    public void setMapper(CustomerMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Customer> selectCustomerById(Long id_customer) {
        this.session = sqlSessionFactory.openSession();
        try {
            setMapper(session.getMapper(CustomerMapper.class));
            List<Customer> customers = mapper.selectCustomerById(id_customer);
            return customers;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Customer> selectAllCustomers() {
        this.session = sqlSessionFactory.openSession();
        try {
            setMapper(session.getMapper(CustomerMapper.class));
            List<Customer> customers = mapper.selectAllCustomers();
            return customers;
        } finally {
            session.close();
        }
    }

    @Override
    public void insertCostumer(Customer customer) {
        this.session = sqlSessionFactory.openSession();
        try {
            setMapper(session.getMapper(CustomerMapper.class));
            mapper.insertCustomer(customer);
            session.commit();
        } finally {
            session.close();
        }
    }

}
