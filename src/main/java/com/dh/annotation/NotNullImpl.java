package com.dh.annotation;

import com.dh.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotNullImpl implements ConstraintValidator<NotNull, Object>{

	@Override
	public boolean isValid(Object param, ConstraintValidatorContext arg1) {
		if(param instanceof String){
			if(StringUtils.isEmptyString((String) param)){
				return false;
			}
		}else{
			if(param == null) return false;
		}
		return true;
	}

}
