package com.messaging.jmx.stats.helperimpl;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.management.MBeanServerConnection;
import javax.management.MBeanServerInvocationHandler;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;

import org.apache.activemq.broker.jmx.BrokerViewMBean;
import org.apache.activemq.broker.jmx.QueueViewMBean;

import com.messaging.jmx.stats.exception.JmxConnectionException;
import com.messaging.jmx.stats.helper.ConnectionHelper;
import com.messaging.jmx.stats.model.Connection;
import com.messaging.jmx.stats.model.Message;

public class AMQStatisticsCollector {
	public static Connection connection;
	public static JMXConnector jmxConnector;
	public static MBeanServerConnection serverConnection;
	public static ConnectionHelper connectionHelper = new AMQConnectionHelperImpl();

	public void getQueueStatistics(Connection connection) throws JmxConnectionException, IOException {
		jmxConnector = connectionHelper.getConnector(connection);
		serverConnection = connectionHelper.openConnection(jmxConnector);
	}

	public Map<String, Message> populateMessage() {
		ObjectName activeMQ;
		Map<String, Message> queueStatistics = new HashMap<String, Message>();
		try {
			activeMQ = new ObjectName("org.apache.activemq:type=Broker,brokerName=amq");
			BrokerViewMBean mbean = (BrokerViewMBean) MBeanServerInvocationHandler.newProxyInstance(serverConnection,
					activeMQ, BrokerViewMBean.class, true);

			for (ObjectName name : mbean.getQueues()) {
				QueueViewMBean queueView = (QueueViewMBean) MBeanServerInvocationHandler
						.newProxyInstance(serverConnection, name, QueueViewMBean.class, true);
				Message message = new Message(queueView.getName(), new Long(queueView.getQueueSize()),
						new Long(queueView.getEnqueueCount()), new Long(queueView.getDequeueCount()));
				// System.out.println(jmsMessage);
				queueStatistics.put(queueView.getName(), message);
			}

		} catch (MalformedObjectNameException e) {
			System.out.println(e);
		} finally {
		}
		return queueStatistics;

	}

	public static void main(String args[]) {
		AMQStatisticsCollector amqStatisticsCollector = new AMQStatisticsCollector();
		connection = new Connection("172.16.10.206", "1099", "cep_di", "c3pd1");
		
		try {
			AMQStatisticsDAOImpl amqDao = new AMQStatisticsDAOImpl();
			amqStatisticsCollector.getQueueStatistics(connection);
			for (int i=1;;i++){
				amqDao.persist(amqStatisticsCollector.populateMessage());
				System.out.println("Number Of Cycle Completed : " +i);
				System.out.println(new Date() + " Will sleep for 1 Min!!");
				Thread.sleep(60000);
				System.out.println(new Date() + " Woke up will start processing again!! ");
			}
			
					
		} catch (JmxConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				connectionHelper.closeConnection(jmxConnector);
			} catch (JmxConnectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
