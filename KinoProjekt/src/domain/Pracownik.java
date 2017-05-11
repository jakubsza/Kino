package domain;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class Pracownik {

	public Pracownik(EntityManagerFactory entityManagerFactory,EntityManager entityManager){
		this.entityManagerFactory=entityManagerFactory;
		this.entityManager=entityManager;
		dane = new Scanner(System.in);
	}
	
	public  EntityManagerFactory entityManagerFactory;
	public  EntityManager entityManager;
	public  Scanner dane;
	
	public void pracownik(){
/*
		System.out.println("Zaloguj siê\n"
				+ "Podaj numer ID");
		
		//wyszukiwanie loginów pracowników
		TypedQuery<Pracownicy> query1 = entityManager.createQuery("select p from Pracownicy p",Pracownicy.class);
		List<Pracownicy> pracownicy = query1.getResultList();
	
		boolean jest_kod=false;
		String numer = null;
		
		//podawanie numeruID i sprawdzanie czy istnieje taki pracownik
		while(!jest_kod){
		
			numer=dane.next();
			
			if(numer.toUpperCase().equals("X"))jest_kod=true;
			
			else{
			
			for(Pracownicy pomoc : pracownicy)
					if (pomoc.getNumerID().equals(numer)){
						jest_kod=true;
					}
				
				if(!jest_kod){
					
					System.out.println("Podany numerID jest niepoprawny. Podaj inny numer lub nacisnij 'X', gdy ju¿ nie chcesz"
							+ " siê logowaæ");
					
				}
			}
		}
			
			jest_kod=false;
			
			System.out.println("Podaj has³o");
			
			//podawanie i sprawdzanei has³a
			while(!jest_kod){
			
				String haslo=dane.next();
				
				if(numer.toUpperCase().equals("X"))jest_kod=true;
				
				else{
				
				for(Pracownicy pomoc : pracownicy)
						if (pomoc.getNumerID().equals(numer) && pomoc.getHaslo().equals(haslo)){
							jest_kod=true;
						}
					
					if(!jest_kod){
						
						System.out.println("Podany has³ow jest niepoprawna. Podaj inne has³o lub nacisnij 'X', gdy ju¿ nie chcesz"
								+ " siê logowaæ");
						
					}
				}
		}
		
			*/
		zarzadzajKinem();
		
		
		}
	

	private void zarzadzajKinem() {
		
		boolean koniec=false;
		
		while(!koniec){
		
			System.out.println("Wybierz co chcesz zrobiæ\n"
					+ "1 - dodaj bilet\n"
					+ "2 - dodaj film\n"
					+ "3 - dodaj salê\n"
					+ "4 - dodaj wyœwietlenia\n"
					+ "5 - dodaj kody\n");
			
			
			boolean ok=false;
			int wynik = 0;
	
			//try-catch dla podania z³ych danych
			while(!ok){
			try{
				wynik=Integer.parseInt(dane.next());
			}
				catch(NumberFormatException e){
					System.out.println("Wybrano z³y numer, podaj jeszcze raz");
					continue;
				}
			if (wynik==1 || wynik==2 || wynik==3 || wynik==4 || wynik==5)ok=true;
			else System.out.println("Wybrano z³y numer, podaj jeszcze raz");
	
			}
			
			switch(wynik){
			case 1: bilet(); break;
			case 2: film();break;
			case 3: sala();break;
			case 4: wyswietlenia();break;
			case 5: kody();break;
			}
		
			System.out.println("Jeœli chcesz siê wylogowaæ naciœnij 'X', jeœli nie, to naciœnij dowolny klawisz");
			
			String coDalej=dane.next();
			
			if(coDalej.toUpperCase().equals("X"))koniec=true;
		
		}
		
	}
	
	public void bilet(){
		
		String nazwa;
		double cena = 0;
		
		System.out.println("Podaj rodzaj biletu");
		
		nazwa=dane.next();
		
		System.out.println("Podaj cenê biletu");
		
		boolean ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			cena=Double.parseDouble(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³¹ cenê, podaj jeszcze raz");
				continue;
			}
		if(cena>0)ok=true;
		else System.out.println("Cena jest ujemna, proszê podaæ inn¹");
		}
		
		Bilety bilet = new Bilety(nazwa,cena);
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(bilet);

		entityManager.getTransaction().commit();
	}
	
	public void film(){
		
		String tytul;
		int czasTrwania = 0;
		int ograniczenia = 0;
		
		System.out.println("Podaj tytu³ filmu");
		
		tytul=dane.next();
		
		System.out.println("Podaj czas trwania filmu");
		
		boolean ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			czasTrwania=Integer.parseInt(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y czas, podaj jeszcze raz");
				continue;
			}
		if(czasTrwania>0)ok=true;
		else System.out.println("Czas jest ujemny, proszê podaæ inny");
		}
		
		ok=false;
		
		System.out.println("Podaj ograniczenia wiekowe dla filmu");

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			ograniczenia=Integer.parseInt(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y czas, podaj jeszcze raz");
				continue;
			}
		if(ograniczenia>=0)ok=true;
		else System.out.println("Ograniczenia wiekowe nie mog¹ byæ ujemne, proszê podaæ inne");
		}
		
		
		Film film = new Film(tytul, czasTrwania, ograniczenia);
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(film);

		entityManager.getTransaction().commit();
		
	}
	
	public void sala(){
		
		int rzedy = 0;
		int kolumny = 0;
		
		System.out.println("Podaj liczbê rzêdów");
		
		boolean ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			rzedy=Integer.parseInt(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y czas, podaj jeszcze raz");
				continue;
			}
		if(rzedy>0)ok=true;
		else System.out.println("Liczba rzêdów nie mo¿e byæ ujemna, proszê podaæ inn¹");
		}
		
		System.out.println("Podaj liczbê kolumn");
		
		ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			kolumny=Integer.parseInt(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y czas, podaj jeszcze raz");
				continue;
			}
		if(kolumny>0)ok=true;
		else System.out.println("Liczba kolumn nie mo¿e byæ ujemna, proszê podaæ inn¹");
		}
		
		Sala sala = new Sala(rzedy,kolumny);
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(sala);

		entityManager.getTransaction().commit();
		
	}
	
	public Sala salaPomoc(){
		
		int rzedy = 0;
		int kolumny = 0;
		
		System.out.println("Podaj liczbê rzêdów");
		
		boolean ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			rzedy=Integer.parseInt(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y czas, podaj jeszcze raz");
				continue;
			}
		if(rzedy>0)ok=true;
		else System.out.println("Liczba rzêdów nie mo¿e byæ ujemna, proszê podaæ inn¹");
		}
		
		System.out.println("Podaj liczbê kolumn");
		
		ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			kolumny=Integer.parseInt(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y czas, podaj jeszcze raz");
				continue;
			}
		if(kolumny>0)ok=true;
		else System.out.println("Liczba kolumn nie mo¿e byæ ujemna, proszê podaæ inn¹");
		}
		
		Sala sala = new Sala(rzedy,kolumny);
		
		return sala;
		
	}
	
	public void wyswietlenia(){
		
		Calendar dzien = Calendar.getInstance();
		Calendar godzina = Calendar.getInstance();
		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss");
		
		String dzienS;
		String godzinaS;
		
		System.out.println("Podaj dzieñ wyœwietlenia filmu w formacie dd/MM/yyyy");
		
		boolean ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			dzienS=dane.next();
			dzien.setTime(sdf1.parse(dzienS));
		}
			catch(ParseException e){
				System.out.println("Wybrano z³¹ datê, podaj jeszcze raz");
				continue;
			}
		ok=true;
		}
		
		System.out.println("Podaj godzine wyœwietlenia filmu w formacie HH:mm:ss");
		
		ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			godzinaS=dane.next();
			godzina.setTime(sdf2.parse(godzinaS));
		}
			catch(ParseException e){
				System.out.println("Wybrano z³¹ godzinê, podaj jeszcze raz");
				continue;
			}
		ok=true;
		}
		//dzien = new GregorianCalendar(2017,4,12);
		//godzina = new GregorianCalendar(0,0,0,12,0);
		
		Sala sala = salaPomoc();
		
		Wyswietlenia wyswietlenia = new Wyswietlenia(dzien,godzina,sala);
		

		entityManager.getTransaction().begin();
		
		entityManager.persist(sala);
		entityManager.persist(wyswietlenia);

		entityManager.getTransaction().commit();
		
	}
	
	public void kody(){
		
		String kod;
		double znizka = 0;
		
		System.out.println("Podaj kod zni¿ki");
		
		kod=dane.next();
		
		System.out.println("Podaj procent zni¿ki");
		
		boolean ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			znizka=Double.parseDouble(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³¹ cenê, podaj jeszcze raz");
				continue;
			}
		if(znizka>0 && znizka<=1)ok=true;
		else System.out.println("Zni¿ka jest ujemna, proszê podaæ inn¹");
		if(znizka>1) System.out.println("Zni¿ka nie mo¿e mo¿e byæ wiêksza ni¿ 100%, proszê podaæ inn¹");
		}
		
		Bilety kody = new Bilety(kod,znizka);
		
		entityManager.getTransaction().begin();
		
		entityManager.persist(kody);

		entityManager.getTransaction().commit();
		
	}
}
