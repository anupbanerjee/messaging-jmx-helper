package com.messaging.jmx.stats.helperimpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.messaging.jmx.stats.exception.JmxConnectionException;
import com.messaging.jmx.stats.helper.ConnectionHelper;
import com.messaging.jmx.stats.model.Connection;

public class AMQConnectionHelperImpl implements ConnectionHelper {

	/*
	 * The below method needs to be implemented to get JMXConnector object
	 */
	public JMXConnector getConnector(Connection connectionParams) throws JmxConnectionException, IOException{
		Map<String, String[]> env = new HashMap<String, String[]>();

		String[] credentials = new String[] { connectionParams.getUserId(), connectionParams.getPassword() };
		env.put(JMXConnector.CREDENTIALS, credentials);
		JMXServiceURL address = new JMXServiceURL("service:jmx:rmi:///jndi/rmi://" + connectionParams.getHostname() + ":" + connectionParams.getPort() + "/jmxrmi");
		JMXConnector connector = JMXConnectorFactory.connect(address, env);
		return connector;
		//MBeanServerConnection amqConnection = connector.getMBeanServerConnection();
		//return amqConnection;
	};
	
	
	
	public void closeConnection(JMXConnector connector) throws JmxConnectionException, IOException {
		// TODO Auto-generated method stub
		connector.close();

	}

	public MBeanServerConnection openConnection(JMXConnector connector) throws JmxConnectionException, IOException {
		// TODO Auto-generated method stub
		return connector.getMBeanServerConnection();
	}

	
}
