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
@Constraint(validatedBy = NotNullImpl.class)
public @interface NotNull {

	String message() default "不能为null";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};		
}
