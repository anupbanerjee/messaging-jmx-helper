package com.messaging.jmx.stats.model;

public abstract class Message {
	
	private final String queueName;
	
	private final Long queueSize;
	
	private final Long messageEnqueued;
	
	private final Long messageDequeued;
	
	public Message(String queueName, Long queueSize, Long messageEnqueued, Long messageDequeued) {
		super();
		this.queueName = queueName;
		this.queueSize = queueSize;
		this.messageEnqueued = messageEnqueued;
		this.messageDequeued = messageDequeued;
	}


	public String getQueueName() {
		return queueName;
	}

	public Long getQueueSize() {
		return queueSize;
	}

	public Long getMessageEnqueued() {
		return messageEnqueued;
	}

	public Long getMessageDequeued() {
		return messageDequeued;
	}


	@Override
	public String toString() {
		return "JMSMessage [queueName=" + queueName + ", queueSize=" + queueSize + ", messageEnqueued="
				+ messageEnqueued + ", messageDequeued=" + messageDequeued + "]";
	}
	
	
	
}
