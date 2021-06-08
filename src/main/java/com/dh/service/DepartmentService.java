package com.dh.service;

import com.dh.model.Department;
import com.dh.model.dto.DepartmentParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.DepartmentInfoVO;
import com.dh.model.vo.DepartmentListVO;
import com.dh.model.vo.DepartmentRankingVO;

import java.util.List;

public interface DepartmentService {
	
	/**
	 * 获取部门列表（不分页）
	 * @return
	 */
	public List<DepartmentListVO> getDepartmentList(DepartmentParamDTO paramDTO);
	
	/**
	 * 分页获取部门列表
	 * @param paramDTO  入参
	 * @param pageDTO   分页信息
	 * @return
	 */
	public List<DepartmentListVO> getDepartmentList(DepartmentParamDTO paramDTO, PageInfoDTO pageDTO);

	/**
	 * 获取部门信息
	 * @param departmentId 主键标识
	 * @return
	 */
	public DepartmentInfoVO getDepartmentInfo(Integer departmentId);
	
	/**
	 * 添加部门信息
	 * @param department  部门信息
	 * @return
	 */
	public boolean insertDepartmentInfo(Department department);
	
	/**
	 * 修改部门信息
	 * @param department  部门信息
	 * @return
	 */
	public boolean updateDepartmentInfo(Department department);

	/**
	 * 获取部门排名信息
	 * @param departmentId  部门标识
	 * @return
	 */
	public DepartmentRankingVO getDepartmentRankingInfo(Integer departmentId);

	/**
	 * 获取部门排名列表
	 * @param pageDTO
	 * @return
	 */
	public List<DepartmentRankingVO> getDepartmentRankingList(PageInfoDTO pageDTO);
	
}
