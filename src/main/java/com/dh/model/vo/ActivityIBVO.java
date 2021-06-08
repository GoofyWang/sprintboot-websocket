package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "活动试题信息")
public class ActivityIBVO {

	@ApiModelProperty(value = "活动标识")
    private Integer activityId;
	
	@ApiModelProperty(value = "试题标识")
    private Integer itemBankId;
	
	@ApiModelProperty(value = "试题标题")
	private String itemBankTitle;

	@ApiModelProperty(value = "活动试题分数")
    private Integer aibScore;

}
