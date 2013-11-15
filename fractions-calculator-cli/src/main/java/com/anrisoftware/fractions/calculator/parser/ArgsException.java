package com.anrisoftware.fractions.calculator.parser;

import java.util.Map;

import com.anrisoftware.globalpom.exceptions.Context;

/**
 * Signaling errors while parsing command line arguments.
 * 
 * @author Erwin Mueller, erwin.mueller@deventm.org
 * @since 2.0
 */
@SuppressWarnings("serial")
public class ArgsException extends Exception {

	private final Context<ArgsException> context;

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public ArgsException(String message, Throwable cause) {
		super(message, cause);
		this.context = new Context<ArgsException>(this);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public ArgsException(String message) {
		super(message);
		this.context = new Context<ArgsException>(this);
	}

	/**
	 * @see Exception#Exception(String, Throwable)
	 */
	public ArgsException(Object message, Throwable cause) {
		super(message.toString(), cause);
		this.context = new Context<ArgsException>(this);
	}

	/**
	 * @see Exception#Exception(String)
	 */
	public ArgsException(Object message) {
		super(message.toString());
		this.context = new Context<ArgsException>(this);
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public ArgsException add(String name, Object value) {
		context.addContext(name, value);
		return this;
	}

	/**
	 * @see Context#addContext(String, Object)
	 */
	public ArgsException add(Object name, Object value) {
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
