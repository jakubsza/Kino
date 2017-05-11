package domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Film {

	public Film(){

	}
	
	public Film(String tytul,int czasTrwania,int ograniczenieWiekowe){
		this.tytul=tytul;
		this.czasTrwania=czasTrwania;
		this.ograniczenieWiekowe=ograniczenieWiekowe;
	}
	
	public Film(String tytul,int czasTrwania,int ograniczenieWiekowe, List<Wyswietlenia> wyswietlenia){
		this.wyswietlenia=wyswietlenia;
		this.tytul=tytul;
		this.czasTrwania=czasTrwania;
		this.ograniczenieWiekowe=ograniczenieWiekowe;
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	@OneToMany			//(mappedBy="film")
	@JoinColumn(name="Film_ID")
	private List<Wyswietlenia> wyswietlenia;
	
	public List<Wyswietlenia> getWyswietlenia() {
		return wyswietlenia;
	}

	public void setWyswietlenia(List<Wyswietlenia> wyswietlenia) {
		this.wyswietlenia = wyswietlenia;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	private String tytul;
	private int czasTrwania; // czas trwania filmu w minutach
	private int ograniczenieWiekowe;
	public String getTytul() {
		return tytul;
	}

	public int getCzasTrwania() {
		return czasTrwania;
	}

	public int getOgraniczenieWiekowe() {
		return ograniczenieWiekowe;
	}


}
