//Insert 10 employees into an employees table using batch execution.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Problem3 {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try {
            Object[][] employees=new Object[10][3];

            Scanner scanner=new Scanner(System.in);
            for(int i=0;i<10;i++){
                System.out.print("Enter name of employee " +(i+1) + " : ");
                String name=scanner.next();

                System.out.print("Enter position of employee " +(i+1) + " : ");
                String position=scanner.next();

                System.out.print("Enter salary of employee " +(i+1) + " : ");
                double salary=scanner.nextDouble();

                employees[i][0]=name;
                employees[i][1]=position;
                employees[i][2]=salary;
            }
            scanner.close();

            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection= DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);

            String query="INSERT INTO employees(name,position,salary) VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);

            for(Object[] employee : employees){
                preparedStatement.setString(1, (String) employee[0]);
                preparedStatement.setString(2, (String) employee[1]);
                preparedStatement.setDouble(3, (Double) employee[2]);
                preparedStatement.addBatch();
            }

            int[] rowsAffected=preparedStatement.executeBatch();
            connection.commit();

            for(int i=0;i<rowsAffected.length;i++){
                if(rowsAffected[i]==0)
                    System.out.println("Query " +i+ " not executed.");
            }

            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
