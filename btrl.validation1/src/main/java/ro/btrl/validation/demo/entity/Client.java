package ro.btrl.validation.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="external1")
public class Client {

	// define fields
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="cnp")
	private String cnp;
	
	@Column(name="reputation")
	private String reputation;
	
		
	// define constructors
	
	public Client() {
		
	}

	public Client(String cnp, String reputation) {
		this.cnp = cnp;
		this.reputation = reputation;
	}
	// define getter/setter

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getReputation() {
		return reputation;
	}

	public void setReputation(String reputation) {
		this.reputation = reputation;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", cnp=" + cnp + ", reputation=" + reputation + "]";
	}
	
	
	



		
}











