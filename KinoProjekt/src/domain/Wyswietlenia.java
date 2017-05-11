package domain;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Wyswietlenia {

	public Wyswietlenia(){

	}
	
	public Wyswietlenia(Calendar dzien,Calendar godzina,Sala sala){
		this.dzien=dzien.getTime().toLocaleString().substring(0, 10);
		this.godzina=godzina.getTime().toString().substring(11, 19);
		this.sala=sala;
	}
	
	@Id
	@GeneratedValue
	private long id;
	
	private String dzien;
	private String godzina;
	//@Transient

	@ManyToOne
	@JoinColumn(name="sala_ID")
	private Sala sala;
	
	private int wolneMiejsca;
	
	public int getWolneMiejsca() {
		return wolneMiejsca;
	}
	public void setWolneMiejsca(int wolneMiejsca) {
		this.wolneMiejsca = wolneMiejsca;
	}

	@OneToMany//(mappedBy="wyswietlenia")	
	@JoinColumn(name="wyswietleniaID")
	private List<Miejsca> miejsca;
	
	public Sala getSala() {
		return sala;
	}
	public void setSala(Sala sala) {
		this.sala = sala;
	}
	public String getDzien() {
		return dzien;
	}
	public void setDzien(String dzien) {
		this.dzien = dzien;
	}
	public String getGodzina() {
		return godzina;
	}
	public void setGodzina(String godzina) {
		this.godzina = godzina;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public List<Miejsca> getMiejsca() {
		return miejsca;
	}
	public void setMiejsca(List<Miejsca> miejsca) {
		this.miejsca = miejsca;
	}
	
	

}
