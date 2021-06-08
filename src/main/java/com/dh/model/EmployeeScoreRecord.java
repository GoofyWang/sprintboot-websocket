package com.dh.model;

public class EmployeeScoreRecord {
	
	/**主键标识*/
    private Integer esrId;

	/**员工标识*/
    private Integer employeeId;

	/**分数*/
    private Integer esrScore;

	/**类型名称*/
    private String esrTypeName;

	/**创建时间*/
    private String createTime;

	/**可用状态 Y 可用 N 不可用*/
    private String status;

    public Integer getEsrId() {
        return esrId;
    }

    public void setEsrId(Integer esrId) {
        this.esrId = esrId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getEsrScore() {
        return esrScore;
    }

    public void setEsrScore(Integer esrScore) {
        this.esrScore = esrScore;
    }

    public String getEsrTypeName() {
        return esrTypeName;
    }

    public void setEsrTypeName(String esrTypeName) {
        this.esrTypeName = esrTypeName == null ? null : esrTypeName.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}