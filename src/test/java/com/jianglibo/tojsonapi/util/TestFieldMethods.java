package com.jianglibo.tojsonapi.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.jianglibo.tojsonapi.model.MyRole;

public class TestFieldMethods {
	
//	Class<?>	getDeclaringClass()
//	Returns the Class object representing the class or interface that declares the field represented by this Field object.
	
//	Class<?>	getType()
//	Returns a Class object that identifies the declared type for the field represented by this Field object.
	
	
	protected static class Tc {
		
		private List<MyRole> roles;

		public List<MyRole> getRoles() {
			return roles;
		}

		public void setRoles(List<MyRole> roles) {
			this.roles = roles;
		}
	}
	
	@Test
	public void testTypeAndDeclaringClass() throws NoSuchFieldException, SecurityException {
		Field f = MyRole.class.getDeclaredField("name");
		Class<?> c1 = f.getType();
		Class<?> c2 = f.getDeclaringClass();
		
		assertThat("getType() returns the type of this field.",c1, equalTo(String.class));
		assertThat("getDeclaringClass() returns the type of the class which declared this field.",c2, equalTo(MyRole.class));
	}
	
	@Test
	public void testCollectionField() throws NoSuchFieldException, SecurityException, ClassNotFoundException {
		Field f = Tc.class.getDeclaredField("roles");
		Class<?> c1 = f.getType();
		Type tp = f.getGenericType();
		ParameterizedType ptp = (ParameterizedType) tp;
		
		Type atp = ptp.getActualTypeArguments()[0];
		Class<?> c = Class.forName(atp.getTypeName());
		assertThat("List is a kind of collection", c1, equalTo(List.class));
	}
	
	@Test
	public void testm() {
		Map<String, Object> m = new HashMap<>();
		
	}

}
