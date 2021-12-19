package com.xiaobai.mapper;

import com.xiaobai.entity.MeetingRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingRoomMapper {
    public List<MeetingRoom> getAllMr();

    MeetingRoom getMrById(Integer roomid);

    Integer updateroom(MeetingRoom meetingRoom);

    Integer doAddMr(MeetingRoom meetingRoom);
}
