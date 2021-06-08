package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "答题结果")
public class ItemBankAnswerResultVO {
	
	@ApiModelProperty(value = "答题用时")
	private Integer answerUseTime;
	
	@ApiModelProperty(value = "挑战状态 Y 成功 N 失败")
	private String challengeStatus;
	
	@ApiModelProperty(value = "增加积分")
	private Integer addScore;
	
	@ApiModelProperty(value = "当前积分")
	private Integer currScore;
	
	@ApiModelProperty(value = "是否是擂主 Y 是  N 否")
	private String winner;

}
