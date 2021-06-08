package com.dh.dao;

import com.dh.model.DepartmentRanking;
import com.dh.model.vo.DepartmentRankingVO;

import java.util.List;

public interface DepartmentRankingMapper {

	/**
	 * 添加部门排行
	 * @param record
	 * @return
	 */
    int insert(DepartmentRanking record);

    /**
     * 修改部门排行
     * @param record
     * @return
     */
    int updateByPrimaryKey(DepartmentRanking record);
    
    /**
     * 根据部门标识获取部门排行榜信息
     * @param employeeId
     * @return
     */
    DepartmentRanking getDepartmentRankingInfoByDepartmentId(Integer departmentId);
	
	/**
	 * 获取部门当前排名信息
	 * @param employeeId  部门标识
	 * @return
	 */
    DepartmentRankingVO getDepartmentCurrRankingByDepartmentId(Integer departmentId);
	
	/**
	 * 获取部门排名列表
	 * @return
	 */
	List<DepartmentRankingVO> getDepartmentRankingList();
    
}