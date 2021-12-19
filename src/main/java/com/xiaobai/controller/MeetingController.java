package com.xiaobai.controller;

import com.xiaobai.entity.Department;
import com.xiaobai.entity.Employee;
import com.xiaobai.entity.Meeting;
import com.xiaobai.entity.SearchMeeting;
import com.xiaobai.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:46
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName MeetingController
 */
@Controller
public class MeetingController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private MeetingService meetingService;

    public static final Integer PAGE_SIZE = 10;

    @RequestMapping("/bookmeeting")
    public String bookmeeting(Model model) {
        model.addAttribute("mrs", meetingRoomService.getAllMr());
        return "bookmeeting";
    }

    @RequestMapping("/alldeps")
    @ResponseBody
    public List<Department> getAllDeps() {
        return departmentService.getAllDeps();
    }

    @RequestMapping("/getempbydepid")
    @ResponseBody
    public List<Employee> getEmpsByDepId(Integer depId) {
        return employeeService.getEmpsByDepId(depId);
    }

    @RequestMapping("/doAddMeeting")
    public String bookMeeting(Meeting meeting, Integer[] mps, HttpSession httpSession) {
        Employee currentuser = (Employee) httpSession.getAttribute("currentuser");
        meeting.setReservationistid(currentuser.getEmployeeId());
        Integer result = meetingService.addmeeting(meeting, mps);
        if (result == 1) {
            return "redirect:/searchmeetings";
        } else {
            return "forward:/bookmeeting";
        }
    }


    @RequestMapping("/searchmeetings")
    public String searchmeetings(SearchMeeting searchMeeting, @RequestParam(defaultValue = "1") Integer page, Model model) {
        List<SearchMeeting> searchMeetingList = meetingService.getAllSearchMeeting(searchMeeting, page, PAGE_SIZE);
        Long total = meetingService.getTotal(searchMeeting);
        model.addAttribute("sml", searchMeetingList);
        model.addAttribute("total", total);
        model.addAttribute("page", page);
        model.addAttribute("pagenum", total % PAGE_SIZE == 0 ? total / PAGE_SIZE : total / PAGE_SIZE + 1);
        return "searchmeetings";
    }

    @RequestMapping("/meetingdetails")
    public String meetingdetails(Integer meetingid, Model model) {
        Meeting meetings = meetingService.getMeetingById(meetingid);
        model.addAttribute("meeting", meetings);
        List<Employee> employeeList = employeeService.getEmpByMeetingId(meetingid);
        model.addAttribute("emps", employeeList);
        return "meetingdetails";
    }
}
