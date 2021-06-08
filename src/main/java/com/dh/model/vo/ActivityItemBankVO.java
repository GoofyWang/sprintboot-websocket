package com.dh.model.vo;

import com.dh.model.Answer;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "活动试题信息")
public class ActivityItemBankVO {

	@ApiModelProperty(value = "活动标识")
	private Integer activityId;

	@ApiModelProperty(value = "试题标识")
    private Integer itemBankId;
	
	@ApiModelProperty(value = "试题标题")
	private String itemBankTitle;

	@ApiModelProperty(value = "活动试题分数")
    private Integer aibScore;

	@ApiModelProperty(value = "活动试题顺序")
    private Integer aibSort;
	
	@ApiModelProperty(value = "试题解析", example = "1")
    private String itemBankAnalysis;

	@ApiModelProperty(value = "试题类型 1 单选 2 多选 3 判断", example = "1")
    private String itemBankType;
	
	@ApiModelProperty(value = "答案列表")
	private List<Answer> answerList;
    
}
