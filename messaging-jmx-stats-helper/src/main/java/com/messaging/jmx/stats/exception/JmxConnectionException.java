/**
 * 
 */
package com.messaging.jmx.stats.exception;

/**
 * @author anup.banerjee
 *
 */
public abstract class JmxConnectionException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3329204958060306976L;

	public JmxConnectionException(){
		
	}
	
	public JmxConnectionException(String message){
		super(message);
	}

	public JmxConnectionException(Throwable cause){
		super(cause);
	}

	public JmxConnectionException(String message, Throwable cause){
		super(message, cause);
	}
	
	
}
