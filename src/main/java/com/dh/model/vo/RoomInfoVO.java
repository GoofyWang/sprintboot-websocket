package com.dh.model.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "对战")
public class RoomInfoVO {

    private Integer Id;

    private Integer RoomId;

    private Integer EmployeeId;

    private Date CreateTime;

    private String EmployeeHeadImage;

    private String EmployeeSex;

    private String EmployeeRealName;

    private String departmentName;

    public Integer getRoomId() {
        return RoomId;
    }

    public Integer getEmployeeId() {
        return EmployeeId;
    }

}
