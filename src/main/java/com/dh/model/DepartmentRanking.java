package com.dh.model;

public class DepartmentRanking {
    private Integer drId;

    private Integer departmentId;

    private Integer drTotalScore;

    private Integer drTotalTime;

    private String createTime;

    private String updateTime;

    private String status;

    public Integer getDrId() {
        return drId;
    }

    public void setDrId(Integer drId) {
        this.drId = drId;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public Integer getDrTotalScore() {
        return drTotalScore;
    }

    public void setDrTotalScore(Integer drTotalScore) {
        this.drTotalScore = drTotalScore;
    }

    public Integer getDrTotalTime() {
        return drTotalTime;
    }

    public void setDrTotalTime(Integer drTotalTime) {
        this.drTotalTime = drTotalTime;
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