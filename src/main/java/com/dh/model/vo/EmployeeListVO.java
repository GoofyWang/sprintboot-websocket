package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "员工列表")
public class EmployeeListVO {
	
	@ApiModelProperty(value = "主键标识")
    private Integer employeeId;

	@ApiModelProperty(value = "员工昵称")
    private String employeeNickname;

	@ApiModelProperty(value = "员工性别 0 未知 1 男 2 女")
    private String employeeSex;

	@ApiModelProperty(value = "员工真实姓名")
    private String employeeRealName;

	@ApiModelProperty(value = "员工隔离状态 Y 隔离  N 不隔离")
	private String employeeQuarantineStatus;
	
	@ApiModelProperty(value = "部门名称")
    private String departmentName;
	
}
