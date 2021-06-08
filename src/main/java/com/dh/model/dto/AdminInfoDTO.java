package com.dh.model.dto;

import com.dh.annotation.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "登录参数")
public class AdminInfoDTO {

	@NotNull
	@ApiModelProperty(value = "登录名", example = "zhangsan", required = true)
    private String adminLoginName;
	
	@NotNull
	@ApiModelProperty(value = "密码（MD5加密后）", example = "111111", required = true)
	private String adminLoginPwd;
	
	@ApiModelProperty(value = "验证码", example = "1234", hidden = true)
	private String verifyCode;
	
}
