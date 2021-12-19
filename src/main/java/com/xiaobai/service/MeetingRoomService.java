package com.xiaobai.service;

import com.xiaobai.mapper.MeetingRoomMapper;
import com.xiaobai.entity.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:36
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName MeetingRoomService
 */
@Service
public class MeetingRoomService {

    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

    public List<MeetingRoom> getAllMr() {
        return meetingRoomMapper.getAllMr();
    }

    public MeetingRoom getMrById(Integer roomid) {
        return meetingRoomMapper.getMrById(roomid);
    }

    public Integer updateroom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.updateroom(meetingRoom);
    }

    public Integer doAddMr(MeetingRoom meetingRoom) {
        return meetingRoomMapper.doAddMr(meetingRoom);
    }
}
