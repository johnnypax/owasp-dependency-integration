package dev.archety.lez01_database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;

import dev.archety.lez01_database.models.Studente;

public class App 
{
    public static void main( String[] args )
    {

    	try {
	    	MysqlDataSource ds = new MysqlDataSource();
	    	ds.setServerName("10.5.69.3");
	    	ds.setPortNumber(3306);
	    	ds.setUser("root");
	    	ds.setPassword("8569");
	    	ds.setDatabaseName("lez01_scuola");
			ds.setUseSSL(false);
	    	ds.setAllowPublicKeyRetrieval(true);
	    	
	    	Connection conn = ds.getConnection();
	    	
	    	/*
	    	String query = "SELECT studenteID, nome, cognome, matricola FROM Studenti;";
	    	PreparedStatement ps = conn.prepareStatement(query);
	    	
	    	ResultSet rs = ps.executeQuery();
	    	while(rs.next()) {
	    		Studente stu = new Studente();
	    		stu.setId( rs.getInt(1) );
	    		stu.setNome( rs.getString(2) );
	    		stu.setCognome( rs.getString(3) );
	    		stu.setMatricola( rs.getString(4) );
	    		
	    		System.out.println(stu);
	    	}
	    	*/
	    	
	    	String query = "SELECT * FROM Studenti;";
	    	PreparedStatement ps = conn.prepareStatement(query);
	    	
	    	ResultSet rs = ps.executeQuery();
	    	while(rs.next()) {
	    		Studente stu = new Studente();
	    		stu.setId( rs.getInt("studenteID") );
	    		stu.setNome( rs.getString("nome") );
	    		stu.setCognome( rs.getString("cognome") );
	    		stu.setMatricola( rs.getString("matricola") );
	    		
	    		System.out.println(stu);
	    	}
	    	
	    	conn.close();
	    	
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	
    	
    	
    }
}
