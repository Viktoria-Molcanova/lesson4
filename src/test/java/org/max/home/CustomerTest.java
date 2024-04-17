package org.max.home;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class CustomerTest {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    @BeforeEach
    public void setup() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @Test
    public void testCreateCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId((short) 1);
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setPhoneNumber("1234567890");
        customer.setDistrict("Central");
        customer.setStreet("Main Street");
        customer.setHouse("123");
        customer.setApartment("4A");
        customer.setPaymentMethod("Credit Card");
        customer.setCredit(true);
        customer.setCarBrand("Toyota");

        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();

        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId");
        query.setParameter("customerId", (short) 1);
        Customer createdCustomer = (Customer) query.getSingleResult();

        Assertions.assertEquals(customer, createdCustomer);
    }

    @Test
    public void testReadCustomer() {
        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId");
        query.setParameter("customerId", (short) 1);
        Customer customer = (Customer) query.getSingleResult();

        Assertions.assertNotNull(customer);
    }

    @Test
    public void testUpdateCustomer() {
        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId");
        query.setParameter("customerId", (short) 1);
        Customer customer = (Customer) query.getSingleResult();

        customer.setFirstName("Jane");
        customer.setLastName("Smith");

        entityManager.getTransaction().begin();
        entityManager.merge(customer);
        entityManager.getTransaction().commit();

        Customer updatedCustomer = (Customer) query.getSingleResult();

        Assertions.assertEquals("Jane", updatedCustomer.getFirstName());
        Assertions.assertEquals("Smith", updatedCustomer.getLastName());
    }

    @Test
    public void testDeleteCustomer() {
        Query query = entityManager.createQuery("SELECT c FROM Customer c WHERE c.customerId = :customerId");
        query.setParameter("customerId", (short) 1);
        Customer customer = (Customer) query.getSingleResult();

        entityManager.getTransaction().begin();
        entityManager.remove(customer);
        entityManager.getTransaction().commit();

        List<Customer> customers = entityManager.createQuery("SELECT c FROM Customer c").getResultList();

        Assertions.assertTrue(customers.isEmpty());
    }
}
