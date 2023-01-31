package com.example.securitymaster.controller;

import com.example.securitymaster.dao.CustomerDao;
import com.example.securitymaster.ds.Customer;
import com.example.securitymaster.security.annotation.customers.CustomerDelete;
import com.example.securitymaster.security.annotation.customers.CustomersCreate;
import com.example.securitymaster.security.annotation.customers.CustomersPagView;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerDao customerDao;

    @CustomersPagView
    @GetMapping("/customers")
    public ModelAndView findAllCustomers(){
        return new ModelAndView("customers",
                "customers",
                customerDao.findAll());
    }
    @CustomersCreate
    @GetMapping("/customer-form")
    public String customerForm(Model model){
        model.addAttribute("customer",new Customer());
        return "customer-form";
    }
    @CustomersCreate
    @PostMapping("/customer-form")
    public String saveCustomer(@Valid Customer customer,
                               BindingResult result){
        if(result.hasErrors()){
            return "customer-form";
        }
        customerDao.save(customer);
        return "redirect:/customer/customers";
    }

    @CustomerDelete
    @GetMapping("/customers/delete")
    public String deleteCustomer(@RequestParam("id")int id){
        if(customerDao.existsById(id)){
            customerDao.deleteById(id);
            return "redirect:/customer/customers";
        }
        else {
            throw new EntityNotFoundException(id + " Not Found!");
        }
    }

}
