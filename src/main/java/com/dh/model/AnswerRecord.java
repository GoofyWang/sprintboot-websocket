package com.dh.model;

public class AnswerRecord {
	
	/**主键标识*/
    private Integer arId;

	/**员工标识*/
    private Integer employeeId;

	/**试题标识*/
    private Integer itemBankId;

	/**员工答案*/
    private String arEmployeeAnwer;

	/**正确答案*/
    private String arRealAnswer;

	/**是否正确 Y 是 N 否*/
    private String arStatus;

	/**做题次数*/
    private Integer arNumber;

	/**创建时间*/
    private String createTime;

	/**修改时间*/
    private String updateTime;

	/**可用状态 Y 可用 N 不可用*/
    private String status;

    public Integer getArId() {
        return arId;
    }

    public void setArId(Integer arId) {
        this.arId = arId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getItemBankId() {
        return itemBankId;
    }

    public void setItemBankId(Integer itemBankId) {
        this.itemBankId = itemBankId;
    }

    public String getArEmployeeAnwer() {
        return arEmployeeAnwer;
    }

    public void setArEmployeeAnwer(String arEmployeeAnwer) {
        this.arEmployeeAnwer = arEmployeeAnwer == null ? null : arEmployeeAnwer.trim();
    }

    public String getArRealAnswer() {
        return arRealAnswer;
    }

    public void setArRealAnswer(String arRealAnswer) {
        this.arRealAnswer = arRealAnswer == null ? null : arRealAnswer.trim();
    }

    public String getArStatus() {
        return arStatus;
    }

    public void setArStatus(String arStatus) {
        this.arStatus = arStatus == null ? null : arStatus.trim();
    }

    public Integer getArNumber() {
        return arNumber;
    }

    public void setArNumber(Integer arNumber) {
        this.arNumber = arNumber;
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