package com.dh.dao;

import com.dh.model.Employee;
import com.dh.model.dto.EmployeeListDTO;
import com.dh.model.vo.EmployeeListVO;
import com.dh.model.vo.EmployeeRankingVO;
import com.dh.model.vo.EmployeeUserVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {

	/**
	 * 添加员工信息
	 * @param record
	 * @return
	 */
    int insertSelective(Employee record);

    Employee selectByPrimaryKey(Integer employeeId);

    /**
     * 修改员工信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Employee record);

    /**
     * 获取用户信息
     * @param employeeId 用户标识
     * @return
     */
	EmployeeUserVO getEmployeeInfoById(Integer employeeId);
	
	/**
	 * 根据员工标识ids获取员工信息
	 * @param employeeId 用户标识
	 * @return
	 */
	List<EmployeeRankingVO> getEmployeeRankingListByIds(@Param("list") List<Integer> list);
	
	/**
	 * 根据员工标识获取员工信息
	 * @param employeeId 用户标识
	 * @return
	 */
	EmployeeRankingVO getEmployeeRankingInfoById(@Param("employeeId") Integer employeeId);

	/**
	 * 根据openid获取员工信息
	 * @param openid
	 * @return
	 */
	Employee getEmployeeInfoByOpenid(@Param("openid") String openid);

	/**
	 * 根据token获取员工信息
	 * @param token
	 * @return
	 */
	Employee getEmployeeInfoByToken(@Param("token") String token);

	/**
	 * 根据条件获取员工列表
	 * @param paramDTO
	 * @return
	 */
	List<EmployeeListVO> getDepartmentEmployeeList(EmployeeListDTO paramDTO);

	/**
	 * 修改员工隔离状态
	 * @param employee
	 * @return
	 */
	int quarantineStatusEdit(Employee employee);
}