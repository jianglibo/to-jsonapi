package com.jianglibo.tojsonapi.util;

import com.jianglibo.tojsonapi.reflect.JsonapiResource;

public class AnnotationUtil {
	
	public static String getType(Class<?> clazz) {
		JsonapiResource jr = clazz.getAnnotation(JsonapiResource.class);
		if (jr != null) {
			return jr.type();
		} else {
			return null;
		}
	}
}
