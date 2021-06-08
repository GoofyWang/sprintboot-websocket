package com.dh.dao;

import com.dh.model.vo.EmployeeScoreRecordVO;
import com.dh.model.EmployeeScoreRecord;

import java.util.List;
import java.util.Map;

public interface EmployeeScoreRecordMapper {
	
	/**
	 * 获取员工总积分
	 * @param employeeId
	 * @return
	 */
	Integer getEmployeeTotalScore(Integer employeeId);

	/**
	 * 添加员工积分
	 * @param record
	 * @return
	 */
    int insert(EmployeeScoreRecord record);

    /**
     * 获取员工积分列表
     * @param employeeId  员工标识
     * @return
     */
	List<EmployeeScoreRecordVO> getEmployeeScoreRecordList(Integer employeeId);

	/**
	 * 获取总积分前三名的员工
	 * @return
	 */
	List<Map<String, Integer>> getEmployeeScoreProfileList();
}