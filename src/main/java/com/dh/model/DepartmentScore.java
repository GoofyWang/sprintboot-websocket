package com.dh.model;

import java.util.Date;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "对战积分表")
public class DepartmentScore {

    private Integer Id;

    private Integer DepartmentId;

    private Integer Score;

    private Integer Times;

    private Date UpdateTime;

    public Integer getId(){ return Id; }

    public void setId(Integer Id){this.Id = Id;}

    public Integer getDepartmentId(){ return DepartmentId; }

    public void setDepartmentId(Integer DepartmentId){this.DepartmentId = DepartmentId;}

    public Integer getScore() {
        return Score;
    }

    public void setScore(Integer Score){this.Score = Score;}

    public Date getUpdateTime() {
        return UpdateTime;
    }

    public void setUpdateTime(Date UpdateTime){this.UpdateTime = UpdateTime;}

    public Integer getTimes() {
        return Times;
    }

    public void setTimes(Integer times) {
        this.Times = times;
    }
}