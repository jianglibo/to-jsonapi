package com.jianglibo.tojsonapi.structure;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.jianglibo.tojsonapi.reflect.JsonapiField;
import com.jianglibo.tojsonapi.reflect.JsonapiFieldIgnore;
import com.jianglibo.tojsonapi.reflect.JsonapiId;
import com.jianglibo.tojsonapi.reflect.JsonapiRelation;
import com.jianglibo.tojsonapi.reflect.JsonapiResource;

public class ResourceObject implements CanAsMap {
	
	private Map<String, Object> map = new LinkedHashMap<>();
	
	private Map<String, Object> attributes = new LinkedHashMap<>();
	
	private Map<String, Object> relationships = new LinkedHashMap<>();

	private String id;
	private String type;
	
	private Object pojo;
	
	private Links links = new Links();
	
	public void addSelfLink(String url) {
		this.links.addStringLink("self", url);
	}
	
	public ResourceObject(Object pojo) {
		this.pojo = pojo;
		this.buildAttributes();
	}

	private void buildAttributes() {
		Class<?> c = this.pojo.getClass();
		JsonapiResource jr = c.getAnnotation(JsonapiResource.class);
		
		if (jr != null) {
			this.setType(jr.type());
		} else {
			this.setType(c.getSimpleName().toLowerCase() + "s");
		}
		
		Field fieldHasNameId = null;
		List<Field> fields = new ArrayList<>();
		Class<?> sc = c;
		do {
			Field[] superFields = sc.getDeclaredFields();
			for (int i = 0; i < superFields.length; i++) {
				Field field = superFields[i];
				fields.add(field);
			}
		} while ((sc = sc.getSuperclass()) != null && sc != Object.class);
		
		boolean idReady = false;
		for (Field f : fields) {
			boolean stopProcess = false;
			f.setAccessible(true);
			if (!idReady) {
				JsonapiId an = f.getAnnotation(JsonapiId.class);
				if (an != null) {
					idReady = true;
					stopProcess = true;
					setIdField(f);
				}
				if ("id".equals(f.getName())) {
					fieldHasNameId = f;
				}	
			}
			
			if (!stopProcess) {
				JsonapiFieldIgnore jfii = f.getAnnotation(JsonapiFieldIgnore.class);
				if (jfii != null) {
					stopProcess = true;
				}
			}
			
			if (!stopProcess) {
				JsonapiRelation jrelation = f.getAnnotation(JsonapiRelation.class);
				if (jrelation != null) {
					stopProcess = true;
					Class<?> ft = f.getType();
				}
			}
			
			if (!stopProcess) {
				JsonapiField jf = f.getAnnotation(JsonapiField.class);
				if (jf != null && !jf.name().isEmpty()) {
					attributes.put(jf.name(), getFieldValue(f).orElse(null));
				} else {
					attributes.put(f.getName(), getFieldValue(f).orElse(null));					
				}
			}
		}
		
		if (!idReady && fieldHasNameId != null) {
			setIdField(fieldHasNameId);
			this.attributes.remove("id");
		}
		
	}

	private void setIdField(Field f) {
		Optional<Object> oo = getFieldValue(f);
		if (oo.isPresent()) {
			this.setId(oo.get().toString());
		} else {
			this.setId(null);
		}
	}

	private Optional<Object> getFieldValue(Field f) {
		try {
			Object o = f.get(this.pojo);
			return Optional.ofNullable(o);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public Map<String, Object> asMap() {
		this.map.put("id", id);
		this.map.put("type", type);
		this.map.put("attributes", attributes);
		this.map.put("links", links.asMap());
		return this.map;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}
}
