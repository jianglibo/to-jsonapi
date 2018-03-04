package com.jianglibo.tojsonapi.exception;

public class UnAnnotatedResourceObject extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5422750929526944799L;
	
	public UnAnnotatedResourceObject(Object o) {
		super(o.getClass().getName());
	}

}
