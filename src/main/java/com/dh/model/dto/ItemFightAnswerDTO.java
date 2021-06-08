package com.dh.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "试题答案信息")
public class ItemFightAnswerDTO {

	@ApiModelProperty(value = "答题用时（秒）")
	private Integer answerUseTime;

	@ApiModelProperty(value = "对战号")
	private Integer matchId;

	@ApiModelProperty(value = "房间号")
	private Integer roomId;

	@ApiModelProperty(value = "题目id")
	private Integer itemBankId;

	@ApiModelProperty(value = "试题答案")
	private List<Integer> ibAnswerList;

}
