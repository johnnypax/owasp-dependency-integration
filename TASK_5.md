# TASK 5

Creare un report di analisi statica per il seguente progetto MAVEN:

Main.java

```java
package it.leonardo.lez09_porodotti;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	 LoggerExample loggerExample = new LoggerExample();
         loggerExample.logMessage("Hello, OWASP Dependency Checker with SLF4J and H2 Database!");

         DatabaseManager dbManager = new DatabaseManager();
         dbManager.connect();
         dbManager.createTable();
         dbManager.insertProduct("Laptop", 999.99);
         dbManager.getProducts();
         dbManager.getProductByNameVulnerable("Laptop");
         dbManager.closeConnection();
    }
}

```

DatabaseManager.java

```java
package it.leonardo.lez09_porodotti;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
	private static final String URL = "jdbc:10.1.1.1:3600/test";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection connection;

    public void connect() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Database connected!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable() {
        String query = "CREATE TABLE IF NOT EXISTS products (id INT AUTO_INCREMENT, name VARCHAR(255), price DOUBLE, PRIMARY KEY (id))";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertProduct(String name, double price) {
        String query = "INSERT INTO products (name, price) VALUES (?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProducts() {
        String query = "SELECT * FROM products";
        try (PreparedStatement pstmt = connection.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Product: " + rs.getString("name") + " - Price: " + rs.getDouble("price"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getProductByNameVulnerable(String name) {
        // Questa query è vulnerabile a SQL Injection
        String query = "SELECT * FROM products WHERE name = '" + name + "'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("Product: " + rs.getString("name") + " - Price: " + rs.getDouble("price"));
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
package it.leonardo.lez09_porodotti;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerExample {
	private static final Logger logger = LoggerFactory.getLogger(LoggerExample.class);

    public void logMessage(String message) {
        logger.info("Logging message: {}", message);
    }
}

```

Pom.xml

```java

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>
    

        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-classic</artifactId>
            <version>1.2.3</version>
        </dependency>
        <dependency>
            <groupId>ch.qos.logback</groupId>
            <artifactId>logback-core</artifactId>
            <version>1.2.3</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>1.7.30</version>
        </dependency>

        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.200</version>
        </dependency>
  </dependencies>
```

