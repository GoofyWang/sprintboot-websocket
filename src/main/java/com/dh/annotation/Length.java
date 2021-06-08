package com.dh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = LengthImpl.class)
public @interface Length {

	String message() default "参数长度不正确";
	
	/**指定长度*/
	int len() default 0;

	/**最大长度*/
	int max() default 0;

	/**最小长度*/
	int min() default 0;
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};
	
}
