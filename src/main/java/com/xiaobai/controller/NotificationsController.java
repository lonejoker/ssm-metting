package com.xiaobai.controller;

import com.xiaobai.entity.Employee;
import com.xiaobai.entity.Notifications;
import com.xiaobai.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:48
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName NotificationsController
 */
@Controller
public class NotificationsController {

    @Autowired
    private MeetingService meetingService;

    @GetMapping("/notifications")
    public String notifications(Model model, HttpSession httpSession) {
        Employee currentuser = (Employee) httpSession.getAttribute("currentuser");
        Integer employeeId = currentuser.getEmployeeId();
        List<Notifications> nts = meetingService.getNotifications0(employeeId);
        List<Notifications> qxs = meetingService.getNotifications1(employeeId);
        model.addAttribute("nts", nts);
        model.addAttribute("qxs", qxs);
        return "notifications";
    }

}
