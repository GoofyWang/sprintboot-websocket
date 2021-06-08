package com.dh.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "试题答案")
public class IBAnswerDTO {
	
	@ApiModelProperty(value = "试题标识")
	private Integer itemBankId;
	
	@ApiModelProperty(value = "答案标识list")
	private List<Integer> answerIdList;

}
