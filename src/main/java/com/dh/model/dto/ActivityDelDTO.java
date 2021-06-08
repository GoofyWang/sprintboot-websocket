package com.dh.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "活动删除")
public class ActivityDelDTO {
	
	@ApiModelProperty(value = "活动标识")
    private Integer activityId;

	@ApiModelProperty(value = "修改人", hidden = true)
    private Integer updateId;

	@ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;

	@ApiModelProperty(value = "可用状态 Y 可用 N 不可用", hidden = true)
    private String status;

}
