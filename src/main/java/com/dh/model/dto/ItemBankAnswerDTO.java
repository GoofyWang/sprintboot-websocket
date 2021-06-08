package com.dh.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "试题答案信息")
public class ItemBankAnswerDTO {
	
	@ApiModelProperty(value = "答题用时（秒）")
	private Integer answerUseTime;
	
	@ApiModelProperty(value = "活动标识")
	private Integer activityId;
	
	@ApiModelProperty(value = "试题答案")
	private List<IBAnswerDTO> ibAnswerList;

}
