package com.jianglibo.tojsonapi.reflect;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

@Retention(RUNTIME)
public @interface JsonapiResource {
	String type();
	String resourceName() default "";	
}
