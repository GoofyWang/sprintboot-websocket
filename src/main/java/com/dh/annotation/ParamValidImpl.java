package com.dh.annotation;

import com.dh.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ParamValidImpl implements ConstraintValidator<ParamValid, String>{

	private String type;
	
	private String pattern;
	
	@Override
	public void initialize(ParamValid valid) {
		type = valid.type();
		pattern = valid.pattern();
	}

	@Override
	public boolean isValid(String param, ConstraintValidatorContext valid){
		boolean flag = StringUtils.isEmptyString(param);
		try {
			if(flag) return true;
			paramValid(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 参数校验
	 * @param param
	 * @return
	 */
	private boolean paramValid(String param) throws Exception{
		if(StringUtils.isEmptyString(type)){
			if(StringUtils.isEmptyString(pattern)){
				throw new RuntimeException("pattern is not null");
			}
			return param.matches(pattern);
		}else{
			if(ValidType.idcard.equals(type)){//身份证号验证
				return IdcardValid.idcardCheck(param);
			}
		}
		return false;
	}
}
