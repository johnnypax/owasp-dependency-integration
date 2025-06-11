# TASK 3 - Progetto Albergo	

Creare un report di analisi statica per il seguente progetto MAVEN:

App.java

```java
package it.leonardo.lez07_prenotazioni;

public class App 
{
	public static void main(String[] args) {
        BookingService bookingService = new BookingService();

        Booking booking = new Booking("John Doe", "Suite', '', ''); DROP TABLE Booking;--", "2024-07-01", "2024-07-10");

        bookingService.addBooking(booking);

        bookingService.showBooking("<script>alert('Ciao Giovanni');</script>");
    }
}

```

Booking.java

```java
package it.leonardo.lez07_prenotazioni;

public class Booking {
	private String customerName;
    private String roomType;
    private String checkInDate;
    private String checkOutDate;

    public Booking(String customerName, String roomType, String checkInDate, String checkOutDate) {
        this.customerName = customerName;
        this.roomType = roomType;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getCheckInDate() {
		return checkInDate;
	}

	public void setCheckInDate(String checkInDate) {
		this.checkInDate = checkInDate;
	}

	public String getCheckOutDate() {
		return checkOutDate;
	}

	public void setCheckOutDate(String checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
    
    
}

```

BookingService.java

```java
package it.leonardo.lez07_prenotazioni;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookingService {

    public void addBooking(Booking booking) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String query = "INSERT INTO Booking(customer_name, room_type, check_in_date, check_out_date) VALUES ('"
                    + booking.getCustomerName() + "', '" + booking.getRoomType() + "', '"
                    + booking.getCheckInDate() + "', '" + booking.getCheckOutDate() + "')";

            PreparedStatement ps = conn.prepareStatement(query);

            int affRows = ps.executeUpdate();

            if (affRows > 0)
                System.out.println("Prenotazione riuscita");
            else
                System.out.println("Prenotazione non riuscita");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showBooking(String customerName) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String query = "SELECT * FROM Booking WHERE customer_name = '" + customerName + "'";

            PreparedStatement ps = conn.prepareStatement(query);

            // Esecuzione e gestione della query omessa per brevità

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

DatabaseConnection.java

```java
package it.leonardo.lez07_prenotazioni;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_booking_system";
    private static final String USER = "root";
    private static final String PASS = "admin123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
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

    <!-- https://mvnrepository.com/artifact/com.mysql/mysql-connector-j -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
...
```

