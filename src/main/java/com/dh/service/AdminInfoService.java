package com.dh.service;

import com.dh.model.AdminInfo;

public interface AdminInfoService {
	
	/**
	 * 根据用户名获取用户信息
	 * @param loginName
	 * @return
	 */
	public AdminInfo getAdminInfoByLoginName(String loginName);
	
}
