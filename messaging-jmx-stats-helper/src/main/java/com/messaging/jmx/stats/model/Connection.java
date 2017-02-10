package com.messaging.jmx.stats.model;

public class Connection {
	
	private final String hostname;
	
	private final String port;
	
	private final String userId;
	
	private final String password;

	/**
	 * @param hostname
	 * @param port
	 * @param userId
	 * @param password
	 */
	public Connection(String hostname, String port, String userId, String password) {
		super();
		this.hostname = hostname;
		this.port = port;
		this.userId = userId;
		this.password = password;
	}

	/**
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}

	/**
	 * @return the port
	 */
	public String getPort() {
		return port;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	

}
