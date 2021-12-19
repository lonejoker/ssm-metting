package com.xiaobai.controller;

import com.xiaobai.entity.Department;
import com.xiaobai.entity.Employee;
import com.xiaobai.entity.UsernamePassWord;
import com.xiaobai.service.DepartmentService;
import com.xiaobai.service.EmployeeService;
import com.xiaobai.service.UsernamePassWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:46
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName LoginController
 */
@Controller
public class LoginController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UsernamePassWordService usernamePassWordService;

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, Model model, HttpSession httpSession) {
        Employee employee = employeeService.doLogin(username, password);
        if (employee == null) {
            model.addAttribute("error", "用户名或密码错误,请重新登录");
            return "forward:/";
        } else {
            if (employee.getStatus() == 0) {
                model.addAttribute("error", "用户待审核");
                return "forward:/";
            } else if (employee.getStatus() == 2) {
                model.addAttribute("error", "审核未通过");
                return "forward:/";
            } else {
                httpSession.setAttribute("currentuser", employee);
                return "redirect:/notifications";
            }
        }
    }

    @RequestMapping("/register")
    public String register(Model model) {
        List<Department> deps = departmentService.getAllDeps();
        model.addAttribute("deps", deps);
        return "register";
    }

    @RequestMapping("/doReg")
    public String doReg(Employee employee, Model model) {
        int result = employeeService.doReg(employee);
        if (result == 1) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "注册失败");
            model.addAttribute("employee", employee);
            return "forward:/register";
        }
    }

    @RequestMapping("/changepassword")
    public String changepassword() {
        return "changepassword";
    }

    @RequestMapping("/changepw")
    public String changepw(UsernamePassWord up, HttpSession httpSession, Model model) {
        Employee currentuser = (Employee) httpSession.getAttribute("currentuser");
        String username = currentuser.getUsername();
        Integer result = usernamePassWordService.changepw(up, username);
        if (result == 1) {
            return "redirect:/";
        } else {
            model.addAttribute("error", "两次密码不一致");
            return "forward:/changepassword";
        }

    }
}
