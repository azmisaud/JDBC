import java.sql.*;
import java.util.Scanner;

public class TransactionHandlingDemo {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    static boolean isSufficient(Connection connection, int account_number, double amount){
        try {
            String query = "SELECT balance FROM accounts WHERE account_number = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,account_number);

            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                double current_balance=resultSet.getDouble("balance");
                return !(amount > current_balance);
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try {
            Connection connection= DriverManager.getConnection(url,username,password);
            connection.setAutoCommit(false);
            String debitQuery="UPDATE accounts SET balance = balance - ? WHERE account_number = ?";
            String creditQuery="UPDATE accounts SET balance = balance + ? WHERE account_number = ?";
            PreparedStatement debitPreparedStatement=connection.prepareStatement(debitQuery);
            PreparedStatement creditPreparedStatement= connection.prepareStatement(creditQuery);

            Scanner scanner=new Scanner(System.in);
            System.out.print("Enter your account number : ");
            int debitAccount=scanner.nextInt();
            System.out.print("Enter the account number for credit : ");
            int creditAccount=scanner.nextInt();
            System.out.print("Enter the amount : ");
            double amount=scanner.nextDouble();

            debitPreparedStatement.setDouble(1,amount);
            debitPreparedStatement.setInt(2,debitAccount);

            creditPreparedStatement.setDouble(1,amount);
            creditPreparedStatement.setInt(2,creditAccount);

            debitPreparedStatement.executeUpdate();
            creditPreparedStatement.executeUpdate();
            if(isSufficient(connection,debitAccount,amount)){
                connection.commit();
                System.out.println("Transaction Successful.");
            } else {
                connection.rollback();
                System.out.println("Transaction Failed.");
            }
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
