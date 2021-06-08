package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("响应数据")
public class ReturnResult<T> {
	
	@ApiModelProperty(value = "响应码", example = "200")
	private Integer code = 200;
	
	@ApiModelProperty(value = "响应信息", example = "成功")
	private String msg = "成功";
	
	@ApiModelProperty(value = "响应数据")
	private T data;

	public ReturnResult(T data) {
		this.data = data;
	}

	public ReturnResult() {
	}

	public ReturnResult(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

}
