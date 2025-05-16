import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertData {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection=DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();
            String query=String.format("INSERT INTO students(name,age,marks) VALUES('%s',%d,%f)", "Ariz", 32, 74.9);
            int rowsAffected=statement.executeUpdate(query);
            if (rowsAffected>0){
                System.out.println("Data Inserted Successfully");
            } else {
                System.out.println("Data not inserted.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
