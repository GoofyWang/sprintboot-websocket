package com.dh.service;

import com.dh.model.ActivityItemBank;
import com.dh.model.dto.ActivityItemBankDTO;
import com.dh.model.dto.ActivityItemBankParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.ActivityIBVO;

import java.util.List;

public interface ActivityItemBankService {

	/**
	 * 获取活动试题详情
	 * @param aibId  活动试题标识
	 * @return
	 */
	public ActivityItemBank getActivityItemBankInfo(Integer aibId);
	
	/**
	 * 获取活动试题列表
	 * @param paramDTo  试题
	 * @param pageDTO    分页dto
	 * @return
	 */
	public List<ActivityIBVO> getActivityItemBankAdminList(ActivityItemBankParamDTO paramDTo, PageInfoDTO pageDTO);
	
	/**
	 * 批量添加活动试题
	 * @param aibDTO
	 * @return
	 */
	public boolean batchInsertActivityItemBank(ActivityItemBankDTO aibDTO);
	
	/**
	 * 添加活动试题信息
	 * @param aib
	 * @return
	 */
	public boolean insertActivityItemBankInfo(ActivityItemBank aib);
	
	/**
	 * 修改活动试题信息
	 * @param aib
	 * @return
	 */
	public boolean updateActivityItemBankInfo(ActivityItemBank aib);
	
	/**
	 * 删除活动试题信息
	 * @param aibId
	 * @return
	 */
	public boolean deleteActivityItemBankInfo(Integer aibId);

	/**
	 * 批量删除活动试题信息
	 * @param aibIdList
	 * @return
	 */
	public boolean batchDeleteActivityItemBankInfo(List<Integer> aibIdList);

	/**
	 * 添加活动试题
	 * @param aibList
	 * @return
	 */
	public boolean addActivityItemBankInfo(List<ActivityItemBank> aibList);

	/**
	 * 根据活动标识获取活动试题
	 * @param activityId 活动标识
	 * @return
	 */
	public List<ActivityIBVO> getActivityItemBankAdminList(Integer activityId);
}
