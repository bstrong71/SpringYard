package com.example.customer.service;

import com.example.customer.model.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    CustomerService customerService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddGet() throws Exception{
        System.out.println("Add testCustomer to database");
        Customer expected = createTestCustomer();
        try {
            customerService.add(expected);
            System.out.println("Completed adding testCustomer to database.");
            assertTrue(true);
        }
        catch (IllegalArgumentException ex) {
            fail("Shouldn't reach this line");
        }

        // test getting all customers from database
        System.out.println("Getting all testCustomers from database");
        List<Customer> customers = customerService.get();
        System.out.println("Done");
        System.out.println("Getting expected customer...");
        Customer receivedFromGet = customers.iterator().next();
        System.out.println("Done");
        System.out.println("Testing getting...");
        Assert.assertNotNull(receivedFromGet);
        Assert.assertEquals(receivedFromGet.getFirstName(), expected.getFirstName());
        Assert.assertEquals(receivedFromGet.getLastName(), expected.getLastName());
        Assert.assertEquals(receivedFromGet.getPhone(), expected.getPhone());
        Assert.assertEquals(receivedFromGet.getEmail(), expected.getEmail());
        System.out.println("Test Passed.");

        // test getting customer by Id from database
        System.out.println("Test getting customer by Id using " + receivedFromGet);
        Customer receivedFromGetById = customerService.getById(receivedFromGet.getId());
        System.out.println("Found customer " + receivedFromGetById);
        Assert.assertNotNull(receivedFromGetById);
        Assert.assertEquals(receivedFromGetById.getFirstName(), receivedFromGet.getFirstName());
        Assert.assertEquals(receivedFromGetById.getLastName(), receivedFromGet.getLastName());
        Assert.assertEquals(receivedFromGetById.getPhone(), receivedFromGet.getPhone());
        Assert.assertEquals(receivedFromGetById.getEmail(), receivedFromGet.getEmail());
        System.out.println("Test Passed.");
    }

    // create test customer using time stamps for unique inputs
    private Customer createTestCustomer() throws InterruptedException{

        Customer testCustomer = new Customer();
        testCustomer.setFirstName(Long.toString(System.currentTimeMillis()));
        Thread.sleep(500);
        testCustomer.setLastName(Long.toString(System.currentTimeMillis()));
        Thread.sleep(500);
        testCustomer.setPhone(Long.toString(System.currentTimeMillis()));
        Thread.sleep(500);
        testCustomer.setEmail(Long.toString(System.currentTimeMillis()));

        return testCustomer;
    }
}
