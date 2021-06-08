package com.dh.service;

import com.dh.model.Activity;
import com.dh.model.dto.ActivityDelDTO;
import com.dh.model.dto.ActivityParamDTO;
import com.dh.model.dto.ItemBankAnswerDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.ActivityItemBankVO;
import com.dh.model.vo.ActivityVO;
import com.dh.model.vo.ItemBankAnswerResultVO;

import java.util.List;

public interface ActivityService {

	/**
	 * 用户端获取活动列表
	 * @param nowTime 当前时间
	 * @return
	 */
	public List<ActivityVO> getActivityUserList(String nowTime);
	
	/**
	 * 根据活动获取试题信息列表
	 * @param activityId
	 * @return
	 */
	public List<ActivityItemBankVO> getActivityItemBankList(Integer activityId);

	/**
	 * 提交答题信息
	 * @param ibaDTO     答题信息
	 * @param employeeId 员工标识
	 * @param departmentId 部门标识
	 * @return
	 */
	public ItemBankAnswerResultVO submitItemBankInfo(ItemBankAnswerDTO ibaDTO, Integer employeeId, Integer departmentId);
	
	/**
	 * 获取当天员工答题次数
	 * @param employeeId
	 * @return
	 */
	public Integer getEmployeeAnswerCount(Integer employeeId);
	
	/**
	 * 获取活动详情
	 * @param activityId  活动标识
	 * @return
	 */
	public ActivityVO getActivityInfo(Integer activityId);
	
	/**
	 * 获取活动列表
	 * @param activityParm 查询参数
	 * @param pageDTO    分页dto
	 * @return
	 */
	public List<ActivityVO> getActivityList(ActivityParamDTO activityParm, PageInfoDTO pageDTO);
	
	/**
	 * 添加活动信息
	 * @param activity
	 * @return
	 */
	public boolean insertActivityInfo(Activity activity);
	
	/**
	 * 修改活动信息
	 * @param activity
	 * @return
	 */
	public boolean updateActivityInfo(Activity activity);
	
	/**
	 * 删除活动信息
	 * @param activityDel
	 * @return
	 */
	public boolean deleteActivityInfo(ActivityDelDTO activityDel);
	
}
