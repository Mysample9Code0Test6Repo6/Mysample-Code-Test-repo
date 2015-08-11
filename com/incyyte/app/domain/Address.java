/**
 * <p>Title: T</p>
 * <p>Description: </p> 
 * This class represents the User Address details.
 * For example:
 * <pre>
 * 		131 Grifon Road, Chafford Hundred Grays. RM16 6RL
 * </pre>
 * <p>Copyright: Copyright (c) 2010</p>
 * <p>Company: InCyyte Ltd</p>
 * @author Timi Boboye
 * @version $Revision 0.1$ 28 Nov 2010
 */
package com.incyyte.app.domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="addresses")
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="addressid")
	private long id;
	
	@Column(name="street_address")
	private String streetAddress;
	private double pca_id;
	private String place;	
	
    @JoinColumn(name="fk_postcodeid")
	@ManyToOne(fetch=FetchType.EAGER)
	private Postcode postcode;

    public Address(long id, String place, String streetAddress, Postcode postcode) {
    	  this.id = id;
	      this.place = place;
	      this.streetAddress = streetAddress;
	      this.postcode = postcode;
    }

    public Address() {
	}
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStreetAddress() {
		return streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	
	public Postcode getPostcode() {
		return postcode;
	}

	public void setPostcode(Postcode postcode) {
		this.postcode = postcode;
	}

	public double getPca_id() {
		return pca_id;
	}

	public void setPca_id(double pca_id) {
		this.pca_id = pca_id;
	}

	@Override
	public String toString() {
		return "Address [id=" + id + ", streetAddress=" + streetAddress
				+ ", pca_id=" + pca_id + ", place=" + place + ", postcode="
				+ postcode + "]";
	}

	
	

}
