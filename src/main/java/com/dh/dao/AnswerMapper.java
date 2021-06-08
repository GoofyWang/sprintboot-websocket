package com.dh.dao;

import com.dh.model.Answer;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerMapper {
    
	/**
	 * 添加试题答案信息
	 * @param answerList  答案列表
	 * @param itembankId  试题标识
	 * @return
	 */
	int insert(@Param("list") List<Answer> answerList, 
			   @Param("itemBankId") Integer itemBankId);

	/**
	 * 删除试题答案信息
	 * @param itembankId  试题标识
	 * @return
	 */
	int deleteAnswerByItemBankId(@Param("itemBankId") Integer itemBankId);

	/**
	 * 根据试题标识ids获取试题答案记录
	 * @param ibIdList
	 * @return
	 */
	List<Answer> getAnswerListByItembankIds(@Param("list") List<Integer> ibIdList);

	/**
	 * 根据试题标识ids获取试题答案记录
	 * @param ibId
	 * @return
	 */
	List<Answer> getAnswerListByItembankId(Integer itemBankId);


}