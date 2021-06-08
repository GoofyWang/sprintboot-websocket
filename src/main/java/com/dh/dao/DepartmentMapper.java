package com.dh.dao;

import com.dh.model.Department;
import com.dh.model.dto.DepartmentParamDTO;
import com.dh.model.vo.DepartmentInfoVO;
import com.dh.model.vo.DepartmentListVO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {

	/**
	 * 添加部门信息
	 * @param record
	 * @return
	 */
    int insertSelective(Department record);

    /**
     * 修改部门信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Department record);

    /**
     * 获取部门信息
     * @param departmentId
     * @return
     */
	DepartmentInfoVO getDepartmentInfo(Integer departmentId);
	
	/**
     * 根据部门ids获取部门列表
     * @param departmentId
     * @return
     */
	List<DepartmentInfoVO> getDepartmentListByIds(@Param("list") List<Integer> list);

	/**
	 * 获取部门列表
	 * @param paramDTO
	 * @return
	 */
	List<DepartmentListVO> getDepartmentList(DepartmentParamDTO paramDTO);
	
}