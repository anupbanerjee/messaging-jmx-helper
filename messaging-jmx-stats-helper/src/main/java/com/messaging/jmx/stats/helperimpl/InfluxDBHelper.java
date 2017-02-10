package com.messaging.jmx.stats.helperimpl;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDB.ConsistencyLevel;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;

public class InfluxDBHelper {
	private static InfluxDBHelper instance;
	private InfluxDB influxDB;
	private BatchPoints batchPoints;

	private InfluxDBHelper() {
		influxDB = InfluxDBFactory.connect("http://10.62.118.240:8086", "root", "root");
		batchPoints = BatchPoints.database("amqdb").tag("async", "true").retentionPolicy("autogen")
				.consistency(ConsistencyLevel.ALL).build();
		System.out.println("Here");
	}

	public static InfluxDBHelper getInstance() {
		if (instance == null) {
			instance = new InfluxDBHelper();
		}
		return instance;
	}

	public InfluxDB getDb() {
		return influxDB;
	}

	public BatchPoints getBp() {
		return batchPoints;
	}

}