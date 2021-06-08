package com.dh.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "活动信息")
public class Activity {
	
	@ApiModelProperty(value = "活动标识")
    private Integer activityId;

	@ApiModelProperty(value = "活动名称")
    private String activityName;

	@ApiModelProperty(value = "活动开始时间")
    private String activityStartTime;

	@ApiModelProperty(value = "活动结束时间")
    private String activityEndTime;
	
	@ApiModelProperty(value = "活动试题数量")
	private Integer activityItemBankNumber;

	@ApiModelProperty(value = "活动备注")
    private String activityRemark;

	@ApiModelProperty(value = "创建人", hidden = true)
    private Integer createId;

	@ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

	@ApiModelProperty(value = "修改人", hidden = true)
    private Integer updateId;

	@ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;

	@ApiModelProperty(value = "可用状态 Y 可用 N 不可用", hidden = true)
    private String status;

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName == null ? null : activityName.trim();
    }

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime == null ? null : activityStartTime.trim();
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime == null ? null : activityEndTime.trim();
    }

    public Integer getActivityItemBankNumber() {
		return activityItemBankNumber;
	}

	public void setActivityItemBankNumber(Integer activityItemBankNumber) {
		this.activityItemBankNumber = activityItemBankNumber;
	}

	public String getActivityRemark() {
        return activityRemark;
    }

    public void setActivityRemark(String activityRemark) {
        this.activityRemark = activityRemark == null ? null : activityRemark.trim();
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
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