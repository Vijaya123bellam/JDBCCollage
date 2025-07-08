package db;
import java.sql.*;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/college_db";
        String user = "root";
        String password = "Vijaya@123";
        return DriverManager.getConnection(url, user, password);
    }
}
