import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateData {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();
            String query=String.format("UPDATE students SET marks = %f  WHERE id = %d",92.5,5);
            int rowsAffected=statement.executeUpdate(query);
            if(rowsAffected>0){
                System.out.println("Data Updated Successfully");
            } else {
                System.out.println("Data not inserted.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
