# TASK PRATICO

Creare un report di analisi statica per il seguente progetto MAVEN:

Comment.java

```java
package it.leonardo.commentapp;

public class Comment {
    private String author;
    private String text;

    public Comment(String author, String text) {
        this.author = author;
        this.text = text;
    }

    public String getAuthor() { return author; }
    public String getText() { return text; }
}
```

CommentService.java

```java
package it.leonardo.commentapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CommentService {

    public void saveComment(Comment comment) {
        String query = "INSERT INTO Comments (author, text) VALUES ('" + comment.getAuthor() + "', '" + comment.getText() + "')";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(query);
            System.out.println("Commento salvato.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void printAllComments() {
        String query = "SELECT * FROM Comments";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                System.out.println("Autore: " + rs.getString("author"));
                System.out.println("Commento: " + rs.getString("text"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

```

App.java

```java
package it.leonardo.commentapp;

public class App {
    public static void main(String[] args) {
        CommentService commentService = new CommentService();

        Comment comment = new Comment("Anonimo", "<script>alert('Ciao');</script>");
        commentService.saveComment(comment);

        commentService.printAllComments();
    }
}

```

# DOMANDA 1

**Che cos’è la sicurezza perimetrale** e quali sono i suoi limiti nel contesto moderno?

# DOMANDA 2

Quali sono le differenze tra **autenticazione, autorizzazione e accounting (AAA)**?
