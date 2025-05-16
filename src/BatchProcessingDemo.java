import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BatchProcessingDemo {
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
            Statement statement=connection.createStatement();
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

                String query=String.format("INSERT INTO students(name,age,marks) VALUES ('%s',%d,%f)",name,age,marks);
                statement.addBatch(query);

                if(choice.equalsIgnoreCase("N"))
                    break;
            }

            int[] arr=statement.executeBatch();

            for(int i=0;i<arr.length;i++){
                if(arr[i]==0)
                    System.out.println("Query Number : " +i+ " not executed.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
