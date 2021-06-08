package com.dh.model;

public class ActivityEmployeeRecord {
	
	/**主键标识*/
    private Integer aerId;

	/**活动标识*/
    private Integer activityId;

	/**员工标识*/
    private Integer employeeId;

	/**答题得分*/
    private Integer aerScore;

	/**答题用时（秒）*/
    private Integer aerAnswerTime;
    
    /**是否挑战成功  Y 是  N 否*/
    private String aerChallengeStatus;

	/**答题日期*/
    private String aerAnswerDate;

	/**创建时间*/
    private String createTime;

    public Integer getAerId() {
        return aerId;
    }

    public void setAerId(Integer aerId) {
        this.aerId = aerId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getAerScore() {
        return aerScore;
    }

    public void setAerScore(Integer aerScore) {
        this.aerScore = aerScore;
    }

    public Integer getAerAnswerTime() {
        return aerAnswerTime;
    }

    public void setAerAnswerTime(Integer aerAnswerTime) {
        this.aerAnswerTime = aerAnswerTime;
    }

    public String getAerAnswerDate() {
        return aerAnswerDate;
    }

    public void setAerAnswerDate(String aerAnswerDate) {
        this.aerAnswerDate = aerAnswerDate == null ? null : aerAnswerDate.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

	public String getAerChallengeStatus() {
		return aerChallengeStatus;
	}

	public void setAerChallengeStatus(String aerChallengeStatus) {
		this.aerChallengeStatus = aerChallengeStatus;
	}
	
}