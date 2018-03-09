package com.jianglibo.tojsonapi.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Optional;

import com.jianglibo.tojsonapi.reflect.JsonapiResource;

public class AnnotationUtil {
	
	public static Optional<String> getResourceType(Class<?> clazz) {
		JsonapiResource jr = clazz.getAnnotation(JsonapiResource.class);
		if (jr != null && !jr.type().isEmpty()) {
			return Optional.of(jr.type());
		} else {
			return Optional.empty();
		}
	}

	public static Optional<Class<?>> getRelationClass(Field field) {
		Optional<Class<?>> co = Optional.empty(); 
		Type tp = field.getGenericType();
		if (tp instanceof ParameterizedType) {
			Type atp = ((ParameterizedType)tp).getActualTypeArguments()[0];
			try {
				Class<?> c = Class.forName(atp.getTypeName());
				co = Optional.of(c);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (!co.isPresent()) {
			co = Optional.of(field.getType());
		}
		return co;
	}
}
