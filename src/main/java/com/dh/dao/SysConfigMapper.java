package com.dh.dao;

import com.dh.model.SysConfig;

import java.util.List;

public interface SysConfigMapper {

	List<SysConfig> selectAll();

	SysConfig selectByPrimaryKey(Integer id);

	SysConfig selectByName(String name);

    int updateByPrimaryKeySelective(SysConfig sysConfig);

	int insert(SysConfig sysConfig);
	
}