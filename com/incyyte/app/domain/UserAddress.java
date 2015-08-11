package com.incyyte.app.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_address")
public class UserAddress implements Serializable{	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="user_addressid")
	private long id;

    @JoinColumn(name="fk_addressid")
	@ManyToOne(fetch=FetchType.EAGER)
	private Address address;
	
	@Column(name="cyyte_home_mail")
	private String cyyteHomeEmail;
	private String authPin;

	@Enumerated(EnumType.STRING) 
	@Column(name="master_pin")
	private String masterPin = "N";	//ENUM('Y', 'N')	

	@Enumerated(EnumType.STRING) 
	private String status = "NON_ACTIVE";		//ENUM('ACTIVE', 'NON_ACTIVE', 'SUSPENDED')

	@Column(name="suspended_date")
	private Calendar suspendedDate;

	@Column(name="deactivated_date")
	private Calendar deactivatedDate;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getCyyteHomeEmail() {
		return cyyteHomeEmail;
	}
	public void setCyyteHomeEmail(String cyyteHomeEmail) {
		this.cyyteHomeEmail = cyyteHomeEmail;
	}
	public String getMasterPin() {
		return masterPin;
	}
	public void setMasterPin(String masterPin) {
		this.masterPin = masterPin;
	}
	public String getAuthPin() {
		return authPin;
	}
	public void setAuthPin(String authPin) {
		this.authPin = authPin;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Calendar getSuspendedDate() {
		return suspendedDate;
	}
	public void setSuspendedDate(Calendar suspendedDate) {
		this.suspendedDate = suspendedDate;
	}
	public Calendar getDeactivatedDate() {
		return deactivatedDate;
	}
	public void setDeactivatedDate(Calendar deactivatedDate) {
		this.deactivatedDate = deactivatedDate;
	}


}
