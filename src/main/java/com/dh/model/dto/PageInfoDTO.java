package com.dh.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 分页dto
 */
@ApiModel("分页信息")
public class PageInfoDTO {

	@ApiModelProperty(value = "当前页数", example = "0")
	private Integer page = 0;
	
	@ApiModelProperty(value = "每页查询条数", example = "10")
	private Integer pageSize = 10;
	
	@ApiModelProperty(value = "总条数", hidden = true)
	private Long totleCount = 0L;
	
	public PageInfoDTO() {}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Long getTotleCount() {
		return totleCount;
	}

	public void setTotleCount(Long totleCount) {
		this.totleCount = totleCount;
	}
	
}
