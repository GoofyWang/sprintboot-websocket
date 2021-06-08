package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("登录成功返回信息")
public class LoginSuccessVO {

	@ApiModelProperty(value = "token")
	private String token;
	
	@ApiModelProperty(value = "Y 已上传  N 未上传")
	private String mobileStatus;
	
	@ApiModelProperty(value = "Y 已完善  N 未完善")
	private String infoStatus;
	
	public LoginSuccessVO(String token) {
		this.token = token;
	}
	
	public LoginSuccessVO() {}
}
