import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//Using CallableStatement to Execute Stored Procedures
public class CallableStatementDemo {

    /*
     * Prerequisite:
     * The following stored procedure must exist in your database before running this program:
     *
     * DELIMITER //
     * CREATE PROCEDURE AddStudent(
     *     IN p_name VARCHAR(50),
     *     IN p_age INT
     * )
     * BEGIN
     *     INSERT INTO students(name, age) VALUES (p_name, p_age);
     * END //
     * DELIMITER ;
     */

    private static final String url = DBConfig.getUrl();
    private static final String username = DBConfig.getUsername();
    private static final String password = DBConfig.getPassword();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            CallableStatement callableStatement = connection.prepareCall("{ call AddStudent(?, ?) }");

            callableStatement.setString(1, "Saud");
            callableStatement.setInt(2, 22);

            callableStatement.execute();

            System.out.println("Student inserted successfully using stored procedure.");

            callableStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
