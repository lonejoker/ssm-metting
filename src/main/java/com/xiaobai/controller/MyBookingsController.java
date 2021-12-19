package com.xiaobai.controller;

import com.xiaobai.entity.Employee;
import com.xiaobai.entity.Meeting;
import com.xiaobai.entity.Notifications;
import com.xiaobai.service.EmployeeService;
import com.xiaobai.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:47
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName MyBookingsController
 */
@Controller
public class MyBookingsController {

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/mybookings")
    public String myBookings(Model model, HttpSession httpSession) {
        Employee currentuser = (Employee) httpSession.getAttribute("currentuser");
        Integer employeeId = currentuser.getEmployeeId();
        List<Notifications> notifications = meetingService.getMeetingById1(employeeId);
        model.addAttribute("nots", notifications);
        return "mybookings";
    }

    @RequestMapping("/mymeetingdetails")
    public String myMeetingDetails(Integer meetingid, Model model, HttpSession httpSession) {
        Employee currentuser = (Employee) httpSession.getAttribute("currentuser");
        Integer employeeId = currentuser.getEmployeeId();
        Meeting meeting = meetingService.getMeetingById(employeeId);
        List<Employee> empByMeetingId = employeeService.getEmpByMeetingId(meetingid);
        model.addAttribute("meeting", meeting);
        model.addAttribute("emps", empByMeetingId);
        return "mymeetingdetails";
    }
}
