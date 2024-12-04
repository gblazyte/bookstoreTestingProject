package test;

import beans.example.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void setUp() {
        customer = new Customer("John Doe", "john.doe@example.com");
    }

    @Test
    void testCustomerConstructor() {
        // Test that the constructor correctly initializes the object
        assertEquals("John Doe", customer.getName());
        assertEquals("john.doe@example.com", customer.getEmail());
    }

    @Test
    void testSetName() {
        // Test the setName method
        customer.setName("Jane Doe");
        assertEquals("Jane Doe", customer.getName());
    }

    @Test
    void testSetEmail() {
        // Test the setEmail method
        customer.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", customer.getEmail());
    }

    @Test
    void testGetName() {
        // Test the getName method
        assertEquals("John Doe", customer.getName());
    }

    @Test
    void testGetEmail() {
        // Test the getEmail method
        assertEquals("john.doe@example.com", customer.getEmail());
    }

    @Test
    void testSetNameNull() {
        // Test setting name to null
        customer.setName(null);
        assertNull(customer.getName());
    }

    @Test
    void testSetEmailNull() {
        // Test setting email to null
        customer.setEmail(null);
        assertNull(customer.getEmail());
    }
}
