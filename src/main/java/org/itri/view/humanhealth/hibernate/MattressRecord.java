package org.itri.view.humanhealth.hibernate;
// Generated 2020/4/24 �U�� 08:59:27 by Hibernate Tools 4.0.0.Final
import javax.persistence.GeneratedValue;import javax.persistence.SequenceGenerator;import javax.persistence.GenerationType;
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
 * MattressRecord generated by hbm2java
 */
@SuppressWarnings("serial")@Entity
@Table(name = "mattress_record")
public class MattressRecord implements java.io.Serializable {

	private long mattressRecordId;
	private Patient patient;
	private Sensor sensor;
	private String mattressData;
	private String mattressStatus;
	private Date timeCreated;

	public MattressRecord() {
	}

	public MattressRecord(long mattressRecordId, Patient patient, Sensor sensor, String mattressData,
			String mattressStatus, Date timeCreated) {
		this.mattressRecordId = mattressRecordId;
		this.patient = patient;
		this.sensor = sensor;
		this.mattressData = mattressData;
		this.mattressStatus = mattressStatus;
		this.timeCreated = timeCreated;
	}

	@SequenceGenerator(name="mattress_record_seq", sequenceName="mattress_record_mattress_record_id_seq", allocationSize=1)	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="mattress_record_seq")	@Id

	@Column(name = "mattress_record_id", unique = true, nullable = false)
	public long getMattressRecordId() {
		return this.mattressRecordId;
	}

	public void setMattressRecordId(long mattressRecordId) {
		this.mattressRecordId = mattressRecordId;
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

	@Column(name = "mattress_data", nullable = false, length = 64)
	public String getMattressData() {
		return this.mattressData;
	}

	public void setMattressData(String mattressData) {
		this.mattressData = mattressData;
	}

	@Column(name = "mattress_status", nullable = false, length = 32)
	public String getMattressStatus() {
		return this.mattressStatus;
	}

	public void setMattressStatus(String mattressStatus) {
		this.mattressStatus = mattressStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "time_created", nullable = false, length = 29)
	public Date getTimeCreated() {
		return this.timeCreated;
	}

	public void setTimeCreated(Date timeCreated) {
		this.timeCreated = timeCreated;
	}

}
