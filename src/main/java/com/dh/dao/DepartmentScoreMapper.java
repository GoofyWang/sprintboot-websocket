package com.dh.dao;

import com.dh.model.DepartmentScore;
import com.dh.model.vo.DepartmentScoreListVO;

import java.util.List;

public interface DepartmentScoreMapper {

    int createLog(DepartmentScore departmentScore);

    void addScore(DepartmentScore departmentScore);

    DepartmentScore getInfo(Integer DepartmentId);

    List<DepartmentScoreListVO> getList(Integer Limit);

}