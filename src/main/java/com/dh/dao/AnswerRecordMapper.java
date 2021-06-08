package com.dh.dao;

import com.dh.model.AnswerRecord;
import com.dh.model.vo.EmployeeFalseItemBankVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnswerRecordMapper {
	
    /**
     * 根据员工标识获取员工答题记录
     * @param employeeId
     * @return
     */
	List<AnswerRecord> getAnswerRecordByEmployeeId(@Param("employeeId") Integer employeeId);

	/**
	 * 根据员工标识和试题id获取答题记录
	 * @param employeeId  员工标识
	 * @param ibIdList    试题标识list
	 * @return
	 */
	List<AnswerRecord> getAnswerRecordList(@Param("employeeId") Integer employeeId, 
										   @Param("list") List<Integer> ibIdList);
	
	/**
	 * 根据主键ids删除数据
	 * @param arIdDelList ids
	 * @return
	 */
	int deleteAnswerRecordByIds(@Param("list") List<Integer> arIdDelList);

	/**
	 * 添加答题记录
	 * @param arList  
	 * @return
	 */
	int insertAnswerRecord(@Param("list") List<AnswerRecord> arList);

	/**
	 * 根据员工标识获取错题信息
	 * @param employeeId  员工标识
	 * @return
	 */
	List<EmployeeFalseItemBankVO> getEmployeeFalseItemBankList(Integer employeeId);
	
}