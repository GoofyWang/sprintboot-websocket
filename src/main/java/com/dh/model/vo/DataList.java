package com.dh.model.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "数据")
public class DataList<T> {

	@ApiModelProperty(value = "数据总条数(分页查询时用)", example = "10")
	private Long count;
	
	@ApiModelProperty(value = "响应数据")
	private T data;
	
	public DataList() {
	}

	public DataList(Long count, T data) {
		this.count = count;
		this.data = data;
	}
	
}
