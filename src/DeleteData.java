import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteData {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();
            String query="DELETE FROM STUDENTS WHERE id=5";
            int rowsAffected=statement.executeUpdate(query);

            if(rowsAffected>0){
                System.out.println("Data deleted successfully.");
            } else {
                System.out.println("Data not deleted.");
            }

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
