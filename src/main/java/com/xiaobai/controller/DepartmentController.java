package com.xiaobai.controller;

import com.xiaobai.entity.Department;
import com.xiaobai.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:44
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName DepartmentController
 */
@Controller
@RequestMapping("/admin")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @RequestMapping("/departments")
    public String departments(Model model) {
        model.addAttribute("deps", departmentService.getAllDeps());
        return "departments";
    }

    @PostMapping("/addDepartments")
    public String addDepartments(String departmentname, Model model) {
        Department depByName = departmentService.getDepByName(departmentname);
        if (depByName != null) {
            model.addAttribute("error", "部门名字重复");
        } else {
            departmentService.addDepartments(departmentname);
        }
        return "redirect:/admin/departments";
    }

    @RequestMapping("/deletedep")
    public String deletedep(Integer departmentid) {
        departmentService.deletedepById(departmentid);
        return "redirect:/admin/departments";
    }

    @RequestMapping("/updatedep")
    @ResponseBody
    public String updatedep(Integer id, String name) {
        Integer result = departmentService.updatedep(id, name);
        if (result == 1) {
            return "success";
        }
        return "error";
    }
}
