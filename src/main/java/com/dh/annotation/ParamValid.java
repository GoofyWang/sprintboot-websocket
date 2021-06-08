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
@Constraint(validatedBy = ParamValidImpl.class)
public @interface ParamValid {
	
	/**校验类型*/
	String type() default "";

	/**正则表达式*/
	String pattern() default "";

	/**返回信息*/
	String message() default "参数格式错误";

	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default{};

}
