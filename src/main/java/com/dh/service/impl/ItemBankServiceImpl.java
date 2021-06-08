package com.dh.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dh.dao.AnswerMapper;
import com.dh.dao.ItemBankMapper;
import com.dh.model.Answer;
import com.dh.model.ItemBank;
import com.dh.model.dto.ItemBankDelDTO;
import com.dh.model.dto.ItemBankParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.ItemBankInfoVO;
import com.dh.model.vo.ItemBankListVO;
import com.dh.service.ItemBankService;
import com.dh.util.SystemConstants;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ItemBankServiceImpl implements ItemBankService{
	
	@Autowired
	private ItemBankMapper itemBankMapper;
	
	@Autowired
	private AnswerMapper answerMapper;
	
	// 获取试题列表
	@Override
	public List<ItemBankListVO> getItemBankList(ItemBankParamDTO paramDTO, PageInfoDTO pageDTO) {
		PageHelper.startPage(pageDTO.getPage() + 1, pageDTO.getPageSize());
		List<ItemBankListVO> list = itemBankMapper.getItemBankList(paramDTO);
		if(list == null || list.size() == 0){
			pageDTO.setTotleCount(0L);
			return new ArrayList<>();
		}
		PageInfo<ItemBankListVO> pageInfo = new PageInfo<>(list);
		pageDTO.setTotleCount(pageInfo.getTotal());
		return list;
	}

	// 获取试题详情
	@Override
	public ItemBankInfoVO getItemBankInfo(Integer itemBankId) {
		return itemBankMapper.getItemBankInfo(itemBankId);
	}

	// 添加试题
	@Override
	public boolean insertItemBankInfo(ItemBank itemBank) {
		log.info("=========添加试题信息：" + JSONObject.toJSONString(itemBank));
		try {
			disposeItemBank(itemBank);
			int res = itemBankMapper.insertSelective(itemBank);
			if(res > 0){
				insertAnswerList(itemBank.getAnswerList(), itemBank.getItemBankId());
				log.info("=========添加试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====添加试题信息异常：", e);
		}
		return false;
	}
	
	/**
	 * 处理正确答案
	 * @param itemBank
	 */
	private void disposeItemBank(ItemBank itemBank){
		List<String> answerList = itemBank.getAnswerList().stream().filter(a -> SystemConstants.Y.equals(a.getAnswerStatus()))
								.map(Answer::getAnswerContent).collect(Collectors.toList());
		itemBank.setItemBankAnswer(JSONObject.toJSONString(answerList));
	}

	// 修改试题
	@Override
	public boolean updateItemBankInfo(ItemBank itemBank) {
		log.info("=========修改试题信息：" + JSONObject.toJSONString(itemBank));
		try {
			disposeItemBank(itemBank);
			int res = itemBankMapper.updateByPrimaryKeySelective(itemBank);
			if(res > 0){
				deleteAnswerByItemBankId(itemBank.getItemBankId());
				insertAnswerList(itemBank.getAnswerList(), itemBank.getItemBankId());
				log.info("=========修改试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====修改试题信息异常：", e);
		}
		return false;
	}

	// 删除试题
	@Override
	public boolean deleteItemBankInfo(ItemBankDelDTO ibDTO) {
		log.info("=========删除试题信息：" + JSONObject.toJSONString(ibDTO));
		try {
			int res = itemBankMapper.deleteItemBankInfo(ibDTO);
			if(res > 0){
				log.info("=========删除试题信息结束=============");
				return true;
			}
		} catch (Exception e) {
			log.error("====删除试题信息异常：", e);
		}
		return false;
	}
	
	/**
	 * 添加答案信息
	 * @param answerList   答案list
	 * @param itembankId   试题标识
	 */
	private void insertAnswerList(List<Answer> answerList, Integer itembankId){
		log.info("=====添加试题答案信息，试题标识：{}， 答案内容：{}", itembankId, JSONObject.toJSONString(answerList));
		try {
			int res = answerMapper.insert(answerList, itembankId);
			if(res > 0){
				log.info("======添加试题答案成功======");
			}
		} catch (Exception e) {
			log.error("=====添加试题答案异常：", e);
		}
	}
	
	/**
	 * 根据试题删除答案信息
	 * @param itembankId
	 */
	private void deleteAnswerByItemBankId(Integer itembankId){
		log.info("=====删除试题答案信息，试题标识：{}", itembankId);
		try {
			int res = answerMapper.deleteAnswerByItemBankId(itembankId);
			if(res > 0){
				log.info("======删除试题答案成功======");
			}
		} catch (Exception e) {
			log.error("=====删除试题答案异常：", e);
		}
	}

}
