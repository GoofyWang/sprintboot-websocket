package com.dh.service.impl;

import com.dh.dao.AdminInfoMapper;
import com.dh.model.AdminInfo;
import com.dh.service.AdminInfoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminInfoServiceImpl implements AdminInfoService{

	@Autowired
	private AdminInfoMapper adminInfoMapper;
	
	// 根据用户名获取用户信息
	@Override
	public AdminInfo getAdminInfoByLoginName(String loginName) {
		return adminInfoMapper.getAdminInfoByLoginName(loginName);
	}

}
