package ro.btrl.demo.response;

import java.io.Serializable;

public class Response implements Serializable {

	private int id;
    private String cnp;
    private int reputation;

	private static final long serialVersionUID = 1L;
	
	
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
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	
	
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", cnp=" + cnp + ", reputation=" + reputation + "]";
	}

    
}