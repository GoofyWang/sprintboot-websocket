package com.dh.dao;

import com.dh.model.ActivityRule;

import java.util.List;

public interface ActivityRuleMapper {
    int deleteByPrimaryKey(Integer activityRuleId);

    int insert(ActivityRule record);

    int insertSelective(ActivityRule record);

    ActivityRule selectByPrimaryKey(Integer activityRuleId);

    int updateByPrimaryKeySelective(ActivityRule record);

    int updateByPrimaryKey(ActivityRule record);

    /**
     * 根据活动获取活动规则
     * @param activityId
     * @return
     */
	List<ActivityRule> getActivityRuleList(Integer activityId);

}