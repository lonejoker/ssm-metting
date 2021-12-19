package com.xiaobai.controller;

import com.xiaobai.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:43
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName ApproveaccountController
 */
@Controller
@RequestMapping("/admin")
public class ApproveaccountController {

    @Autowired
    private EmployeeService employeeService;

    //待批准状态码为0
    public static final Integer PENDING_APPROVE = 0;

    @RequestMapping("/approveaccount")
    public String approveaccount(Model model) {
        model.addAttribute("emps", employeeService.getAllEmpsByStaus(PENDING_APPROVE));
        return "approveaccount";
    }

    @RequestMapping("/updatestatus")
    public String updateStatus(Integer employeeid, Integer status) {
        employeeService.updateStatus(employeeid, status);
        return "redirect:/admin/approveaccount";
    }
}
