package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "员工分数")
public class EmployeeScoreVO {
	
	@ApiModelProperty(value = "员工标识")
	private Integer employeeId;
	
	@ApiModelProperty(value = "员工得分")
	private Integer employeeScore;
	
	@ApiModelProperty(value = "员工得分时间")
	private String employeeScoreTime;

}
