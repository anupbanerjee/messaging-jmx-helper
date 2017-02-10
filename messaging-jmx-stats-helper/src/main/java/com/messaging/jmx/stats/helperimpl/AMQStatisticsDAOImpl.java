package com.messaging.jmx.stats.helperimpl;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import com.messaging.jmx.stats.model.Message;

public class AMQStatisticsDAOImpl {

	public void persist(Map<String, Message> message) {
		InfluxDB influxDB = InfluxDBHelper.getInstance().getDb();
		BatchPoints batchPoints = InfluxDBHelper.getInstance().getBp();
		for (Map.Entry<String, Message> entry : message.entrySet()) {
			Point point = Point.measurement("queuestats")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .tag("queue", entry.getValue().getQueueName())
                    .addField("queuename", entry.getValue().getQueueName())
                    .addField("queuesize", entry.getValue().getQueueSize())
                    .addField("enqueue", entry.getValue().getMessageEnqueued())
                    .addField("dequeue", entry.getValue().getMessageDequeued())
                    .build();
			batchPoints.point(point);
		}
		influxDB.write(batchPoints);
	}

}
