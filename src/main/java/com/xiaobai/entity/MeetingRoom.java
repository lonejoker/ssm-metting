package com.xiaobai.entity;

/**
 * @author 终于白发始于青丝
 * @create 2021-12-19 上午 9:31
 * @program ssm-metting-project
 * @Version 1.0
 * @ClassName MeetingRoom
 */
public class MeetingRoom {
    //会议室ID
    private Integer roomid;
    //门牌号
    private Integer roomnum;
    //会议室名称
    private String roomname;
    //最多容纳人数
    private Integer capacity;
    //当前状态(0启用  1已占用)
    private Integer status;
    //备注
    private String description;

    public MeetingRoom() {
    }

    public MeetingRoom(Integer roomid, Integer roomnum, String roomname, Integer capacity, Integer status, String description) {
        this.roomid = roomid;
        this.roomnum = roomnum;
        this.roomname = roomname;
        this.capacity = capacity;
        this.status = status;
        this.description = description;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomid=" + roomid +
                ", roomnum=" + roomnum +
                ", roomname='" + roomname + '\'' +
                ", capacity=" + capacity +
                ", status=" + status +
                ", description='" + description + '\'' +
                '}';
    }

    public Integer getRoomid() {
        return roomid;
    }

    public void setRoomid(Integer roomid) {
        this.roomid = roomid;
    }

    public Integer getRoomnum() {
        return roomnum;
    }

    public void setRoomnum(Integer roomnum) {
        this.roomnum = roomnum;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
