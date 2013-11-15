package com.anrisoftware.fractions.calculator.app;

import java.util.Map;

import com.anrisoftware.globalpom.exceptions.Context;

/**
 * Signaling errors while parsing command line arguments.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@SuppressWarnings("serial")
public class AppException extends Exception {

	private final Context<AppException> context;

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public AppException(String message, Throwable cause) {
		super(message, cause);
		this.context = new Context<AppException>(this);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public AppException(String message) {
		super(message);
		this.context = new Context<AppException>(this);
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public AppException(Object message, Throwable cause) {
		super(message.toString(), cause);
		this.context = new Context<AppException>(this);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public AppException(Object message) {
		super(message.toString());
		this.context = new Context<AppException>(this);
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public AppException add(String name, Object value) {
		context.addContext(name, value);
		return this;
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public AppException add(Object name, Object value) {
		context.addContext(name.toString(), value);
		return this;
	}

	/**
	 * @see Context#getContext()
	 */
	public Map<String, Object> getContext() {
		return context.getContext();
	}

	@Override
	public String toString() {
		return context.toString();
	}
}
