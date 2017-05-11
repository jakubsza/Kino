package domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Kino {
	
	public Kino(EntityManagerFactory entityManagerFactory,EntityManager entityManager){
		this.entityManagerFactory=entityManagerFactory;
		this.entityManager=entityManager;
		dane = new Scanner(System.in);
	}
	
	public  EntityManagerFactory entityManagerFactory;
	public  EntityManager entityManager;
	public  Scanner dane;
	
public void przygotujSQLa(){
		
		/*Sala salaA = new Sala(10,10);
		Sala salaB = new Sala(12,14);
		Sala salaC = new Sala(8,6);*/
		
		Pracownicy pracownik1 = new Pracownicy("e568532","123haha");
		Pracownicy pracownik2 = new Pracownicy("e598452","edzia15");
		Pracownicy pracownik3 = new Pracownicy("e685952","t4d4k");
		Pracownicy pracownik4 = new Pracownicy("e566852","h3h3szki");
		
		Kody kod1 = new Kody("a8ksp34",0.1);
		Kody kod2 = new Kody("sko58a6",0.1);
		Kody kod3 = new Kody("586as2w",0.1);
		Kody kod4 = new Kody("86a4z85",0.15);
		Kody kod5 = new Kody("jdkas86",0.20);
		Kody kod6 = new Kody("qw18385",0.25);
		Kody kod7 = new Kody("186588a",0.50);
		
		Sala salaA = new Sala(2,1);
		Sala salaB = new Sala(5,3);
		Sala salaC = new Sala(1,1);
		
		Wyswietlenia wyswietlenia1 = new Wyswietlenia(new GregorianCalendar(2017,4,12),new GregorianCalendar(0,0,0,12,0),salaA);
		Wyswietlenia wyswietlenia2 = new Wyswietlenia(new GregorianCalendar(2017,4,12),new GregorianCalendar(0,0,0,18,0),salaB);
		Wyswietlenia wyswietlenia3 = new Wyswietlenia(new GregorianCalendar(2017,4,13),new GregorianCalendar(0,0,0,12,0),salaA);
		Wyswietlenia wyswietlenia4 = new Wyswietlenia(new GregorianCalendar(2017,4,14),new GregorianCalendar(0,0,0,12,0),salaB);
		Wyswietlenia wyswietlenia5 = new Wyswietlenia(new GregorianCalendar(2017,4,15),new GregorianCalendar(0,0,0,20,0),salaB);
		Wyswietlenia wyswietlenia6 = new Wyswietlenia(new GregorianCalendar(2017,4,12),new GregorianCalendar(0,0,0,14,0),salaC);
		Wyswietlenia wyswietlenia7 = new Wyswietlenia(new GregorianCalendar(2017,4,13),new GregorianCalendar(0,0,0,16,0),salaC);
		
		przygotujSale(wyswietlenia1);
		przygotujSale(wyswietlenia2);
		przygotujSale(wyswietlenia3);
		przygotujSale(wyswietlenia4);
		przygotujSale(wyswietlenia5);
		przygotujSale(wyswietlenia6);
		przygotujSale(wyswietlenia7);
		
		losowoZabukujSale(wyswietlenia1);
		losowoZabukujSale(wyswietlenia2);
		losowoZabukujSale(wyswietlenia3);
		losowoZabukujSale(wyswietlenia4);
		losowoZabukujSale(wyswietlenia5);
		losowoZabukujSale(wyswietlenia6);
		losowoZabukujSale(wyswietlenia7);
		
		List<Wyswietlenia> wyswietleniaA = new ArrayList<Wyswietlenia>();
		List<Wyswietlenia> wyswietleniaB = new ArrayList<Wyswietlenia>();
		List<Wyswietlenia> wyswietleniaC = new ArrayList<Wyswietlenia>();
		
		wyswietleniaA.add(wyswietlenia1);
		wyswietleniaA.add(wyswietlenia2);
		wyswietleniaA.add(wyswietlenia3);
		wyswietleniaA.add(wyswietlenia4);
		wyswietleniaA.add(wyswietlenia5);
		wyswietleniaB.add(wyswietlenia6);
		wyswietleniaC.add(wyswietlenia7);
		
		Film film1 = new Film("Film A",123,15,wyswietleniaA);
		Film film2 = new Film("Film B",153,18,wyswietleniaB);
		Film film3 = new Film("Film C",92,0,wyswietleniaC);
		
		Bilety bilet1 = new Bilety("Normalny", 35.00);
		Bilety bilet2 = new Bilety("Ulogowy", 17.50);
		
		entityManager.getTransaction().begin();
		
		
		entityManager.persist(film1);
		entityManager.persist(film2);
		entityManager.persist(film3);
		entityManager.persist(wyswietlenia1);
		entityManager.persist(wyswietlenia2);
		entityManager.persist(wyswietlenia3);
		entityManager.persist(wyswietlenia4);
		entityManager.persist(wyswietlenia5);
		entityManager.persist(wyswietlenia6);
		entityManager.persist(wyswietlenia7);
		entityManager.persist(salaA);
		entityManager.persist(salaB);
		entityManager.persist(salaC);
		entityManager.persist(bilet1);
		entityManager.persist(bilet2);
		entityManager.persist(kod1);
		entityManager.persist(kod2);
		entityManager.persist(kod3);
		entityManager.persist(kod4);
		entityManager.persist(kod5);
		entityManager.persist(kod6);
		entityManager.persist(kod7);
		entityManager.persist(pracownik1);
		entityManager.persist(pracownik2);
		entityManager.persist(pracownik3);
		entityManager.persist(pracownik4);


		entityManager.getTransaction().commit();

	}
	
	public void losowoZabukujSale(Wyswietlenia wyswietlenia){
		int wynik;
		for(int i=0;i<wyswietlenia.getSala().getRzedy()*wyswietlenia.getSala().getKolumny()*0.5;i++){
			wynik=(int)(Math.random()*wyswietlenia.getSala().getRzedy()*wyswietlenia.getSala().getKolumny());
			if(!wyswietlenia.getMiejsca().get(wynik).isZajete()){
				wyswietlenia.setWolneMiejsca(wyswietlenia.getWolneMiejsca()-1);;
			}
			wyswietlenia.getMiejsca().get(wynik).setZajete(true);
			// entityManager.refresh(wyswietlenia);
		}

	}
	
	public void przygotujSale(Wyswietlenia wyswietlenia){
		wyswietlenia.setWolneMiejsca(wyswietlenia.getSala().getKolumny()*wyswietlenia.getSala().getRzedy());
		List<Miejsca> miejsca = new ArrayList<Miejsca>();
		for(int i=0;i<wyswietlenia.getSala().getRzedy();i++)
			for (int j=0;j<wyswietlenia.getSala().getKolumny();j++){
				Miejsca miejsce = new Miejsca(i,j);
				miejsca.add(miejsce);
			
				entityManager.getTransaction().begin();
				
				entityManager.persist(miejsce);

				entityManager.getTransaction().commit();
			}
		 				wyswietlenia.setMiejsca(miejsca);
	}

}
