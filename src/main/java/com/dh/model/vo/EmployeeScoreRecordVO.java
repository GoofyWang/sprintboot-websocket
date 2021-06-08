package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "员工分数")
public class EmployeeScoreRecordVO {

	@ApiModelProperty(value = "分数")
    private Integer esrScore;

	@ApiModelProperty(value = "类型名称")
    private String esrTypeName;

	@ApiModelProperty(value = "创建时间")
    private String createTime;
	
}
