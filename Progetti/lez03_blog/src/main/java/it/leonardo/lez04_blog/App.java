package it.leonardo.lez04_blog;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import it.leonardo.lez04_blog.classes.User;
import it.leonardo.lez04_blog.classes.UserDAL;

public class App 
{
	public static void main(String[] args) {
		
		// I - Inserimento, S - Stampa, Q - Uscire dal programma
		Scanner interceptor = new Scanner(System.in);
		
		boolean insAbilitato = true;
		
		while(insAbilitato) {
			System.out.println("\nScegli l'operazione: \n"
					+ "I - Inserimento utente\n"
					+ "S - Stampa utenti\n"
					+ "Q - Uscire dal programma");
			
			String input = interceptor.nextLine();
			
			switch (input) {
			case "I":
				System.out.println("Inserisci il nome utente");
				String username = interceptor.nextLine();
				System.out.println("Inserisci la password");
				String password = interceptor.nextLine();
				System.out.println("Inserisci l'email");
				String email = interceptor.nextLine();
				
				User temp = new User(username, password, email);
				try {
					UserDAL userDalIns = new UserDAL();
					if(userDalIns.insert(temp))
						System.out.println("Inserimento utente completato");
					else
						System.out.println("Errore di inserimento utente");
				} catch (SQLException e) {
					System.out.println("ERRORE: " + e.getMessage());
				}
				break;
			case "S":
				try {
					UserDAL userDal = new UserDAL();
					ArrayList<User> elenco = userDal.findAll();
					
					for(int i=0; i<elenco.size(); i++) {
						System.out.println(elenco.get(i));
					}
					
				} catch (SQLException e) {
					System.out.println("ERRORE: " + e.getMessage());
				}
				break;
			case "Q":
				insAbilitato = false;
				break;
			default:
				System.out.println("Comando non riconosciuto");
				break;
			}
		}
		
		System.out.println("Programma terminato");
		
	}
}
