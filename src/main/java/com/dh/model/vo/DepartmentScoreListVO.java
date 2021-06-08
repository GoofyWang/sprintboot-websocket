package com.dh.model.vo;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "部门列表")
public class DepartmentScoreListVO {

    private Integer Id;

    private Integer DepartmentId;

    private Integer Score;

    private Date UpdateTime;

    private String DepartmentName;

}
