package com.dh.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "对战房间")
public class Room {
	
    private Integer RoomId;

    private Integer OwnerId;

    private Integer Num;

    private Integer Status;

    private Integer Department;

    private Integer Type;

    private Date CreateTime;

    public Integer getRoomId() {
        return RoomId;
    }

    public void setRoomId(Integer RoomId){this.RoomId = RoomId;}

    public Integer getOwnerId() {
        return OwnerId;
    }

    public void setOwnerId(Integer OwnerId){this.OwnerId = OwnerId;}

    public Integer getNum() {
        return Num;
    }

    public void setNum(Integer Num){this.Num = Num;}

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer Status){this.Status = Status;}

    public Integer getDepartment() {
        return Department;
    }

    public void setDepartment(Integer Department){this.Department = Department;}

    public Integer getType() {
        return Type;
    }

    public void setType(Integer Type){this.Type = Type;}

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime){this.CreateTime = CreateTime;}

}