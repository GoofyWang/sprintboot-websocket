package com.dh.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "活动试题多条数据")
public class ActivityItemBankParamDTO {

	@ApiModelProperty(value = "试题标题")
    private String itemBankTitle;

    @ApiModelProperty(value = "活动标识")
	private Integer activityId;

}
