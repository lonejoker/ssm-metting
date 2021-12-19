package com.xiaobai.service;

import com.xiaobai.mapper.EmployeeMapper;
import com.xiaobai.mapper.MeetingMapper;
import com.xiaobai.entity.Meeting;
import com.xiaobai.entity.Notifications;
import com.xiaobai.entity.SearchMeeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:36
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName MeetingService
 */
@Service
public class MeetingService {

    @Autowired
    private MeetingMapper meetingMapper;

    @Autowired
    private EmployeeMapper employeeMapper;

    public Integer addmeeting(Meeting meeting, Integer[] mps) {
        meeting.setReservationtime(new Date());
        meeting.setStatus(0);
        Integer result = meetingMapper.addmeeting(meeting);
        meetingMapper.addParticipants(meeting.getMeetingid(), mps);
        return result;
    }

    public List<SearchMeeting> getAllSearchMeeting(SearchMeeting searchMeeting, Integer page, Integer pageSize) {
        page = (page - 1) * pageSize;
        return meetingMapper.getAllSearchMeeting(searchMeeting, page, pageSize);
    }

    public Long getTotal(SearchMeeting searchMeeting) {
        return meetingMapper.getTotal(searchMeeting);
    }

    public Meeting getMeetingById(Integer meetingid) {
        return meetingMapper.getMeetingById(meetingid);
    }

    public List<Notifications> getNotifications0(Integer employeeId) {
        return meetingMapper.getNotifications0(employeeId);
    }

    public List<Notifications> getNotifications1(Integer employeeId) {
        return meetingMapper.getNotifications1(employeeId);
    }

    public List<Notifications> getMeetingById1(Integer employeeId) {
        return meetingMapper.getMeetingById1(employeeId);
    }

    public List<SearchMeeting> getSearchMeeting(Integer employeeId) {
        return meetingMapper.getSearchMeeting(employeeId);
    }
}
