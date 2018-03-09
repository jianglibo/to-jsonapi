package com.jianglibo.tojsonapi.reflect;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface JsonapiRelation {
	String name() default "";
	JsonapiRelationType relationType();
	Class<?> targetType();
	
	public static enum JsonapiRelationType {
		ITERABLE, SINGLE, MAP
	}
}
