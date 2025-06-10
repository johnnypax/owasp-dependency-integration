package dev.archety.lez02_es_dal;

import java.sql.SQLException;

import dev.archety.lez02_es_dal.models.Contatto;
import dev.archety.lez02_es_dal.models.ContattoDAL;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Contatto gio = new Contatto("Giovanni", "Pace", "gio@pace.com", "123456");
    	
    	try {
			ContattoDAL dal = new ContattoDAL();
			
//			boolean risultato = dal.Insert(gio);
//			if(risultato)
//				System.out.println("STAPPOOOOOO");
//			else
//				System.out.println("ERRORE ;(");
			
//			dal.CercaPerEmail("laura.bianchi@example.com' -- ");
			dal.CercaPerEmail("' OR 1=1; DROP TABLE Contatto; -- ");
			
//			dal.CercaPerEmailSanificata("' OR 1=1; DROP TABLE Contatto; -- ");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
