package com.dh.service;

import com.dh.model.dto.ItemBankDelDTO;
import com.dh.model.dto.ItemBankParamDTO;
import com.dh.model.dto.PageInfoDTO;
import com.dh.model.vo.ItemBankInfoVO;
import com.dh.model.vo.ItemBankListVO;
import com.dh.model.ItemBank;

import java.util.List;

public interface ItemBankService {
	
	/**
	 * 获取试题列表
	 * @param paramDTO
	 * @param pageDTO
	 * @return
	 */
	public List<ItemBankListVO> getItemBankList(ItemBankParamDTO paramDTO, PageInfoDTO pageDTO);
	
	/**
	 * 获取试题详情
	 * @param itemBankId
	 * @return
	 */
	public ItemBankInfoVO getItemBankInfo(Integer itemBankId);
	
	/**
	 * 添加试题
	 * @param itemBank
	 * @return
	 */
	public boolean insertItemBankInfo(ItemBank itemBank);
	
	/**
	 * 修改试题
	 * @param itemBank
	 * @return
	 */
	public boolean updateItemBankInfo(ItemBank itemBank);
	
	/**
	 * 删除试题
	 * @param ibDTO
	 * @return
	 */
	public boolean deleteItemBankInfo(ItemBankDelDTO ibDTO);

}
