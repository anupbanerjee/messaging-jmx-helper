package com.messaging.jmx.stats.helper;

import java.io.IOException;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;

import com.messaging.jmx.stats.exception.JmxConnectionException;
import com.messaging.jmx.stats.model.Connection;

public interface ConnectionHelper {

	/*
	 * The below method needs to be implemented to get JMXConnector object
	 */
	
	public JMXConnector getConnector(Connection connectionParams) throws JmxConnectionException, IOException;
	
	public MBeanServerConnection openConnection(JMXConnector connector) throws JmxConnectionException, IOException;
	
	public void closeConnection(JMXConnector connector) throws JmxConnectionException, IOException;
	
}
