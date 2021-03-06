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
import com.jianglibo.tojsonapi.util.ResourceUrl;

public class ResourceObject implements CanAsMap {
	
	private Map<String, Object> map = new LinkedHashMap<>();
	
	private Map<String, Object> attributes = new LinkedHashMap<>();
	
	private Map<String, RelationLink> relationships = new LinkedHashMap<>();

	private String id;
	private String type;
	
	private Object pojo;
	
	private Map<String, Object> links = new LinkedHashMap<>();
	
	private final String requestUrl;
	
	
	/**
	 * 
	 * @param requestUrl The requesting url which results this pojo.
	 * @param pojo The result pojo to convert.
	 */
	protected ResourceObject(String requestUrl, Object pojo) {
		this.requestUrl = requestUrl;
		this.pojo = pojo;
	}

	protected void buildAttributes() {
		Class<?> c = this.pojo.getClass();
		JsonapiResource jr = c.getAnnotation(JsonapiResource.class);
		
		if (jr != null) {
			this.setType(jr.type());
		} else {
			this.setType(c.getSimpleName().toLowerCase() + "s");
		}
		
		Field fieldHasNameId = null;
		List<Field> fields = new ArrayList<>();
		List<Field> relationFields = new ArrayList<>();

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
				if(f.isAnnotationPresent(JsonapiRelation.class)) {
					stopProcess = true;
					relationFields.add(f);
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
		
		for(Field rf:  relationFields) {
			JsonapiRelation jra = rf.getAnnotation(JsonapiRelation.class);
			String relationName = jra.name().isEmpty() ? rf.getName() : jra.name();
			String thisResourceUrl = new ResourceUrl(getType(), getId()).calUrl(requestUrl);
			String selfUrl =  thisResourceUrl + "/relationships/" + relationName;
			String relatedUrl = thisResourceUrl + "/" + relationName;
			RelationLink rl = new RelationLink();
			rl.addSelfLink(selfUrl);
			rl.addRelatedLink(relatedUrl);
			this.relationships.put(relationName, rl);
			
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
		if (this.relationships != null && !this.relationships.isEmpty()) {
			this.map.put("relationships", relationships);
		}
		this.links.put("self", new ResourceUrl(getType(), getId()).calUrl(requestUrl));
		this.map.put("links", links);
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
