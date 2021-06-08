package com.dh.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "对战房间信息")
public class RoomInfo {
	
    private Integer Id;

    private Integer RoomId;

    private Integer EmployeeId;

    private Date CreateTime;

    private String EmployeeHeadImage;

    private String EmployeeSex;

    private String EmployeeNickname;

//	private List<Answer> answerList;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id){this.Id = Id;}

    public Integer getRoomId() {
        return RoomId;
    }

    public void setRoomId(Integer RoomId){this.RoomId = RoomId;}

    public Integer getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(Integer EmployeeId){this.EmployeeId = EmployeeId;}

    public Date getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(Date CreateTime){this.CreateTime = CreateTime;}

}