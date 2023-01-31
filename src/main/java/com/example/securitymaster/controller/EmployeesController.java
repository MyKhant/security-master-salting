package com.example.securitymaster.controller;

import com.example.securitymaster.dao.EmployeeDao;
import com.example.securitymaster.ds.Employee;
import com.example.securitymaster.security.annotation.employees.EmployeesCreate;
import com.example.securitymaster.security.annotation.employees.EmployeesDelete;
import com.example.securitymaster.security.annotation.employees.EmployeesRead;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class EmployeesController {

    @Autowired
    private EmployeeDao employeesDao;

    @EmployeesRead
    @GetMapping("/employees")
    public ModelAndView index() {
        return new ModelAndView("employees", "employees", employeesDao.findAll());
    }

    @EmployeesCreate
    @GetMapping("/employees/create")
    public ModelAndView create() {
        return new ModelAndView("employee-create", "employee", new Employee());
    }

    @EmployeesCreate
    @PostMapping("/employees/create")
    public String create(@ModelAttribute @Valid Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "employee-create";
        } else {
            employeesDao.save(employee);

            return "redirect:/employees";
        }
    }

    @EmployeesDelete
    @GetMapping("/employees/delete/{id}")
    public String delete(@PathVariable Integer id) {
        employeesDao.deleteById(id);

        return "redirect:/employees";
    }
}
