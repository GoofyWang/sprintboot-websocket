package com.dh.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "活动参数")
public class ActivityParamDTO {

	@ApiModelProperty(value = "活动名称")
    private String activityName;

	@ApiModelProperty(value = "活动开始时间")
    private String activityStartTime;

	@ApiModelProperty(value = "活动结束时间")
    private String activityEndTime;

}
