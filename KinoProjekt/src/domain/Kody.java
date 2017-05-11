package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Kody {

	public Kody(){

	}

	public Kody(String kod, double znizka){
		this.kod=kod;
		this.znizka=znizka;
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	private String kod;
	private double znizka;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getKod() {
		return kod;
	}
	public void setKod(String kod) {
		this.kod = kod;
	}
	public double getZnizka() {
		return znizka;
	}
	public void setZnizka(double znizka) {
		this.znizka = znizka;
	}
	
	
}
