package com.xiaobai.controller;

import com.xiaobai.entity.Employee;
import com.xiaobai.entity.SearchMeeting;
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
 * @ClassName MyMeetingsController
 */
@Controller
public class MyMeetingsController {

    @Autowired
    private MeetingService meetingService;

    @RequestMapping("/mymeetings")
    public String mymeetings(Model model, HttpSession httpSession) {
        Employee currentuser = (Employee) httpSession.getAttribute("currentuser");
        Integer employeeId = currentuser.getEmployeeId();
        List<SearchMeeting> sms = meetingService.getSearchMeeting(employeeId);
        model.addAttribute("sms", sms);
        return "mymeetings";
    }
}
