package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Pracownicy {

	public Pracownicy(){

	}

	public Pracownicy(String numerID, String haslo){
		this.numerID=numerID;
		this.haslo=haslo;
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	private String numerID;
	private String haslo;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNumerID() {
		return numerID;
	}
	public void setNumerID(String numerID) {
		this.numerID = numerID;
	}
	public String getHaslo() {
		return haslo;
	}
	public void setHaslo(String haslo) {
		this.haslo = haslo;
	}
	
	
}
