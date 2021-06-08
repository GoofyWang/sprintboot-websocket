package com.dh.dao;

import com.dh.model.EmployeeLogin;

public interface EmployeeLoginMapper {
    int deleteByPrimaryKey(Integer employeeLoginId);

    int insert(EmployeeLogin record);

    int insertSelective(EmployeeLogin record);

    EmployeeLogin selectByPrimaryKey(Integer employeeLoginId);

    int updateByPrimaryKeySelective(EmployeeLogin record);

    int updateByPrimaryKey(EmployeeLogin record);

}