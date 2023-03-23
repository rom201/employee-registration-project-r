package com.cydo.controller;

import com.cydo.bootstrap.DataGenerator;
import com.cydo.model.Employee;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/register")
    public String createEmployee(Model model){

        model.addAttribute("employee", new Employee());
        model.addAttribute("states", DataGenerator.getAllStates());

        return "employee/employee-create";
    }

    @PostMapping("/list")
    public String employeeList(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult, Model model){

        //this st prevent to go in another page until user fix the data on the register page
        if(bindingResult.hasErrors()){

            //it still with same employee(we already have it here) on the page but need add states to select again if needed
            model.addAttribute("states", DataGenerator.getAllStates());

            return "employee/employee-create";
        }

        DataGenerator.saveEmployee(employee);

        model.addAttribute("employees", DataGenerator.readAllEmployee());


        return "employee/employee-list";
    }


}
