package com.dh.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "员工查询参数")
public class EmployeeListDTO {
	
	@ApiModelProperty(value = "部门标识")
	private Integer departmentId;
	
	@ApiModelProperty(value = "员工名称")
	private String employeeName;

}
