# TASK PRATICO

Creare un report di analisi statica per il seguente progetto MAVEN:

User.java

```java
package it.leonardo.securelogin;

public class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
}

```

UserService.java

```java
package it.leonardo.securelogin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserService {

    public void registerUser(User user) {
        String query = "INSERT INTO Users (username, password) VALUES ('" + user.getUsername() + "', '" + user.getPassword() + "')";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Utente registrato.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean login(String username, String password) {
        String query = "SELECT * FROM Users WHERE username = '" + username + "' AND password = '" + password + "'";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
```

DatabaseConnection.java

```java
package it.leonardo.securelogin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/login_db";
    private static final String USER = "root";
    private static final String PASS = "admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }
}

```

App.java

```java
package it.leonardo.securelogin;

public class App {
    public static void main(String[] args) {
        UserService userService = new UserService();

        User user = new User("admin", "password123");

        userService.registerUser(user);

        boolean success = userService.login("admin' --", "any");
        System.out.println("Accesso: " + (success ? "concesso" : "negato"));
    }
}

```

POM.xml

```java
...
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>

<dependency>
    <groupId>org.mindrot</groupId>
    <artifactId>jbcrypt</artifactId>
    <version>0.4</version>
</dependency>
 ...
```

# DOMANDA 1

Cosa si intende per **vulnerabilità zero-day** e perché è una minaccia critica.

# DOMANDA 2

Analizzare le **fasi di risposta a un incidente**: Preparazione, Rilevamento, Analisi, Contenimento, Eradicazione, Recupero.
