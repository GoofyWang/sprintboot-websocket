package com.dh.dao;

import com.dh.model.Permission;
import com.dh.model.SysConfig;
import com.dh.model.vo.PermissionEmpListVO;

import java.util.List;

public interface PermissionMapper {

	List<SysConfig> selectAll();

	Permission selectByPrimaryKey(Integer id);

	Permission selectPermissionByPermission(Permission permission);

	int updateByPrimaryKeySelective(Permission permission);

	int insert(Permission permission);

	int deleteByPrimaryKeySelective(Permission permission);

	List<PermissionEmpListVO> getRoomPermissionList();
}