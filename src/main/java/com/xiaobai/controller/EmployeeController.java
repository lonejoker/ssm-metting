package com.xiaobai.controller;

import com.xiaobai.entity.Employee;
import com.xiaobai.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:45
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName EmployeeController
 */
@RequestMapping("/admin")
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    public static final Integer PAGE_SIZE = 10;

    @RequestMapping("/searchemployees")
    public String getAllEmployees(Employee employee, @RequestParam(defaultValue = "1") Integer page, Model model) {
        List<Employee> emps = employeeService.getAllEmps(employee, page, PAGE_SIZE);
        Long total = employeeService.getTotal(employee);
        model.addAttribute("emps", emps);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("pagenum", total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1);
        return "searchemployees";
    }

    @RequestMapping("/updateemp")
    public String updateemp(Integer id) {
        employeeService.updateStatus(id, 2);
        return "redirect:/admin/searchemployees?status=1";
    }
}
