package com.dh.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "手机号信息")
public class MobileParamDTO {
	
	@ApiModelProperty(value = "加密数据")
	private String encryptedData;
	
	@ApiModelProperty(value = "iv")
	private String iv;

}
