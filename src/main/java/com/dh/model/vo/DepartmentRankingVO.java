package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "部门排行")
public class DepartmentRankingVO {
	
	@ApiModelProperty(value = "主键标识")
    private Integer departmentId;

	@ApiModelProperty(value = "部门名称")
	private String departmentName;

	@ApiModelProperty(value = "上级部门名称")
	private String departmentParentName;
	
	@ApiModelProperty(value = "部门父标识", hidden = true)
	private Integer departmentParentId;

	@ApiModelProperty(value = "总积分")
	private Integer departmentTotalScore = 0;
	
	@ApiModelProperty(value = "总用时")
	private Integer departmentTotalUseTime = 0; 
	
	@ApiModelProperty(value = "名次")
	private Integer departmentRankingNumber = 9999;

}
