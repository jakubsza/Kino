import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Kino;
import domain.Klient;
import domain.Pracownik;

public class Main{
	
	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager entityManager;
	public static Scanner dane;
	
	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("myDatabase");
		entityManager = entityManagerFactory.createEntityManager();
		
		Klient klient = new Klient(entityManagerFactory,entityManager);
		Kino kino = new Kino(entityManagerFactory,entityManager);
		Pracownik pracownik = new Pracownik(entityManagerFactory,entityManager);
		
		//dodanie wszystkich encji
		kino.przygotujSQLa();
		
		System.out.println("Witamy w Kinie Olkusz!\nJe�li jeste� pracownikiem wybierz 1, je�li klientem wybierz 2");
		
		dane = new Scanner(System.in);
		
		boolean ok=false;
		int wynik = 0;

		//try-catch dla podania z�ych danych
		while(!ok){
		try{
			wynik=Integer.parseInt(dane.next());
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z�y numer, podaj jeszcze raz");
				continue;
			}
		ok=true;
		}
		
		switch(wynik){
		case 1: pracownik.pracownik(); break;
		case 2: klient.klient();break;
		}
		
		entityManager.close();
		entityManagerFactory.close();

	}

}
