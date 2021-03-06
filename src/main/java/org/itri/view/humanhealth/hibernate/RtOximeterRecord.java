package org.itri.view.humanhealth.hibernate;

// Generated 2020/4/24 �U�� 08:59:27 by Hibernate Tools 4.0.0.Final
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * RtOximeterRecord generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "rt_oximeter_record")
public class RtOximeterRecord implements java.io.Serializable {

	private long rtOximeterRecordId;
	private Patient patient;
	private Sensor sensor;
	private String oximeterData;
	private String heartRateData;
	private String batteryLevel;
	private String oximeterStatus;
	private String heartRateStatus;
	private Date lastUpdated;

	public RtOximeterRecord() {
	}

	public RtOximeterRecord(long rtOximeterRecordId, Patient patient, Sensor sensor, String oximeterData,
			String heartRateData, String batteryLevel, String oximeterStatus, String heartRateStatus,
			Date lastUpdated) {
		this.rtOximeterRecordId = rtOximeterRecordId;
		this.patient = patient;
		this.sensor = sensor;
		this.oximeterData = oximeterData;
		this.heartRateData = heartRateData;
		this.batteryLevel = batteryLevel;
		this.oximeterStatus = oximeterStatus;
		this.heartRateStatus = heartRateStatus;
		this.lastUpdated = lastUpdated;
	}

	@SequenceGenerator(name = "rt_oximeter_record_seq", sequenceName = "rt_oximeter_record_rt_oximeter_record_id_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rt_oximeter_record_seq")
	@Id

	@Column(name = "rt_oximeter_record_id", unique = true, nullable = false)
	public long getRtOximeterRecordId() {
		return this.rtOximeterRecordId;
	}

	public void setRtOximeterRecordId(long rtOximeterRecordId) {
		this.rtOximeterRecordId = rtOximeterRecordId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "patient_id", nullable = false)
	public Patient getPatient() {
		return this.patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sensor_id", nullable = false)
	public Sensor getSensor() {
		return this.sensor;
	}

	public void setSensor(Sensor sensor) {
		this.sensor = sensor;
	}

	@Column(name = "oximeter_data", nullable = false, length = 64)
	public String getOximeterData() {
		return this.oximeterData;
	}

	public void setOximeterData(String oximeterData) {
		this.oximeterData = oximeterData;
	}

	@Column(name = "heart_rate_data", nullable = false, length = 64)
	public String getHeartRateData() {
		return this.heartRateData;
	}

	public void setHeartRateData(String heartRateData) {
		this.heartRateData = heartRateData;
	}

	@Column(name = "battery_level", nullable = false, length = 64)
	public String getBatteryLevel() {
		return this.batteryLevel;
	}

	public void setBatteryLevel(String batteryLevel) {
		this.batteryLevel = batteryLevel;
	}

	@Column(name = "oximeter_status", nullable = false, length = 32)
	public String getOximeterStatus() {
		return this.oximeterStatus;
	}

	public void setOximeterStatus(String oximeterStatus) {
		this.oximeterStatus = oximeterStatus;
	}

	@Column(name = "heart_rate_status", nullable = false, length = 32)
	public String getHeartRateStatus() {
		return this.heartRateStatus;
	}

	public void setHeartRateStatus(String heartRateStatus) {
		this.heartRateStatus = heartRateStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", nullable = false, length = 29)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

}
