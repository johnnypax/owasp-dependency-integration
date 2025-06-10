package dev.archety.lez02_es_dal.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.MysqlDataSource;
import com.mysql.cj.protocol.Resultset;

public class ContattoDAL {

	private MysqlDataSource ds;
	
	public ContattoDAL() throws SQLException{
		ds = new MysqlDataSource();
    	ds.setServerName("localhost");
    	ds.setPortNumber(3306);
    	ds.setUser("root");
    	ds.setPassword("toor");
    	ds.setDatabaseName("its_recap_01_rubrica");
		ds.setUseSSL(false);
    	ds.setAllowPublicKeyRetrieval(true);
	}
	
	public boolean Insert(Contatto c)  throws SQLException{
		Connection conn = ds.getConnection();
		
		String query = "INSERT INTO Contatto (nome, cognome, email, telefono) "
				+ "VALUES ('" + c.getNome() + "', '" + c.getCogn() + "', '" + c.getMail() + "', '" + c.getTele() + "');";
		
		PreparedStatement ps = conn.prepareStatement(query);
		
		int righeCoinvolte = ps.executeUpdate();
		
		conn.close();
		
		if(righeCoinvolte > 0)
			return true;
		else
			return false;
		
//		conn.close();			//Qui non chiuderei la connessione ;(
	}
	
	public void CercaPerEmail(String varEmail)   throws SQLException{

		Connection conn = ds.getConnection();
		
		String query = "SELECT * FROM Contatto WHERE email = '" + varEmail +  "' AND tipologia = 'user'";
		System.out.println(query);
		PreparedStatement ps = conn.prepareStatement(query);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println( 
					"Id: " + rs.getInt("contattoID") + "\n" + 
					"Nome: " + rs.getString("nome") + "\n" + 
					"Coogn: " + rs.getString("cognome") + "\n" + 
					"Email: " + rs.getString("email") + "\n" + 
					"Tele: " + rs.getString("telefono") + "\n"
			);
		}
	}
	
	public void CercaPerEmailSanificata(String varEmail)   throws SQLException{

		Connection conn = ds.getConnection();
		
		String query = "SELECT * FROM Contatto WHERE email = ? AND tipologia = 'user'";
		System.out.println(query);
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setString(1, varEmail);
		
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			System.out.println( 
					"Id: " + rs.getInt("contattoID") + "\n" + 
					"Nome: " + rs.getString("nome") + "\n" + 
					"Coogn: " + rs.getString("cognome") + "\n" + 
					"Email: " + rs.getString("email") + "\n" + 
					"Tele: " + rs.getString("telefono") + "\n"
			);
		}
	}
}
