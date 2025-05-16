import java.sql.*;
//Handling IN, OUT, and INOUT Parameters in Stored Procedures
public class StudentInOutDemo {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    /*
     * Prerequisite:
     * The following stored procedure must exist in your database before running this program:
     *
        DELIMITER //
        CREATE PROCEDURE UpdateStudentAge(
            IN p_id INT,
            INOUT p_age INT,
            OUT p_name VARCHAR(50)
        )
        BEGIN
            SELECT name, age INTO p_name, p_age FROM students WHERE id = p_id;

            SET p_age = p_age + 1;

            UPDATE students SET age = p_age WHERE id = p_id;
        END //

        DELIMITER ;
     */

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);
            CallableStatement callableStatement=connection.prepareCall("{ call UpdateStudentAge(?,?,?) }");
            callableStatement.setInt(1,1);
            callableStatement.setInt(2,22);
            callableStatement.registerOutParameter(2, Types.INTEGER);
            callableStatement.registerOutParameter(3,Types.VARCHAR);

            callableStatement.execute();

            int updatedAge = callableStatement.getInt(2);
            String studentName = callableStatement.getString(3);

            System.out.println("Student Name : " +studentName);
            System.out.println("Updated Age : " +updatedAge);

            callableStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
