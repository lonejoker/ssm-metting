package com.xiaobai.entity;

import java.util.Date;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:32
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName SearchMeeting
 */
public class SearchMeeting {
    //会议id
    private Integer meetingid;
    //会议名称
    private String meetingname;
    //房间号
    private Integer roomid;
    //会议室名称
    private String roomname;
    //预定时间
    private Date reservationtime;
    //会议时间
    private Date starttime;
    private Date endtime;
    //预约者id
    private Integer reservationistid;
    //预约者
    private String reservationistname;

    public SearchMeeting() {
    }

    public SearchMeeting(Integer meetingid, String meetingname, Integer roomid, String roomname, Date reservationtime, Date starttime, Date endtime, Integer reservationistid, String reservationistname) {
        this.meetingid = meetingid;
        this.meetingname = meetingname;
        this.roomid = roomid;
        this.roomname = roomname;
        this.reservationtime = reservationtime;
        this.starttime = starttime;
        this.endtime = endtime;
        this.reservationistid = reservationistid;
        this.reservationistname = reservationistname;
    }

    @Override
    public String toString() {
        return "SearchMeeting{" +
                "meetingid=" + meetingid +
                ", meetingname='" + meetingname + '\'' +
                ", roomid=" + roomid +
                ", roomname='" + roomname + '\'' +
                ", reservationtime=" + reservationtime +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", reservationistid=" + reservationistid +
                ", reservationistname='" + reservationistname + '\'' +
                '}';
    }

    public Integer getMeetingid() {
        return meetingid;
    }

    public void setMeetingid(Integer meetingid) {
        this.meetingid = meetingid;
    }

    public String getMeetingname() {
        return meetingname;
    }

    public void setMeetingname(String meetingname) {
        this.meetingname = meetingname;
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Date getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(Date reservationtime) {
        this.reservationtime = reservationtime;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Integer getReservationistid() {
        return reservationistid;
    }

    public void setReservationistid(Integer reservationistid) {
        this.reservationistid = reservationistid;
    }

    public String getReservationistname() {
        return reservationistname;
    }

    public void setReservationistname(String reservationistname) {
        this.reservationistname = reservationistname;
    }
}