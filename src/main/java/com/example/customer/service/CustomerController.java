package com.example.customer.service;

import com.example.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(path="/all_customers", method = RequestMethod.GET)
    public String allCustomers(Model model) {
        model.addAttribute("customers", customerService.get());
        return "all_customers";
    }


}
