package com.xiaobai.mapper;

import com.xiaobai.entity.Meeting;
import com.xiaobai.entity.Notifications;
import com.xiaobai.entity.SearchMeeting;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MeetingMapper {

    Integer addmeeting(Meeting meeting);

    void addParticipants(@Param("meetingid") Integer meetingid, @Param("mps") Integer[] mps);

    List<SearchMeeting> getAllSearchMeeting(@Param("sm") SearchMeeting searchMeeting, @Param("page") Integer page, @Param("pageSize") Integer pageSize);

    Long getTotal(@Param("sm") SearchMeeting searchMeeting);

    Meeting getMeetingById(Integer meetingid);

    List<Notifications> getNotifications0(Integer employeeId);

    List<Notifications> getNotifications1(Integer employeeId);

    List<Notifications> getMeetingById1(Integer employeeId);

    List<SearchMeeting> getSearchMeeting(Integer employeeId);
}
