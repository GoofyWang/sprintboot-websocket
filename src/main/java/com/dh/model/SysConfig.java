package com.dh.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "配置")
public class SysConfig {
	
	@ApiModelProperty(value = "主键", example = "1")
    private Integer id;

	@ApiModelProperty(value = "配置名字", example = "1")
    private String name;

	@ApiModelProperty(value = "配置", example = "1")
    private String value;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}