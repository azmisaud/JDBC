import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Write a Java program to connect to a database using JDBC and display a success message.
public class Problem1 {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(url,username,password);
            if(connection!=null)
                System.out.println("Successfully connected to a database.");
            else
                System.out.println("Failed to connect to database.");

            assert connection != null;
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
