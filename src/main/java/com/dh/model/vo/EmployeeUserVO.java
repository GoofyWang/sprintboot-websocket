package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "员工信息")
public class EmployeeUserVO {
	
	@ApiModelProperty(value = "主键标识")
    private Integer employeeId;

	@ApiModelProperty(value = "员工昵称")
    private String employeeNickname;

	@ApiModelProperty(value = "员工头像")
    private String employeeHeadImage;

	@ApiModelProperty(value = "员工性别 0 未知 1 男 2 女")
    private String employeeSex;

	@ApiModelProperty(value = "员工手机号")
    private String employeeMobile;

	@ApiModelProperty(value = "员工真实姓名")
    private String employeeRealName;

	@ApiModelProperty(value = "员工年龄")
    private Integer employeeAge;

	@ApiModelProperty(value = "部门标识")
    private Integer departmentId;
	
	@ApiModelProperty(value = "部门名称")
    private String departmentName;
	
	@ApiModelProperty(value = "员工隔离状态 Y 隔离  N 不隔离")
	private String employeeQuarantineStatus;
	
	@ApiModelProperty(value = "答题总数")
	private Integer employeeAnswerTotalCount;
	
	@ApiModelProperty(value = "答题正确数")
	private Integer employeeAnswerTrueCount;
	
	@ApiModelProperty(value = "答题错误数")
	private Integer employeeAnswerFalseCount;
	
	@ApiModelProperty(value = "总积分")
	private Integer employeeTotalScore;
	
	@ApiModelProperty(value = "胜率")
	private String employeeWinRate;

}
