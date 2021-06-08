package com.dh.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "活动试题")
public class ActivityItemBank {
	
	@ApiModelProperty(value = "活动试题标识", hidden = true)
    private Integer aibId;

    @ApiModelProperty(value = "活动标识")
	private Integer activityId;

	@ApiModelProperty(value = "试题标识")
    private Integer itemBankId;

	@ApiModelProperty(value = "活动试题分数")
    private Integer aibScore;

	@ApiModelProperty(value = "活动试题顺序", hidden = true)
    private Integer aibSort;

    public Integer getAibId() {
        return aibId;
    }

    public void setAibId(Integer aibId) {
        this.aibId = aibId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getItemBankId() {
        return itemBankId;
    }

    public void setItemBankId(Integer itemBankId) {
        this.itemBankId = itemBankId;
    }

    public Integer getAibScore() {
        return aibScore;
    }

    public void setAibScore(Integer aibScore) {
        this.aibScore = aibScore;
    }

    public Integer getAibSort() {
        return aibSort;
    }

    public void setAibSort(Integer aibSort) {
        this.aibSort = aibSort;
    }
}