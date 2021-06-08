package com.dh.model;

public class ActivityRule {
    private Integer activityRuleId;

    private Integer activityId;

    private String activityRuleContent;

    private String activityRuleType;

    public Integer getActivityRuleId() {
        return activityRuleId;
    }

    public void setActivityRuleId(Integer activityRuleId) {
        this.activityRuleId = activityRuleId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getActivityRuleContent() {
        return activityRuleContent;
    }

    public void setActivityRuleContent(String activityRuleContent) {
        this.activityRuleContent = activityRuleContent == null ? null : activityRuleContent.trim();
    }

    public String getActivityRuleType() {
        return activityRuleType;
    }

    public void setActivityRuleType(String activityRuleType) {
        this.activityRuleType = activityRuleType == null ? null : activityRuleType.trim();
    }
}