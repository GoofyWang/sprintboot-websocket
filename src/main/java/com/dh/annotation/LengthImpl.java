package com.dh.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LengthImpl implements ConstraintValidator<Length, Object>{

	private int len;

	private int max;
	
	private int min;
	
	@Override
	public void initialize(Length valid) {
		len = valid.len();
		max = valid.max();
		min = valid.min();
	}
	
	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		if(len <= 0 && min <= 0 && max <= 0){
			throw new RuntimeException("len min max 不能同时为0");
		}
		String param = String.valueOf(arg0);
		int lenth = param.length();
		if(len > 0){
			if(len != lenth) return false;
			else return true;
		}
		if(min > 0){
			if(lenth >= min) return true;
			else return false;
		}
		if(max > 0){
			if(max < min) throw new RuntimeException("max is invalid");
			if(lenth <= max) return true;
			else return false;
		}
		return false;
	}

}
