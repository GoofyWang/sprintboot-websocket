package com.dh.dao;

import com.dh.model.vo.EmployeeRankingInfoVO;
import com.dh.model.EmployeeRanking;

import java.util.List;

public interface EmployeeRankingMapper {

    /**
     * 添加员工排行榜信息
     * @param record
     * @return
     */
    int insert(EmployeeRanking record);

    /**
     * 修改排行榜信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(EmployeeRanking record);

    /**
     * 根据员工标识获取员工排行榜信息
     * @param employeeId
     * @return
     */
	EmployeeRanking getEmployeeRankingInfoByEmployeeId(Integer employeeId);
	
	/**
	 * 获取员工当前排名信息
	 * @param employeeId  员工标识
	 * @return
	 */
	EmployeeRankingInfoVO getEmployeeCurrRankingByEmployeeId(Integer employeeId);
	
	/**
	 * 获取员工排名列表
	 * @return
	 */
	List<EmployeeRankingInfoVO> getEmployeeRankingList();
	
}