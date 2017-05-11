package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class Klient {

	public Klient(EntityManagerFactory entityManagerFactory,EntityManager entityManager){
		this.entityManagerFactory=entityManagerFactory;
		this.entityManager=entityManager;
		dane = new Scanner(System.in);
	}
	
	public  EntityManagerFactory entityManagerFactory;
	public  EntityManager entityManager;
	public  Scanner dane;
	
	public  void klient(){
		
		System.out.println("Na jaki chcesz film siê wybraæ?");
		
		//zapytanie przeszukuj¹ce tablicê Film
		TypedQuery<Film> query = entityManager.createQuery("select f from Film f", Film.class);
		List<Film> wyniki = query.getResultList();	
		
		int i=0;
		
		//wypisanie tytu³ów
		for(Film tytul:wyniki)
			System.out.println(++i + " - " + tytul.getTytul());
		
		boolean ok=false;
		
		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			i=Integer.parseInt(dane.next());
			try{
			System.out.println("Wybrano "+wyniki.get(i-1).getTytul());
			ok=true;
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("Wybrano z³y numer, podaj jeszcze raz");
				continue;
			}
			ok=true;
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y numer, podaj jeszcze raz");
				continue;
			}
		}
		

		
		System.out.println("Wybierz dzieñ, w którym chcesz obejrzeæ film");
		
		
		//zapytanie wypisuj¹ce unikalne dni dla danego filmu
		Query query2 = entityManager.createQuery("select distinct dzien from Wyswietlenia w where Film_ID =(select f from Film f where tytul = ?1)");
		query2.setParameter(1, wyniki.get(i-1).getTytul());
		List<?> dzien = query2.getResultList();
		
		int j=0;
		
		//wypisanie unikalnych dni
		for(;j<dzien.size();)
			System.out.println(++j + " - " + dzien.get(j-1));
		
		ok=false;

		//try-catch dla podania z³ych danych
		while(!ok){
		try{
			j=Integer.parseInt(dane.next());
			try{
				System.out.println("Wybrano "+dzien.get(j-1));
			ok=true;
			}
			catch(IndexOutOfBoundsException e){
				System.out.println("Wybrano z³y numer, podaj jeszcze raz");
				continue;
			}
			ok=true;
		}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y numer, podaj jeszcze raz");
				continue;
			}
		}
		
		
		//wyci¹gniêcie filmID
		long filmID=wyniki.get(i-1).getId();
		
		System.out.println("Wybierz godzinê");
		
		//zapytanie wyszukuj¹ce godziny dla wybranego wczeœniej filmu i dnia
		Query query3 = entityManager.createQuery("select godzina from Wyswietlenia w where Film_ID = ?1 and dzien =?2");
		query3.setParameter(1, filmID);
		query3.setParameter(2, dzien.get(j-1));
		List<String> godzina = query3.getResultList();
		
		//pomocnicze zapytanie wyci¹gaj¹ce info ile jest wolnych miejsc w danej sali na dany dzieñ, godzinê i film
		Query query31 = entityManager.createQuery("select wolneMiejsca from Wyswietlenia w where Film_ID = ?1 and dzien =?2");
		query31.setParameter(1, filmID);
		query31.setParameter(2, dzien.get(j-1));
		List<Integer> id31 = query31.getResultList();
		
		int k=0;
		
		//try-catch dla podania z³ych danych
		for(;k<godzina.size();)
			System.out.println(++k + " - " + godzina.get(k-1));
		
		ok=false;
		
		while(!ok){
			try{
				k=Integer.parseInt(dane.next());
				try{
					System.out.println("Wybrano film "+wyniki.get(i-1).getTytul()+", który bêdzie grany dnia "
							+dzien.get(j-1)+" o godzinie "+godzina.get(k-1));
				ok=true;
				}
				catch(IndexOutOfBoundsException e){
					continue;
				}
				ok=true;
			}
				catch(NumberFormatException e){
					System.out.println("Wybrano z³y numer, podaj jeszcze raz");
					continue;
				}
			}
		
		System.out.println("Wybierz rodzaj biletu");
		
		//zapytanie wyszukuj¹ce mo¿liwe bilety
		TypedQuery<Bilety> query4 = entityManager.createQuery("select b from Bilety b",Bilety.class);
		List<Bilety> bilet = query4.getResultList();
		
		List<String> bilety = new ArrayList<String>();
		double suma=0.0;
		
		int l=0;
		int iscdalej=0;
	
		//mo¿na dodawaæ bilety dopóki u¿ytkownik chce to robiæ lub braknie miejsc
		while(iscdalej==0 && bilety.size()<id31.get(k-1)){
			
		for(int m=0;m<bilet.size();)
			System.out.println(++m + " - " + bilet.get(m-1).getRodzajBiletu());
			

			
			ok=false;

			//try-catch dla podania z³ych danych
			while(!ok){
				try{
					l=Integer.parseInt(dane.next());
					try{
						System.out.println("Dodano bilet "+bilet.get(l-1).getRodzajBiletu());
					ok=true;
					}
					catch(IndexOutOfBoundsException e){
						System.out.println("Wybrano z³y numer, podaj jeszcze raz");
						continue;
					}
					ok=true;
				}
					catch(NumberFormatException e){
						System.out.println("Wybrano z³y numer, podaj jeszcze raz");
						continue;
					}
			}
			
		bilety.add(bilet.get(l-1).getRodzajBiletu());
		suma+=bilet.get(l-1).getCena();
		
		//sprawdzamy czy s¹ wolne miejsca jak nie, to koniec. 
		if (bilety.size()==id31.get(k-1)) System.out.println("Przepraszamy, ale sala jest ju¿ pe³na - nie mo¿na"
				+ "dodaæ wiêcej biletów");
		
		//gdy s¹ wolne miejsca jedziemy dalej
		else{
		
			System.out.println("0 - dodaj kolejny bilet");
			System.out.println("1 - przejdz dalej");
			
			
			
			boolean ok2=false;
	
			//try-catch dla podania z³ych danych
			while(!ok2 && (iscdalej==1 || iscdalej==0)){
				try{
					iscdalej=Integer.parseInt(dane.next());
	
				}
					catch(NumberFormatException e){
						System.out.println("Wybrano z³y numer, podaj jeszcze raz");
						continue;
					}
				if(iscdalej==1 || iscdalej==0)ok2=true;
				}
			}

		
		}
		
		System.out.println("Czy posiadasz jakies kody promocyjne?\n"+
				" 1 - tak\n"+
				" 2 - nie");
		
		ok=false;
		int wybor = 0;
		
		while(!ok){
			try{
				wybor=Integer.parseInt(dane.next());
				ok=true;
			}
			catch(NumberFormatException e){
				System.out.println("Wybrano z³y numer, podaj jeszcze raz");
				continue;
			}
		}
			
		//zapytanie czy klient chce podaæ numer
		if(wybor==1){
			
			//wyszukiwanie kodów promocyjnych
			TypedQuery<Kody> query5 = entityManager.createQuery("select k from Kody k",Kody.class);
			List<Kody> kody = query5.getResultList();
			
			System.out.println("Podaj kod");
		
			boolean jest_kod=false;
			
			while(!jest_kod){
			
				String kod=dane.next();
				
				if(kod.toUpperCase().equals("X"))jest_kod=true;
				
				else{
				
				for(Kody pomoc : kody)
						if (pomoc.getKod().equals(kod)){
							suma=suma*(1-pomoc.getZnizka());
							jest_kod=true;
						}
					
					if(!jest_kod){
						
						System.out.println("Podany kod jest niepoprawny. Podaj inny kod lub nacisnij 'X', gdy juz nie chcesz"
								+ " podawaæ kodów");
						
					}
				}
			}

		
		}
		
		System.out.println("Wybierz miejsce:\nX - zarezerwowane\nO - wolne");
		
		//pomocnicze zapytanie dotycz¹ce wyœwietleñ, dziêki niemu mo¿emy wypisaæ wolne i zajête miejsca w sali
		TypedQuery<Wyswietlenia> query6 = entityManager.createQuery("select w from Wyswietlenia w where Film_ID = ?1 and dzien =?2 and godzina=?3",Wyswietlenia.class);
		query6.setParameter(1, filmID);
		query6.setParameter(2, dzien.get(j-1));
		query6.setParameter(3, godzina.get(k-1));
		
		List<Wyswietlenia> sala = query6.getResultList();
		
		//funkcja wypisuj¹ca wolne i zajête miejsca w sali
		wypiszMiejsca(sala.get(0).getSala().getKolumny(),sala.get(0).getSala().getRzedy(),sala.get(0));
		
		int kolumna = 0;
		int rzad = 0;
		
		ok=false;
		boolean ok2=false;
		boolean zajete=false;
		
		for (int ii=0;ii<bilety.size();ii++){
			
			System.out.println("Podaj rz¹d dla biletu nr "+(ii+1)+" : ");
			
			while(!ok){
				while(!ok2){
					try{
					rzad=Integer.parseInt(dane.next())-1;
					}
					catch(NumberFormatException e){
						System.out.println("Wybrano z³y numer, podaj jeszcze raz");
						continue;
					}
					ok2=true;
				}
			
			System.out.println("Podaj kolumnê dla biletu nr "+(ii+1)+" : ");
			kolumna = ((int) dane.next().toUpperCase().charAt(0))-65;		//A-0 etc
			
			
				try{
				//miejsce (1,A) = 1
				zajete=sala.get(0).getMiejsca().get(rzad+kolumna).isZajete();
				}
				catch(IndexOutOfBoundsException e){
					System.out.println("Wybrano z³e numery, podaj jeszcze raz2");
					ok2=false;
					continue;
				}
				ok=true;
			}
			
			
			while(zajete){
				System.out.println("Wybrane miejsce jest ju¿ zajête");
				System.out.println("Podaj inny rz¹d dla biletu nr "+(ii+1)+" : ");
				rzad=dane.nextInt()-1;
				System.out.println("Podaj inny kolumnê dla biletu nr "+(ii+1)+" : ");
				kolumna = ((int) dane.next().charAt(0))-65;
			}
					sala.get(0).getMiejsca().get(rzad+kolumna).setZajete(true);
					
			
			entityManager.refresh(sala.get(0));//.getMiejsca().get(rzad+kolumna));
		}
		
		System.out.println("Gratulacjê! Uda³o siê zarezerwowaæ bilety.");
		System.out.println("Kwota do zap³aty: "+suma);
			
	}
	
	public void wypiszMiejsca(int kolumny, int rzedy, Wyswietlenia miejsca){
		
		//najpier ekran
		for(int j=0;j<kolumny;j++)
			System.out.print("--");
		System.out.print("\n");
		
		//wszystkie rzêdy i kolumny
		for(int i=0;i<rzedy*kolumny;i++){
				if(miejsca.getMiejsca().get(i).isZajete())System.out.print("X ");
				else System.out.print("O ");
				
			//rzêdy oznaczmy numerami
			if((i+1)%kolumny==0)
			System.out.print(" "+(i/kolumny+1)+"\n");
			}
		
		//a kolumny cyframi
		for(int j=0;j<kolumny;j++)
			System.out.print((char)(j+65)+" ");
		System.out.println();
	}

	
}
