package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "员工列表")
public class PermissionEmpListVO {
	
	@ApiModelProperty(value = "主键标识")
    private Integer id;

    private Integer employeeId;

    private String employeeRealName;

    private String permission;

    private String value;

    private String departmentName;
	
}
