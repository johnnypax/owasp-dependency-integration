# TASK 2 - Progetto Blog

Creare una report di analisi statica per il seguente progetto MAVEN:

*App.java*

```java
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

```

User.java

```java
package it.leonardo.lez04_blog.classes;

public class User {
    private String username;
    private String password;
    private String email;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

```

UserDAL.java

```java
package it.leonardo.lez04_blog.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAL {
	private Connection getConnection() throws SQLException {
        // Modifica con i dettagli del tuo database
        String url = "jdbc:mysql://localhost:3306/yourdatabase";
        String username = "yourusername";
        String password = "yourpassword";
        return DriverManager.getConnection(url, username, password);
    }

    public boolean insert(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, user.getUsername());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getEmail());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        }
    }

    public ArrayList<User> findAll() throws SQLException {
        String sql = "SELECT * FROM users";
        ArrayList<User> userList = new ArrayList<>();
        
        try (Connection conn = getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                User user = new User(username, password, email);
                userList.add(user);
            }
        }
        
        return userList;
    }
}

```

pom.xml

```
...
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    
	<dependency>
	    <groupId>mysql</groupId>
	    <artifactId>mysql-connector-java</artifactId>
	    <version>5.1.40</version>
	</dependency>
	
	<dependency>
	    <groupId>org.testng</groupId>
	    <artifactId>testng</artifactId>
	    <version>7.0.0</version>
	    <scope>test</scope>
	</dependency>

  </dependencies>
...
```

