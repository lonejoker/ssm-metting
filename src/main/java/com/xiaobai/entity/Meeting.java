package com.xiaobai.entity;

import java.util.Date;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:30
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName Meeting
 */
public class Meeting {
    //会议ID
    private Integer meetingid;
    //会议名称
    private String meetingname;
    //房间号
    private Integer roomid;
    //谁预订的
    private Integer reservationistid;
    //参加人数
    private Integer numberofparticipants;
    //开始时间
    private Date starttime;
    //结束时间
    private Date endtime;
    //预约时间
    private Date reservationtime;
    //取消时间
    private Date canceledtime;
    //会议说明
    private String description;
    //状态（0启用  1已占用）
    private Integer status;
    //取消原因
    private String canceledreason;

    public Meeting() {
    }

    public Meeting(Integer meetingid, String meetingname, Integer roomid, Integer reservationistid, Integer numberofparticipants, Date starttime, Date endtime, Date reservationtime, Date canceledtime, String description, Integer status, String canceledreason) {
        this.meetingid = meetingid;
        this.meetingname = meetingname;
        this.roomid = roomid;
        this.reservationistid = reservationistid;
        this.numberofparticipants = numberofparticipants;
        this.starttime = starttime;
        this.endtime = endtime;
        this.reservationtime = reservationtime;
        this.canceledtime = canceledtime;
        this.description = description;
        this.status = status;
        this.canceledreason = canceledreason;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "meetingid=" + meetingid +
                ", meetingname='" + meetingname + '\'' +
                ", roomid=" + roomid +
                ", reservationistid=" + reservationistid +
                ", numberofparticipants=" + numberofparticipants +
                ", starttime=" + starttime +
                ", endtime=" + endtime +
                ", reservationtime=" + reservationtime +
                ", canceledtime=" + canceledtime +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", canceledreason='" + canceledreason + '\'' +
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

    public Integer getReservationistid() {
        return reservationistid;
    }

    public void setReservationistid(Integer reservationistid) {
        this.reservationistid = reservationistid;
    }

    public Integer getNumberofparticipants() {
        return numberofparticipants;
    }

    public void setNumberofparticipants(Integer numberofparticipants) {
        this.numberofparticipants = numberofparticipants;
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

    public Date getCanceledtime() {
        return canceledtime;
    }

    public void setCanceledtime(Date canceledtime) {
        this.canceledtime = canceledtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCanceledreason() {
        return canceledreason;
    }

    public void setCanceledreason(String canceledreason) {
        this.canceledreason = canceledreason;
    }
}