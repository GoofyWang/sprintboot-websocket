package com.dh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dh.dao.DepartmentMapper;
import com.dh.dao.DepartmentRankingMapper;
import com.dh.model.Department;
import com.dh.model.dto.DepartmentParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.DepartmentInfoVO;
import com.dh.model.vo.DepartmentListVO;
import com.dh.model.vo.DepartmentRankingVO;
import com.dh.service.DepartmentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

/**
 * 部门service
 */
@Slf4j
@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	@Autowired
	private DepartmentMapper departmentMapper;
	
	@Autowired
	private DepartmentRankingMapper deptRankingMapper;

	// 获取部门列表（不分页）
	@Override
	public List<DepartmentListVO> getDepartmentList(DepartmentParamDTO paramDTO) {
		List<DepartmentListVO> list = departmentMapper.getDepartmentList(paramDTO);
		if(list == null) list = new ArrayList<>();
		return list;
	}

	// 分页获取部门列表
	@Override
	public List<DepartmentListVO> getDepartmentList(DepartmentParamDTO paramDTO, PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<DepartmentListVO> list = departmentMapper.getDepartmentList(paramDTO);
		if(list == null || list.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		PageInfo<DepartmentListVO> pageInfo = new PageInfo<>(list);
		pageDTO.setTotleCount(pageInfo.getTotal());
		return list;
	}

	// departmentId
	@Override
	public DepartmentInfoVO getDepartmentInfo(Integer departmentId) {
		return departmentMapper.getDepartmentInfo(departmentId);
	}

	// 添加部门信息
	@Override
	public boolean insertDepartmentInfo(Department department) {
		log.info("=========添加部门信息：" + JSONObject.toJSONString(department));
		try {
			int res = departmentMapper.insertSelective(department);
			if(res > 0){
				log.info("=========添加部门信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====添加部门信息异常：", e);
		}
		return false;
	}

	// 修改部门信息
	@Override
	public boolean updateDepartmentInfo(Department department) {
		log.info("=========修改部门信息：" + JSONObject.toJSONString(department));
		try {
			int res = departmentMapper.updateByPrimaryKeySelective(department);
			if(res >= 0){
				log.info("=========修改部门信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====修改部门信息异常：", e);
		}
		return false;
	}

	// 获取部门排名信息
	@Override
	public DepartmentRankingVO getDepartmentRankingInfo(Integer departmentId) {
		DepartmentRankingVO deptRank = deptRankingMapper.getDepartmentCurrRankingByDepartmentId(departmentId);
		if(deptRank == null){
			deptRank = new DepartmentRankingVO();
			DepartmentInfoVO departmentInfo = departmentMapper.getDepartmentInfo(departmentId);
			if(departmentInfo != null){
				deptRank.setDepartmentParentId(departmentInfo.getDepartmentParentId());
				deptRank.setDepartmentName(departmentInfo.getDepartmentName());
				deptRank.setDepartmentId(departmentId);
			}
		}
		DepartmentInfoVO departmentInfo = departmentMapper.getDepartmentInfo(deptRank.getDepartmentParentId());
		if(departmentInfo != null){
			deptRank.setDepartmentParentName(departmentInfo.getDepartmentName());
		}
		return deptRank;
	}

	// 获取部门排名列表
	@Override
	public List<DepartmentRankingVO> getDepartmentRankingList(PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<DepartmentRankingVO> deptRankList = deptRankingMapper.getDepartmentRankingList();
		if(deptRankList == null || deptRankList.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		List<Integer> deptIdList = deptRankList.stream().map(DepartmentRankingVO::getDepartmentParentId)
											   .distinct().collect(Collectors.toList());
		Map<Integer, String> deptMap = new HashMap<>();
		if(deptIdList != null && deptIdList.size() > 0){
			List<DepartmentInfoVO> departmentList = departmentMapper.getDepartmentListByIds(deptIdList);
			if(departmentList != null && departmentList.size() > 0){
				departmentList.forEach(d -> deptMap.put(d.getDepartmentId(), d.getDepartmentName()));
			}
		}
		PageInfo<DepartmentRankingVO> pageInfo = new PageInfo<>(deptRankList);
		pageDTO.setTotleCount(pageInfo.getTotal());
		deptRankList.forEach(er -> er.setDepartmentParentName(deptMap.get(er.getDepartmentParentId())));
		return deptRankList;
	}

}
