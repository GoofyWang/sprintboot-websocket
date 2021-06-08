package com.dh.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "答案")
public class Answer {
	
	@ApiModelProperty(value = "答案标识", example = "1")
    private Integer answerId;
	
	@ApiModelProperty(value = "试题标识", example = "1")
    private Integer itemBankId;
	
	@ApiModelProperty(value = "答案内容", example = "1")
    private String answerContent;
	
	@ApiModelProperty(value = "是否是正确答案  Y 是  N 否", example = "Y")
    private String answerStatus;

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getItemBankId() {
        return itemBankId;
    }

    public void setItemBankId(Integer itemBankId) {
        this.itemBankId = itemBankId;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent == null ? null : answerContent.trim();
    }

    public String getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(String answerStatus) {
        this.answerStatus = answerStatus == null ? null : answerStatus.trim();
    }
}