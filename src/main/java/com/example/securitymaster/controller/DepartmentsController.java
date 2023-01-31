package com.example.securitymaster.controller;


import com.example.securitymaster.dao.DepartmentDao;
import com.example.securitymaster.ds.Department;
import com.example.securitymaster.security.annotation.departments.DepartmentsCreate;
import com.example.securitymaster.security.annotation.departments.DepartmentsDelete;
import com.example.securitymaster.security.annotation.departments.DepartmentsRead;
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
public class DepartmentsController {

    @Autowired
    private DepartmentDao departmentsDao;

    @DepartmentsRead
    @GetMapping("/departments")
    public ModelAndView index() {
        return new ModelAndView("departments", "departments", departmentsDao.findAll());
    }

    @DepartmentsCreate
    @GetMapping("/departments/create")
    public ModelAndView create() {
        return new ModelAndView("department-create", "department", new Department());
    }

    @DepartmentsCreate
    @PostMapping("/departments/create")
    public String create(@ModelAttribute @Valid Department department, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "department-create";
        } else {
            departmentsDao.save(department);

            return "redirect:/departments";
        }
    }

    @DepartmentsDelete
    @GetMapping("/departments/delete/{id}")
    public String delete(@PathVariable Integer id) {
        departmentsDao.deleteById(id);

        return "redirect:/departments";
    }
}
