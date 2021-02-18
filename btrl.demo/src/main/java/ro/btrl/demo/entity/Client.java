package ro.btrl.demo.entity;


import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ro.btrl.demo.validation.ValidCNP;
import ro.btrl.demo.validation.ValidDate;
import ro.btrl.demo.validation.ValidNumber;
import ro.btrl.demo.validation.ValidSerial;

@Entity
@Table(name = "client")
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@NotNull(message = "is required")
	@Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters")
	@Column(name = "first_name")
	private String firstName;

	@NotNull(message = "is required")
	@Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters")
	@Column(name = "last_name")
	private String lastName;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 45, message = "Nationality must be between 1 and 45 characters")
	@Column(name = "nationality")
	private String nationality;
	
	@NotNull(message = "is required")
	@Size(min = 1, max = 1, message = "Gender must be M or F")
	@Column(name = "gender")
	private String gender;

	@NotNull(message = "is required")
	@Size(min = 1, max = 45, message = "Place of birth must be between 1 and 45 characters")
	@Column(name = "placeofbirth")
	private String placeofbirth;

	@NotNull(message = "is required")
	@Size(min = 1, max = 45, message = "Address must be between 1 and 45 characters")
	@Column(name = "address")
	private String address;
	
	@ValidCNP
	@NotNull(message = "is required")
	@Size(min = 1, max = 13, message = "13 numbers are required")
	@Column(name = "cnp")
	private String cnp;

	@ValidSerial
	@NotNull(message = "is required")
	@Size(min = 1, max = 2, message = "2 letters are required")
	@Column(name = "serial")
	private String serial;

	@ValidNumber
	@NotNull(message = "is required")
	@Size(min = 1, max = 6, message = "6 numbers are required")
	@Column(name = "nr")
	private String nr;
	
	@ValidDate
	@NotNull(message = "is required")
	@Temporal(TemporalType.DATE)
	@Column(name = "validfrom")
	private Date validFrom;

	@Temporal(TemporalType.DATE)
	@NotNull(message = "is required")
	@Column(name = "validto")
	private Date validTo;
	
    @Lob 
	@Column(name = "document")
	private byte[] document;

 
	public Client() {

	}

	public Client(int id,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters") String firstName,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters") String lastName,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Nationality must be between 1 and 45 characters") String nationality,
			@NotNull(message = "is required") @Size(min = 1, max = 1, message = "Gender must be M or F") String gender,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Place of birth must be between 1 and 45 characters") String placeofbirth,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Address must be between 1 and 45 characters") String address,
			@NotNull(message = "is required") @Size(min = 1, max = 13, message = "13 numbers are required") String cnp,
			@NotNull(message = "is required") @Size(min = 1, max = 2, message = "2 letters are required") String serial,
			@NotNull(message = "is required") @Size(min = 1, max = 6, message = "6 numbers are required") String nr,
			@NotNull(message = "is required") Date validFrom, @NotNull(message = "is required") Date validTo,
			byte[] document) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.gender = gender;
		this.placeofbirth = placeofbirth;
		this.address = address;
		this.cnp = cnp;
		this.serial = serial;
		this.nr = nr;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.document = document;
	}

	public Client(
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "First name must be between 1 and 45 characters") String firstName,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Last name must be between 1 and 45 characters") String lastName,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Nationality must be between 1 and 45 characters") String nationality,
			@NotNull(message = "is required") @Size(min = 1, max = 1, message = "Gender must be M or F") String gender,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Place of birth must be between 1 and 45 characters") String placeofbirth,
			@NotNull(message = "is required") @Size(min = 1, max = 45, message = "Address must be between 1 and 45 characters") String address,
			@NotNull(message = "is required") @Size(min = 1, max = 13, message = "13 numbers are required") String cnp,
			@NotNull(message = "is required") @Size(min = 1, max = 2, message = "2 letters are required") String serial,
			@NotNull(message = "is required") @Size(min = 1, max = 6, message = "6 numbers are required") String nr,
			@NotNull(message = "is required") Date validFrom, @NotNull(message = "is required") Date validTo,
			byte[] document) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.nationality = nationality;
		this.gender = gender;
		this.placeofbirth = placeofbirth;
		this.address = address;
		this.cnp = cnp;
		this.serial = serial;
		this.nr = nr;
		this.validFrom = validFrom;
		this.validTo = validTo;
		this.document = document;
	}

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPlaceofbirth() {
		return placeofbirth;
	}

	public void setPlaceofbirth(String placeofbirth) {
		this.placeofbirth = placeofbirth;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String nr) {
		this.nr = nr;
	}

	public Date getValidFrom() {
		return validFrom;
	}

	public void setValidFrom(Date validFrom) {
		this.validFrom = validFrom;
	}

	public Date getValidTo() {
		return validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cnp == null) ? 0 : cnp.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (cnp == null) {
			if (other.cnp != null)
				return false;
		} else if (!cnp.equals(other.cnp))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", nationality="
				+ nationality + ", gender=" + gender + ", placeofbirth=" + placeofbirth + ", address=" + address
				+ ", cnp=" + cnp + ", serial=" + serial + ", nr=" + nr + ", validFrom=" + validFrom + ", validTo="
				+ validTo + ", document=" + Arrays.toString(document) + "]";
	}
	
	
}
