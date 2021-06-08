package com.dh.dao;

import com.dh.model.Activity;
import com.dh.model.dto.ActivityDelDTO;
import com.dh.model.dto.ActivityParamDTO;
import com.dh.model.vo.ActivityVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityMapper {
	

	/**
	 * 添加活动
	 * @param record
	 * @return
	 */
    int insertSelective(Activity record);

    /**
     * 获取活动详情
     * @param activityId
     * @return
     */
    Activity selectByPrimaryKey(Integer activityId);

    /**
     * 修改活动
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Activity record);

    /**
     * 用户端获取活动列表
     * @param nowTime  当前时间
     * @return
     */
	List<ActivityVO> getActivityUserList(@Param("nowTime") String nowTime);

	/**
	 * 获取活动详情
	 * @param activityId
	 * @return
	 */
	ActivityVO getActivityInfo(@Param("activityId") Integer activityId);

	/**
	 * 获取活动列表
	 * @param activityParm
	 * @return
	 */
	List<ActivityVO> getActivityList(ActivityParamDTO activityParm);

	/**
	 * 删除活动信息
	 * @param activityDel
	 * @return
	 */
	int deleteActivityInfo(ActivityDelDTO activityDel);


}