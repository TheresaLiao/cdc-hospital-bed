package org.itri.view.humanhealth.hibernate;
// Generated 2020/4/24 �U�� 08:59:27 by Hibernate Tools 4.0.0.Final
import javax.persistence.GeneratedValue;import javax.persistence.SequenceGenerator;import javax.persistence.GenerationType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Room generated by hbm2java
 */
@SuppressWarnings("serial")@Entity
@Table(name = "room")
public class Room implements java.io.Serializable {

	private long roomId;
	private Users users;
	private String roomNum;
	private boolean isFull;
	private Date lastUpdated;
	private Set<Patient> patients = new HashSet<Patient>(0);

	public Room() {
	}

	public Room(long roomId, Users users, String roomNum, boolean isFull) {
		this.roomId = roomId;
		this.users = users;
		this.roomNum = roomNum;
		this.isFull = isFull;
	}

	public Room(long roomId, Users users, String roomNum, boolean isFull, Date lastUpdated, Set<Patient> patients) {
		this.roomId = roomId;
		this.users = users;
		this.roomNum = roomNum;
		this.isFull = isFull;
		this.lastUpdated = lastUpdated;
		this.patients = patients;
	}

	@SequenceGenerator(name="room_seq", sequenceName="room_room_id_seq", allocationSize=1)	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="room_seq")	@Id

	@Column(name = "room_id", unique = true, nullable = false)
	public long getRoomId() {
		return this.roomId;
	}

	public void setRoomId(long roomId) {
		this.roomId = roomId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "users_id", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "room_num", nullable = false, length = 128)
	public String getRoomNum() {
		return this.roomNum;
	}

	public void setRoomNum(String roomNum) {
		this.roomNum = roomNum;
	}

	@Column(name = "is_full", nullable = false)
	public boolean isIsFull() {
		return this.isFull;
	}

	public void setIsFull(boolean isFull) {
		this.isFull = isFull;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_updated", length = 29)
	public Date getLastUpdated() {
		return this.lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	public Set<Patient> getPatients() {
		return this.patients;
	}

	public void setPatients(Set<Patient> patients) {
		this.patients = patients;
	}

}
