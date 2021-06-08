package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "部门列表")
public class DepartmentListVO {
	
	@ApiModelProperty(value = "部门标识", example = "1")
    private Integer departmentId;

	@ApiModelProperty(value = "部门名称", example = "1")
    private String departmentName;

	@ApiModelProperty(value = "部门父标识", example = "1")
    private Integer departmentParentId;

	@ApiModelProperty(value = "部门级别", example = "1")
    private Integer departmentLevel;

	@ApiModelProperty(value = "部门顺序", example = "1")
    private Integer departmentSort;
}
