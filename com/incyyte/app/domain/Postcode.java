package com.incyyte.app.domain;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name="postcodes")
public class Postcode implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="postcodeid")
	private long id;
	private String code;
	
	@Transient
	private String selectedAddress;
	
    public Postcode(String postcode) {
	      this.code = postcode;
    }
    
    public Postcode() {
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getSelectedAddress() {
		return selectedAddress;
	}

	public void setSelectedAddress(String selectedAddress) {
		this.selectedAddress = selectedAddress;
	}

	@Override
	public String toString() {
		return "Postcode [id=" + id + ", code=" + code + "]";
	}
	
	

}
