# TASK 4

Creare un report di analisi statica per il seguente progetto MAVEN:

App.java

```java
package it.leonardo.lez08_utenze;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main(String[] args) {
        LoggerExample loggerExample = new LoggerExample();
        loggerExample.logMessage("Hello Giovanni");

        DatabaseManager dbManager = new DatabaseManager();
        dbManager.connect();
        dbManager.insertData("Giovanni", "Pace");
        dbManager.getData();
        dbManager.closeConnection();
    }
}

```

DatabaseManager.java

```java
package it.leonardo.lez08_utenze;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String USER = "yourusername";
    private static final String PASSWORD = "yourpassword";
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertData(String firstName, String lastName) {
        String query = "INSERT INTO users (first_name, last_name) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getData() {
        String query = "SELECT * FROM users";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("User: " + rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void getDataName(String firstName) {
        String query = "SELECT * FROM users WHERE first_name = '" + firstName + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("User: " + rs.getString("first_name") + " " + rs.getString("last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
```

LoggerExample.java

```java
package it.leonardo.lez08_utenze;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerExample {
    private static final Logger logger = LogManager.getLogger(LoggerExample.class);

    public void logMessage(String message) {
        logger.info("Logging message: {}", message);
    }
}
```

pom.xml

```xml
...
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-core</artifactId>
        <version>2.14.1</version>
    </dependency>
    <dependency>
        <groupId>org.apache.logging.log4j</groupId>
        <artifactId>log4j-api</artifactId>
        <version>2.14.1</version>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>8.0.26</version>
    </dependency>
  </dependencies>
...
```

