package com.xiaobai.entity;

import java.util.Date;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:32
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName Notifications
 */
public class Notifications {
    private Integer meetingid;

    private String meetingname;

    private Integer roomid;

    private String roomname;

    private Integer reservationistid;

    private Date starttime;

    private Date endtime;

    private Date reservationtime;

    private String status;

    public Notifications() {
    }

    public Notifications(Integer meetingid, String meetingname, Integer roomid, String roomname, Integer reservationistid, Date starttime, Date endtime, Date reservationtime, String status) {
        this.meetingid = meetingid;
        this.meetingname = meetingname;
        this.roomid = roomid;
        this.roomname = roomname;
        this.reservationistid = reservationistid;
        this.starttime = starttime;
        this.endtime = endtime;
        this.reservationtime = reservationtime;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Notifications{" +
                "meetingid=" + meetingid +
                ", meetingname='" + meetingname + '\'' +
                ", roomid=" + roomid +
                ", roomname='" + roomname + '\'' +
                ", reservationistid=" + reservationistid +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", reservationtime=" + reservationtime +
                ", status='" + status + '\'' +
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

    public Integer getReservationistid() {
        return reservationistid;
    }

    public void setReservationistid(Integer reservationistid) {
        this.reservationistid = reservationistid;
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

    public Date getReservationtime() {
        return reservationtime;
    }

    public void setReservationtime(Date reservationtime) {
        this.reservationtime = reservationtime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
