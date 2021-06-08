package com.dh.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "部门")
public class Department {
	
	@ApiModelProperty(value = "部门标识  传空时为添加信息", example = "1")
    private Integer departmentId;

	@ApiModelProperty(value = "部门名称", example = "1")
    private String departmentName;

	@ApiModelProperty(value = "部门父标识", example = "1")
    private Integer departmentParentId;

	@ApiModelProperty(value = "部门级别", example = "1")
    private Integer departmentLevel;

	@ApiModelProperty(value = "部门顺序", example = "1")
    private Integer departmentSort;

	@ApiModelProperty(value = "创建人标识", hidden = true)
    private Integer createId;

	@ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

	@ApiModelProperty(value = "修改人标识", hidden = true)
    private Integer updateId;

	@ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;

	@ApiModelProperty(value = "可用状态 Y 可用 N 不可用", hidden = true)
    private String status;

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName == null ? null : departmentName.trim();
    }

    public Integer getDepartmentParentId() {
        return departmentParentId;
    }

    public void setDepartmentParentId(Integer departmentParentId) {
        this.departmentParentId = departmentParentId;
    }

    public Integer getDepartmentLevel() {
        return departmentLevel;
    }

    public void setDepartmentLevel(Integer departmentLevel) {
        this.departmentLevel = departmentLevel;
    }

    public Integer getDepartmentSort() {
        return departmentSort;
    }

    public void setDepartmentSort(Integer departmentSort) {
        this.departmentSort = departmentSort;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}