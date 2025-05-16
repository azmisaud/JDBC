//Let user search player by name using wildcard characters (LIKE).
//
//
//        First let user enter some entries.
import java.sql.*;
import java.util.Scanner;

public class Problem4 {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();

            String createTable = """
                     CREATE TABLE IF NOT EXISTS player(
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(250) NOT NULL,
                        age INT NOT NULL
                    );
               """;

            statement.executeUpdate(createTable);
            statement.close();

            Scanner scanner=new Scanner(System.in);
            System.out.println("How many students do you want to insert?");
            int count=scanner.nextInt();

            String insertPlayer = "INSERT INTO player(name,age) VALUES (?, ?)";
            PreparedStatement preparedStatement=connection.prepareStatement(insertPlayer);

            for(int i=0;i<count;i++){
                System.out.print("Enter name of player " +(i+1) + " : ");
                String name=scanner.next();

                System.out.println("Enter age of player " +(i+1) + " : ");
                int age=scanner.nextInt();

                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.addBatch();
            }

            int[] rowsAffected=preparedStatement.executeBatch();
            for(int i=0;i<rowsAffected.length;i++){
                if(rowsAffected[i]==0)
                    System.out.println("Query " +(i+1)+ " not executed.");
            }
            preparedStatement.close();

            System.out.println("Enter search keyword (use % as wildcard) : ");
            String search=scanner.next();

            scanner.close();

            String searchPlayer = "SELECT * FROM player WHERE name LIKE ?";
            PreparedStatement preparedStatement1= connection.prepareStatement(searchPlayer);
            preparedStatement1.setString(1,search);

            ResultSet resultSet=preparedStatement1.executeQuery();
            System.out.println("\n Matching Players : ");
            while (resultSet.next())
                System.out.println(resultSet.getInt("id") +
                        " | " + resultSet.getString("name") +
                        " | " + resultSet.getInt("age"));

            preparedStatement1.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
