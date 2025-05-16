import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDataUsingPreparedStatement {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection= DriverManager.getConnection(url,username,password);
            String query="UPDATE students SET marks = ? WHERE id=?";

            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setDouble(1,79.5);
            preparedStatement.setInt(2,2);
            int rowsAffected=preparedStatement.executeUpdate();

            if(rowsAffected>0)
                System.out.println("Data Updated Successfully");
            else
                System.out.println("Data not updated,");

        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
