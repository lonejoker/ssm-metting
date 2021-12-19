package com.xiaobai.controller;

import com.xiaobai.entity.MeetingRoom;
import com.xiaobai.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:47
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName MeetingRoomController
 */
@Controller
public class MeetingRoomController {

    @Autowired
    private MeetingRoomService meetingRoomService;

    @RequestMapping("/meetingrooms")
    public String meetingrooms(Model model) {
        model.addAttribute("mrs", meetingRoomService.getAllMr());
        return "meetingrooms";
    }

    @RequestMapping("/roomdetails")
    public String roomdetails(Integer roomid, Model model) {
        model.addAttribute("mr", meetingRoomService.getMrById(roomid));
        return "roomdetails";
    }

    @RequestMapping("/updateroom")
    public String updateroom(MeetingRoom meetingRoom) {
        Integer result = meetingRoomService.updateroom(meetingRoom);
        if (result == 1) {
            return "redirect:/meetingrooms";
        } else {
            return "forward:/roomdetails?roomid=" + meetingRoom.getRoomid();
        }
    }

    @RequestMapping("/admin/addmeetingroom")
    public String addmeetingroom() {
        return "addmeetingroom";
    }

    @RequestMapping("/admin/doAddMr")
    public String doAddMr(MeetingRoom meetingRoom) {
        meetingRoomService.doAddMr(meetingRoom);
        return "redirect:/meetingrooms";
    }
}
