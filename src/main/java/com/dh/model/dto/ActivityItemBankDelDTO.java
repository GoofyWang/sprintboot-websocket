package com.dh.model.dto;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "活动试题删除")
public class ActivityItemBankDelDTO {

	@ApiModelProperty(value = "活动试题标识  两个字段不能全部为空")
    private Integer aibId;

	@ApiModelProperty(value = "活动试题标识list 两个字段不能全部为空")
    private List<Integer> aibIdList;

}
