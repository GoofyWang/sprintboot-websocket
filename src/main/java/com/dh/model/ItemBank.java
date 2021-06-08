package com.dh.model;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "试题")
public class ItemBank {
	
	@ApiModelProperty(value = "试题标识", example = "1")
    private Integer itemBankId;

	@ApiModelProperty(value = "试题标题", example = "1")
    private String itemBankTitle;

	@ApiModelProperty(value = "试题解析", example = "1")
    private String itemBankAnalysis;

	@ApiModelProperty(value = "试题类型 1 单选 2 多选 3 判断", example = "1")
    private String itemBankType;

	@ApiModelProperty(value = "试题答案", hidden = true)
    private String itemBankAnswer;

	@ApiModelProperty(value = "创建人", hidden = true)
    private Integer createId;

	@ApiModelProperty(value = "创建时间", hidden = true)
    private String createTime;

	@ApiModelProperty(value = "修改人", hidden = true)
    private Integer updateId;

	@ApiModelProperty(value = "修改时间", hidden = true)
    private String updateTime;

	@ApiModelProperty(value = "试题可用状态 Y 可用  N 不可用", hidden = true)
    private String status;
	
	@ApiModelProperty(value = "答案列表")
	private List<Answer> answerList;

    public Integer getItemBankId() {
        return itemBankId;
    }

    public void setItemBankId(Integer itemBankId) {
        this.itemBankId = itemBankId;
    }

    public String getItemBankTitle() {
        return itemBankTitle;
    }

    public void setItemBankTitle(String itemBankTitle) {
        this.itemBankTitle = itemBankTitle == null ? null : itemBankTitle.trim();
    }

    public String getItemBankAnalysis() {
        return itemBankAnalysis;
    }

    public void setItemBankAnalysis(String itemBankAnalysis) {
        this.itemBankAnalysis = itemBankAnalysis == null ? null : itemBankAnalysis.trim();
    }

    public String getItemBankType() {
        return itemBankType;
    }

    public void setItemBankType(String itemBankType) {
        this.itemBankType = itemBankType == null ? null : itemBankType.trim();
    }

    public String getItemBankAnswer() {
        return itemBankAnswer;
    }

    public void setItemBankAnswer(String itemBankAnswer) {
        this.itemBankAnswer = itemBankAnswer == null ? null : itemBankAnswer.trim();
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

	public List<Answer> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<Answer> answerList) {
		this.answerList = answerList;
	}
    
}