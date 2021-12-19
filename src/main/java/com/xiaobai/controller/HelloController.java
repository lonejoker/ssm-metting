package com.xiaobai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:45
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName HelloController
 */
@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("hello", "hello world");
        return "hello";
    }
}
