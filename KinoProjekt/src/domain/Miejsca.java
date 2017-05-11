package domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Miejsca {

	public Miejsca(){

	}
	
	public Miejsca(int rzad,int kolumna){
		this.rzad=rzad;
		this.kolumna=kolumna;
		this.zajete=false;
	}

	@Id
	@GeneratedValue
	private long id;
	
	private int rzad;
	private int kolumna;
	private boolean zajete;


	public int getRzad() {
		return rzad;
	}
	public int getKolumna() {
		return kolumna;
	}

	public boolean isZajete() {
		return zajete;
	}
	public void setZajete(boolean zajete) {
		this.zajete = zajete;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public void setRzad(int rzad) {
		this.rzad = rzad;
	}
	public void setKolumna(int kolumna) {
		this.kolumna = kolumna;
	}

	
	
}
