package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "试题列表")
public class ItemBankListVO {
	
	@ApiModelProperty(value = "试题标识", example = "1")
    private Integer itemBankId;

	@ApiModelProperty(value = "试题标题", example = "1")
    private String itemBankTitle;

	@ApiModelProperty(value = "试题解析", example = "1")
    private String itemBankAnalysis;

	@ApiModelProperty(value = "试题类型 1 单选 2 多选 3 判断", example = "1")
    private String itemBankType;

}
