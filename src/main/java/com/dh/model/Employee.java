package com.dh.model;

public class Employee {
	
	/**主键标识*/
    private Integer employeeId;

	/**员工昵称*/
    private String employeeNickname;

	/**员工头像*/
    private String employeeHeadImage;

	/**员工性别 0 未知 1 男 2 女*/
    private String employeeSex;

	/**员工手机号*/
    private String employeeMobile;

	/**员工openid*/
    private String employeeOpenid;

	/**员工sessionkey*/
    private String employeeSessionkey;

	/**员工unionid*/
    private String employeeUnionid;

	/**员工真实姓名*/
    private String employeeRealName;

	/**员工登录密码*/
    private String employeePassword;
    
    /**员工隔离状态  Y 是  N 否*/
    private String employeeQuarantineStatus;

	/**员工年龄*/
    private Integer employeeAge;

	/**部门标识*/
    private Integer departmentId;

	/**创建时间*/
    private String createTime;

	/**修改时间*/
    private String updateTime;

	/**可用状态 Y 可用 N 不可用*/
    private String status;

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNickname() {
        return employeeNickname;
    }

    public void setEmployeeNickname(String employeeNickname) {
        this.employeeNickname = employeeNickname == null ? null : employeeNickname.trim();
    }

    public String getEmployeeHeadImage() {
        return employeeHeadImage;
    }

    public void setEmployeeHeadImage(String employeeHeadImage) {
        this.employeeHeadImage = employeeHeadImage == null ? null : employeeHeadImage.trim();
    }

    public String getEmployeeSex() {
        return employeeSex;
    }

    public void setEmployeeSex(String employeeSex) {
        this.employeeSex = employeeSex == null ? null : employeeSex.trim();
    }

    public String getEmployeeQuarantineStatus() {
		return employeeQuarantineStatus;
	}

	public void setEmployeeQuarantineStatus(String employeeQuarantineStatus) {
		this.employeeQuarantineStatus = employeeQuarantineStatus;
	}

	public String getEmployeeMobile() {
        return employeeMobile;
    }

    public void setEmployeeMobile(String employeeMobile) {
        this.employeeMobile = employeeMobile == null ? null : employeeMobile.trim();
    }

    public String getEmployeeOpenid() {
        return employeeOpenid;
    }

    public void setEmployeeOpenid(String employeeOpenid) {
        this.employeeOpenid = employeeOpenid == null ? null : employeeOpenid.trim();
    }

    public String getEmployeeSessionkey() {
        return employeeSessionkey;
    }

    public void setEmployeeSessionkey(String employeeSessionkey) {
        this.employeeSessionkey = employeeSessionkey == null ? null : employeeSessionkey.trim();
    }

    public String getEmployeeUnionid() {
        return employeeUnionid;
    }

    public void setEmployeeUnionid(String employeeUnionid) {
        this.employeeUnionid = employeeUnionid == null ? null : employeeUnionid.trim();
    }

    public String getEmployeeRealName() {
        return employeeRealName;
    }

    public void setEmployeeRealName(String employeeRealName) {
        this.employeeRealName = employeeRealName == null ? null : employeeRealName.trim();
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword == null ? null : employeePassword.trim();
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
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