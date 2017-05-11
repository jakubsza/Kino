package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sala {

	public Sala(){

	}
	
	public Sala(int rzedy, int kolumny){
		this.rzedy=rzedy;
		this.kolumny=kolumny;
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRzedy() {
		return rzedy;
	}

	public int getKolumny() {
		return kolumny;
	}
	
	@OneToMany(mappedBy="sala")	
	private List<Wyswietlenia> wyswietlenia;

	private int rzedy;
	private int kolumny;
	

}
