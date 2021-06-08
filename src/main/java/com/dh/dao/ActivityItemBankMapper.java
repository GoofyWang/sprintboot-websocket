package com.dh.dao;

import com.dh.model.ActivityItemBank;
import com.dh.model.dto.ActivityItemBankParamDTO;
import com.dh.model.vo.ActivityIBVO;
import com.dh.model.vo.ActivityItemBankVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ActivityItemBankMapper {
	
	/**
	 * 删除活动试题
	 * @param aibId
	 * @return
	 */
    int deleteByPrimaryKey(Integer aibId);

    /**
     * 添加活动试题
     * @param record
     * @return
     */
    int insertSelective(ActivityItemBank record);

    /**
     * 获取活动试题详情
     * @param aibId
     * @return
     */
    ActivityItemBank selectByPrimaryKey(Integer aibId);

    /**
     * 修改活动试题
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ActivityItemBank record);

    /**
     * 根据活动标识获取试题总分
     * @param activityId  活动标识
     * @return
     */
    @Deprecated
    Integer getTotalScoreByActivityId(Integer activityId);
    
    /**
	 * 根据活动获取试题信息列表
	 * @param activityId  活动标识 
	 * @return
	 */
	List<ActivityItemBankVO> getActivityItemBankList(@Param("activityId") Integer activityId);


	List<ActivityItemBankVO> getRandomItemBank(List<Integer> ids);

	Integer getRandomItemBankId();

	Integer getRandomItemBankIdNotInIds(List<Integer> ids);


	/**
	 * 根据活动获取试题信息(正确答案)列表
	 * @param activityId  活动标识 
	 * @return
	 */
	List<ActivityItemBankVO> getActivityItemBankRealAnswerList(@Param("activityId") Integer activityId);
	
	/**
	 * 根据活动标识获取活动试题信息
	 * @param activityId  活动标识
	 * @return
	 */
	List<ActivityItemBank> getActivityItemBankListByActivityId(Integer activityId);

	/**
	 * 获取活动试题列表
	 * @param paramDTo
	 * @return
	 */
	List<ActivityIBVO> getActivityItemBankAdminList(ActivityItemBankParamDTO paramDTo);
	
	/**
	 * 获取当前活动试题的最大排序值
	 * @param activityId
	 * @return
	 */
	Integer getMaxSort(@Param("activityId") Integer activityId);
	
	/**
	 * 批量添加活动试题信息
	 * @param aibList
	 * @return
	 */
	int insert(@Param("list") List<ActivityItemBank> aibList);

	/**
	 * 批量删除活动试题信息
	 * @param aibIdList
	 * @return
	 */
	int batchDeleteActivityItemBankInfo(@Param("list") List<Integer> aibIdList);

	/**
	 * 批量添加活动试题
	 * @param aibList
	 * @return
	 */
	int addActivityItemBankInfo(@Param("list") List<ActivityItemBank> aibList);
	
	/**
	 * 根据活动标识删除活动试题
	 * @param activityId
	 * @return
	 */
	int deleteActivityItemBankByActivityId(@Param("activityId") Integer activityId);

	/**
	 * 根据活动标识获取活动试题
	 * @param activityId
	 * @return
	 */
	List<ActivityIBVO> getAibListByActivityId(Integer activityId);
	
}