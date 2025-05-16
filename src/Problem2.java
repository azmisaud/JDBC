import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Write a program that creates a students table with id, name, and age.
public class Problem2 {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url,username,password);

            String query = """
                    CREATE TABLE IF NOT EXISTS details(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(250) NOT NULL,
                        age INT NOT NULL
                    );
                    """;

            Statement statement=connection.createStatement();
            statement.executeUpdate(query);

            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
