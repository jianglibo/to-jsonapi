package com.jianglibo.tojsonapi.structure;

import java.util.List;
import java.util.stream.Collectors;


public class JsonapiDocumentBuilder {
	
	
	private final Pager pager;
	

	/**
	 * 
	 * @param pager {@link Pager}, a pager object to generate paginate links.
	 */
	public JsonapiDocumentBuilder(Pager pager) {
		super();
		this.pager = pager;
	}
	
	/**
	 * 
	 * @param resource The entity to be convert
	 * @param requestUrl The requesting url which results the entities, the links in jsonapi document
	 * @return be built document.
	 */
	public JsonApiDocument buildSingleResource(Object resource, String requestUrl) {
		ResourceObject ro = new ResourceObjectBuilder(requestUrl).build(resource);
		JsonApiDocument jad = new JsonApiDocument(ro);
		return jad;
	}
	
	/**
	 * 
	 * @param resources List of entities to be converted.
	 * @param totalResourceCount Total number of the resources to paginate. 
	 * @param requestUrl The requesting url which results the entities, the links in jsonapi document 
	 * @return built document.
	 * 
	 * requestUrl must be kind of /baseUrl/pluralResourceName?page[limit]=10.
	 * Why not extract resource name from urls?  Because you cannot image embedded resource names from request urls. 
	 * If you resource name in requestUrl are different from what annotated in class, It's up to you.
	 */
	public JsonApiDocument buildListResource(List<Object> resources, long totalResourceCount, String requestUrl) {
		String baseUrl = requestUrl.replaceFirst("/\\w+/?\\?.*$", "/");
		List<ResourceObject> rss = resources.stream().map(pojo -> new ResourceObjectBuilder(baseUrl).build(pojo)).collect(Collectors.toList());
		JsonApiDocument jad = new JsonApiDocument(rss);
		jad.addMeta("totalResourceCount", totalResourceCount);
		this.pager.calLinks(totalResourceCount, requestUrl).forEach((k, v) -> jad.addLink(k, v));
		return jad;
	}

}
