package v1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Embedded {

    public static void main(String[] args) {
        String jdbcURL = "jdbc:h2:~/test";
        String username = "sa";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);

            System.out.println("Connected to database");

            connection.close();
            System.out.println("Disconnected from database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
