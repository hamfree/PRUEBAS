/*
 * Para uso interno.
 * Proyecto de aprendizaje y pruebas
 */
package es.nom.juanfranciscoruiz.mybatisdemo.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author hamfree
 */
public class Customer {

    private int customer_id;
    private String name;
    private String address;
    private String website;
    private BigDecimal credit_limit;

    public Customer() {
    }

    public Customer(int customer_id, String name, String address, String website, BigDecimal credit_limit) {
        this.customer_id = customer_id;
        this.name = name;
        this.address = address;
        this.website = website;
        this.credit_limit = credit_limit;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public BigDecimal getCredit_limit() {
        return credit_limit;
    }

    public void setCredit_limit(BigDecimal credit_limit) {
        this.credit_limit = credit_limit;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.customer_id);
        hash = 59 * hash + Objects.hashCode(this.name);
        hash = 59 * hash + Objects.hashCode(this.address);
        hash = 59 * hash + Objects.hashCode(this.website);
        hash = 59 * hash + Objects.hashCode(this.credit_limit);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.website, other.website)) {
            return false;
        }
        if (!Objects.equals(this.customer_id, other.customer_id)) {
            return false;
        }
        if (!Objects.equals(this.credit_limit, other.credit_limit)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", name=" + name + ", address=" + address + ", website=" + website + ", credit_limit=" + credit_limit + '}';
    }

}
