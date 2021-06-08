package com.dh.model;

public class EmployeeRanking {
	
    private Integer erId;

    private Integer employeeId;

    private Integer erTotalScore;

    private Integer erTotalTime;

    private String createTime;

    private String updateTime;

    private String status;

    public Integer getErId() {
        return erId;
    }

    public void setErId(Integer erId) {
        this.erId = erId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getErTotalScore() {
        return erTotalScore;
    }

    public void setErTotalScore(Integer erTotalScore) {
        this.erTotalScore = erTotalScore;
    }

    public Integer getErTotalTime() {
        return erTotalTime;
    }

    public void setErTotalTime(Integer erTotalTime) {
        this.erTotalTime = erTotalTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}