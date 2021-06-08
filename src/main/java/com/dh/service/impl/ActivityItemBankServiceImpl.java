package com.dh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dh.dao.ActivityItemBankMapper;
import com.dh.model.ActivityItemBank;
import com.dh.model.dto.ActivityItemBankDTO;
import com.dh.model.dto.ActivityItemBankParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.ActivityIBVO;
import com.dh.service.ActivityItemBankService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ActivityItemBankServiceImpl implements ActivityItemBankService {
	
	@Autowired
	private ActivityItemBankMapper aibMapper;

	// 获取活动试题详情
	@Override
	public ActivityItemBank getActivityItemBankInfo(Integer aibId) {
		return aibMapper.selectByPrimaryKey(aibId);
	}

	// 获取活动试题列表
	@Override
	public List<ActivityIBVO> getActivityItemBankAdminList(ActivityItemBankParamDTO paramDTo, PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<ActivityIBVO> list = aibMapper.getActivityItemBankAdminList(paramDTo);
		if(list == null || list.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		PageInfo<ActivityIBVO> pageInfo = new PageInfo<>(list);
		pageDTO.setTotleCount(pageInfo.getTotal());
		return list;
	}

	// 添加活动试题信息
	@Override
	public boolean insertActivityItemBankInfo(ActivityItemBank aib) {
		log.info("=========添加活动试题信息：" + JSONObject.toJSONString(aib));
		try {
			aib.setAibSort(getMaxSort(aib.getActivityId()));
			int res = aibMapper.insertSelective(aib);
			if(res > 0){
				log.info("=========添加活动试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====添加活动试题信息异常：", e);
		}
		return false;
	}
	
	// 修改活动试题信息
	@Override
	public boolean updateActivityItemBankInfo(ActivityItemBank aib) {
		log.info("=========修改活动试题信息：" + JSONObject.toJSONString(aib));
		try {
			int res = aibMapper.updateByPrimaryKeySelective(aib);
			if(res > 0){
				log.info("=========修改活动试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====修改活动试题信息异常：", e);
		}
		return false;
	}
	
	/**
	 * 获取最大排序值（+1）
	 * @param activityId 活动标识
	 * @return
	 */
	private Integer getMaxSort(Integer activityId){
		Integer maxSort = aibMapper.getMaxSort(activityId);
		return maxSort == null ? 1 : (maxSort + 1);
	}

	// 删除活动试题信息
	@Override
	public boolean deleteActivityItemBankInfo(Integer aibId) {
		log.info("=========删除活动试题信息：" + aibId);
		try {
			int res = aibMapper.deleteByPrimaryKey(aibId);
			if(res > 0){
				log.info("=========删除活动试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====删除活动试题信息异常：", e);
		}
		return false;
	}

	// 批量添加活动试题
	@Override
	public boolean batchInsertActivityItemBank(ActivityItemBankDTO aibDTO) {
		log.info("=========批量添加活动试题信息：" + JSONObject.toJSONString(aibDTO));
		try {
			List<ActivityItemBank> list = packageActivityItemBank(aibDTO);
			int res = aibMapper.insert(list);
			if(res > 0){
				log.info("=========批量添加活动试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====批量添加活动试题信息异常：", e);
		}
		return false;
	}
	
	/**
	 * 组装活动试题
	 * @param aibDTO
	 * @return
	 */
	private List<ActivityItemBank> packageActivityItemBank(ActivityItemBankDTO aibDTO){
		Integer maxSort = getMaxSort(aibDTO.getActivityId());
		List<ActivityItemBank> list = new ArrayList<>();
		ActivityItemBank aib = null;
		for(Integer itemBankId : aibDTO.getItemBankIdList()){
			aib = new ActivityItemBank();
			aib.setActivityId(aibDTO.getActivityId());
			aib.setAibScore(aibDTO.getAibScore());
			aib.setItemBankId(itemBankId);
			aib.setAibSort(maxSort);
			maxSort ++;
			list.add(aib);
		}
		return list;
	}

	// 批量删除活动试题信息
	@Override
	public boolean batchDeleteActivityItemBankInfo(List<Integer> aibIdList) {
		log.info("=========批量删除活动试题信息：" + JSONObject.toJSONString(aibIdList));
		try {
			int res = aibMapper.batchDeleteActivityItemBankInfo(aibIdList);
			if(res > 0){
				log.info("=========批量删除活动试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====批量删除活动试题信息异常：", e);
		}
		return false;
	}

	// 添加活动试题
	@Override
	public boolean addActivityItemBankInfo(List<ActivityItemBank> aibList) {
		log.info("=========批量添加活动试题信息：" + JSONObject.toJSONString(aibList));
		try {
			aibMapper.deleteActivityItemBankByActivityId(aibList.get(0).getActivityId());
			int res = aibMapper.addActivityItemBankInfo(aibList);
			if(res > 0){
				log.info("=========批量添加活动试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====批量添加活动试题信息异常：", e);
		}
		return false;
	}

	// 根据活动标识获取活动试题
	@Override
	public List<ActivityIBVO> getActivityItemBankAdminList(Integer activityId) {
		List<ActivityIBVO> list = aibMapper.getAibListByActivityId(activityId);
		if(list == null) list = new ArrayList<>();
		return list;
	}

}
