package com.dh.model.dto;

import com.dh.annotation.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("用户微信小程序登录注册参数")
public class UserWechatLoginParamDTO {

	@ApiModelProperty(value = "登录code", example = "111", required = true)
	@NotNull(message = "code不能为空")
	private String code;
	
	@ApiModelProperty(value = "昵称", example = "zhangsan", required = true)
	@NotNull(message = "昵称不能为空")
	private String nickname;
	
	@ApiModelProperty(value = "头像", example = "/head/image", required = true)
	@NotNull(message = "头像不能为空")
	private String avatar;
	
	@ApiModelProperty(value = "性别", example = "1", required = true)
	@NotNull(message = "性别不能为空")
	private String gender;
	
	
}
