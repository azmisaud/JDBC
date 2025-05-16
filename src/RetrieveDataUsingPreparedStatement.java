import java.sql.*;

public class RetrieveDataUsingPreparedStatement {
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
            String query="SELECT marks FROM students WHERE id=?";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            preparedStatement.setInt(1,2);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                double marks=resultSet.getDouble("marks");
                System.out.println("Marks: " + marks);
            } else {
                System.out.println("Not retrieved successfully.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
