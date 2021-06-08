package com.dh.dao;

import com.dh.model.DepartmentScoreLog;
import com.dh.model.FightLog;

import java.util.List;

public interface DepartmentScoreLogMapper {

    int createLog(DepartmentScoreLog departmentScoreLog);

    int getCount(DepartmentScoreLog departmentScoreLog);

}