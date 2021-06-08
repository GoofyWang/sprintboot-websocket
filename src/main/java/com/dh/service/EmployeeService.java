package com.dh.service;

import com.dh.model.Employee;
import com.dh.model.EmployeeLogin;
import com.dh.model.dto.EmployeeListDTO;
import com.dh.model.dto.MobileParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.EmployeeFalseItemBankVO;
import com.dh.model.vo.EmployeeListVO;
import com.dh.model.vo.EmployeeRankingVO;
import com.dh.model.vo.EmployeeScoreRecordVO;
import com.dh.model.vo.EmployeeUserVO;

import java.util.List;

public interface EmployeeService {
	
	/**
	 * 根据主键查询用户信息
	 * @param employeeId
	 * @return
	 */
	EmployeeUserVO getEmployeeInfoById(Integer employeeId);

	/**
	 * 根据openid获取用户信息
	 * @param openid
	 * @return
	 */
	Employee getEmployeeInfoByOpenid(String openid);
	
	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 */
	Employee getEmployeeInfoByToken(String token);
	
	/**
	 * 获取手机号
	 * @param mobileDTO  加密数据
	 * @param empSessionKey 
	 * @return
	 */
	String getUserMobile(MobileParamDTO mobileDTO, String empSessionKey);
	
	/**
	 * 添加员工信息
	 * @param employee 员工信息
	 * @param empLogin 员工登录信息
	 * @return
	 */
	boolean insertEmployeeInfo(Employee employee, EmployeeLogin empLogin);
	
	/**
	 * 修改员工信息
	 * @param employee 员工信息
	 * @param empLogin 员工登录信息
	 * @return
	 */
	boolean updateEmployeeInfo(Employee employee, EmployeeLogin empLogin);
	
	/**
	 * 修改登录信息
	 * @param empLogin
	 * @return
	 */
	boolean updateEmployeeLoginInfo(EmployeeLogin empLogin);

	/**
	 * 获取员工积分列表
	 * @param employeeId  员工标识
	 * @param pageDTO     分页信息
	 * @return
	 */
	List<EmployeeScoreRecordVO> getEmployeeScoreRecordList(Integer employeeId, PageInfoDTO pageDTO);

	/**
	 * 分页获取员工错题信息
	 * @param pageDTO     分页信息
	 * @param employeeId  员工标识
	 * @return
	 */
	List<EmployeeFalseItemBankVO> getEmployeeFalseItemBankList(PageInfoDTO pageDTO, Integer employeeId);

	/**
	 * 获取员工排名信息
	 * @param employeeId 员工标识
	 * @return
	 */
	EmployeeRankingVO getEmployeeRankingInfo(Integer employeeId);

	/**
	 * 获取员工排名列表
	 * @param pageDTO 分页信息
	 * @return
	 */
	List<EmployeeRankingVO> getEmployeeRankingList(PageInfoDTO pageDTO);

	/**
	 * 根据部门获取员工列表
	 * @param paramDTO
	 * @param pageDTO
	 * @return
	 */
	List<EmployeeListVO> getDepartmentEmployeeList(EmployeeListDTO paramDTO, PageInfoDTO pageDTO);

	/**
	 * 修改员工隔离状态
	 * @param employee
	 * @return
	 */
	boolean quarantineStatusEdit(Employee employee);
	
}
