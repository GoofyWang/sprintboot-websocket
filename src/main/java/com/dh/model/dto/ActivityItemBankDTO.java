package com.dh.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "活动试题多条数据")
public class ActivityItemBankDTO {

	@ApiModelProperty(value = "活动试题标识")
    private Integer aibId;

    @ApiModelProperty(value = "活动标识")
	private Integer activityId;

	@ApiModelProperty(value = "试题标识list")
    private List<Integer> itemBankIdList;

	@ApiModelProperty(value = "活动试题分数")
    private Integer aibScore;

}
