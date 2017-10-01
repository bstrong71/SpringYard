package com.example.customer.service;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("/all_customers")
    public String allCustomers(Model model) {
        model.addAttribute("customers", customerService.get());
        return "all_customers";
    }

    @RequestMapping("/add_customer")
    public String addCustomer(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName, @RequestParam(value="phone") String phone, @RequestParam(value="email") String email, Model model) {
        Customer customer = new Customer();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setPhone(phone);
        customer.setEmail(email);
        model.addAttribute(customer);

        return "all_customers";
    }

    @RequestMapping("/view_customer/{id}")
    public String viewCustomer(@PathVariable(value = "id") int customerId, Model model ) {

        model.addAttribute("customers", customerService.getById(customerId));
        return "view_customer";
    }
}
