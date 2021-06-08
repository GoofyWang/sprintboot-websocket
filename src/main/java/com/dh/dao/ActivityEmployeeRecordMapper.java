package com.dh.dao;

import com.dh.model.ActivityEmployeeRecord;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityEmployeeRecordMapper {

	/**
	 * 添加活动用户记录
	 * @param record
	 * @return
	 */
    int insert(ActivityEmployeeRecord record);

    /**
     * 获取用户活动记录
     * @param employeeId
     * @return
     */
    List<ActivityEmployeeRecord> getActivityEmployeeRecordList(Integer employeeId);
    
    /**
	 * 获取当天员工答题次数
	 * @param employeeId  员工标识
	 * @param date  当前日期
	 * @return
	 */
	Integer getEmployeeAnswerCount(@Param("employeeId") Integer employeeId, 
								   @Param("date") String date);
    
}