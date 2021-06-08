package com.dh.dao;

import com.dh.model.AdminInfo;

import org.apache.ibatis.annotations.Param;

public interface AdminInfoMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(AdminInfo record);

    int insertSelective(AdminInfo record);

    AdminInfo selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(AdminInfo record);

    int updateByPrimaryKey(AdminInfo record);

    /**
     * 根据用户名获取用户信息
     * @param loginName
     * @return
     */
	AdminInfo getAdminInfoByLoginName(@Param("adminLoginName") String adminLoginName);
}