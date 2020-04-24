package org.itri.view.humanhealth.hibernate;
// Generated 2020/4/24 �U�� 08:59:27 by Hibernate Tools 4.0.0.Final
import javax.persistence.GeneratedValue;import javax.persistence.SequenceGenerator;import javax.persistence.GenerationType;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * HealthType generated by hbm2java
 */
@SuppressWarnings("serial")@Entity
@Table(name = "health_type")
public class HealthType implements java.io.Serializable {

	private long healthTypeId;
	private String typeName;
	private boolean isDeleted;
	private Set<PatientThreshold> patientThresholds = new HashSet<PatientThreshold>(0);

	public HealthType() {
	}

	public HealthType(long healthTypeId, String typeName, boolean isDeleted) {
		this.healthTypeId = healthTypeId;
		this.typeName = typeName;
		this.isDeleted = isDeleted;
	}

	public HealthType(long healthTypeId, String typeName, boolean isDeleted, Set<PatientThreshold> patientThresholds) {
		this.healthTypeId = healthTypeId;
		this.typeName = typeName;
		this.isDeleted = isDeleted;
		this.patientThresholds = patientThresholds;
	}

	@SequenceGenerator(name="health_type_seq", sequenceName="health_type_health_type_id_seq", allocationSize=1)	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="health_type_seq")	@Id

	@Column(name = "health_type_id", unique = true, nullable = false)
	public long getHealthTypeId() {
		return this.healthTypeId;
	}

	public void setHealthTypeId(long healthTypeId) {
		this.healthTypeId = healthTypeId;
	}

	@Column(name = "type_name", nullable = false, length = 256)
	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	@Column(name = "is_deleted", nullable = false)
	public boolean isIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "healthType")
	public Set<PatientThreshold> getPatientThresholds() {
		return this.patientThresholds;
	}

	public void setPatientThresholds(Set<PatientThreshold> patientThresholds) {
		this.patientThresholds = patientThresholds;
	}

}
