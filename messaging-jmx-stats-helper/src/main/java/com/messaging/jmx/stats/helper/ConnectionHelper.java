package com.messaging.jmx.stats.helper;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;

import com.messaging.jmx.stats.exception.JmxConnectionException;
import com.messaging.jmx.stats.model.Connection;

public interface ConnectionHelper {

	/*
	 * The below method needs to be implemented to get JMXConnector object
	 */
	public JMXConnector getConnector() throws JmxConnectionException;
	
	public MBeanServerConnection openConnection(Connection connection, JMXConnector connector ) throws JmxConnectionException;
	
	public void closeConnection(MBeanServerConnection connection) throws JmxConnectionException; 
	
}
