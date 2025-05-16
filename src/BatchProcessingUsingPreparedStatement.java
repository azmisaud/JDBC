import java.sql.*;
import java.util.Scanner;

public class BatchProcessingUsingPreparedStatement {
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
            String query="INSERT INTO students(name,age,marks) VALUES(?,?,?)";
            PreparedStatement preparedStatement=connection.prepareStatement(query);
            Scanner scanner=new Scanner(System.in);

            while(true){
                System.out.print("Enter Name : ");
                String name=scanner.next();
                System.out.print("Enter Age : ");
                int age=scanner.nextInt();
                System.out.print("Enter marks : ");
                double marks=scanner.nextDouble();

                System.out.print("Enter more data (Y/N) : ");
                String choice=scanner.next();

                preparedStatement.setString(1,name);
                preparedStatement.setInt(2,age);
                preparedStatement.setDouble(3,marks);

                preparedStatement.addBatch();
                if(choice.equalsIgnoreCase("N"))
                    break;
            }

            int[] arr=preparedStatement.executeBatch();

            for(int i=0;i<arr.length;i++){
                if(arr[i]==0)
                    System.out.println("Query : " +i+ " not executed.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
