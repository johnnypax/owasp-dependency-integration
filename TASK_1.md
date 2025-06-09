# Creare una analisi statica del seguente codice sorgente:

File: *App.java*

```java
//App.java

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
```

File: *Studente.java*

```java
package dev.archety.lez01_database.models;

public class Studente {

	private int id;
	private String nome;
	private String cognome;
	private String matricola;
	
	public Studente() {
		
	}
	
	public Studente(int id, String nome, String cognome, String matricola) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.matricola = matricola;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public String getMatricola() {
		return matricola;
	}
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
}
```

