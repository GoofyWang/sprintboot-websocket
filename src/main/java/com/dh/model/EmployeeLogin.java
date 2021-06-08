package com.dh.model;

public class EmployeeLogin {
    private Integer employeeLoginId;

    private Integer employeeId;

    private String employeeLoginToken;

    private String employeeLoginIp;

    private String employeeLoginStatus;

    private String employeeLoginType;

    private String createTime;

    private String updateTime;

    public Integer getEmployeeLoginId() {
        return employeeLoginId;
    }

    public void setEmployeeLoginId(Integer employeeLoginId) {
        this.employeeLoginId = employeeLoginId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeLoginToken() {
        return employeeLoginToken;
    }

    public void setEmployeeLoginToken(String employeeLoginToken) {
        this.employeeLoginToken = employeeLoginToken == null ? null : employeeLoginToken.trim();
    }

    public String getEmployeeLoginIp() {
        return employeeLoginIp;
    }

    public void setEmployeeLoginIp(String employeeLoginIp) {
        this.employeeLoginIp = employeeLoginIp == null ? null : employeeLoginIp.trim();
    }

    public String getEmployeeLoginStatus() {
        return employeeLoginStatus;
    }

    public void setEmployeeLoginStatus(String employeeLoginStatus) {
        this.employeeLoginStatus = employeeLoginStatus == null ? null : employeeLoginStatus.trim();
    }

    public String getEmployeeLoginType() {
        return employeeLoginType;
    }

    public void setEmployeeLoginType(String employeeLoginType) {
        this.employeeLoginType = employeeLoginType == null ? null : employeeLoginType.trim();
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
}