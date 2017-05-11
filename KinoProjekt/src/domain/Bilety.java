package domain;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Bilety {

	public Bilety(){

	}

	public Bilety(String rodzajBiletu, double cena){
		this.rodzajBiletu=rodzajBiletu;
		this.cena=cena;
	};
	
	@Id
	@GeneratedValue
	private long id;
	
	private String rodzajBiletu;
	private double cena;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getRodzajBiletu() {
		return rodzajBiletu;
	}
	public void setRodzajBiletu(String rodzajBiletu) {
		this.rodzajBiletu = rodzajBiletu;
	}
	public double getCena() {
		return cena;
	}
	public void setCena(double cena) {
		this.cena = cena;
	}
	
	
}
