package com.dh.dao;

import com.dh.model.dto.ItemBankDelDTO;
import com.dh.model.dto.ItemBankParamDTO;
import com.dh.model.vo.ItemBankInfoVO;
import com.dh.model.vo.ItemBankListVO;
import com.dh.model.ItemBank;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemBankMapper {
	
    /**
     * 获取试题列表
     * @param paramDTO
     * @return
     */
	List<ItemBankListVO> getItemBankList(ItemBankParamDTO paramDTO);

	/**
	 * 获取试题详情
	 * @param itemBankId
	 * @return
	 */
	ItemBankInfoVO getItemBankInfo(@Param("itemBankId") Integer itemBankId);
	
	/**
	 * 添加试题信息
	 * @param record
	 * @return
	 */
	int insertSelective(ItemBank record);

	/**
	 * 修改试题信息
	 * @param record
	 * @return
	 */
    int updateByPrimaryKeySelective(ItemBank record);

    /**
     * 删除试题信息
     * @param ibDTO
     * @return
     */
	int deleteItemBankInfo(ItemBankDelDTO ibDTO);
	
}