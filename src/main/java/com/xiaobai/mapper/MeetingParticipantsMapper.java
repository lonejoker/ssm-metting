package com.xiaobai.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MeetingParticipantsMapper {
    List<Integer> getIdList(Integer meetingid);
}
