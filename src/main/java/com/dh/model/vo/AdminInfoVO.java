package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "管理员信息")
public class AdminInfoVO {

	@ApiModelProperty(value = "管理员标识")
	private Integer adminId;

	@ApiModelProperty(value = "管理员名称")
    private String adminName;

	@ApiModelProperty(value = "管理员性别")
    private String adminSex;

	@ApiModelProperty(value = "管理员头像")
    private String adminHeadImage;
	
	@ApiModelProperty(value = "登录token")
	private String token;

}
