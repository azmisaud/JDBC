import java.sql.*;

//Display column names and types of the students table using ResultSetMetaData.
public class Problem5 {
    private static final String url=DBConfig.getUrl();
    private static final String username=DBConfig.getUsername();
    private static final String password=DBConfig.getPassword();

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection= DriverManager.getConnection(url,username,password);
            Statement statement=connection.createStatement();

            String query = "SELECT * FROM player LIMIT 1";
            ResultSet resultSet=statement.executeQuery(query);

            ResultSetMetaData metaData= resultSet.getMetaData();
            int columnCount=metaData.getColumnCount();

            System.out.println("Table Metadata for 'player' : ");
            for(int i=1;i<=columnCount;i++){
                String columnName=metaData.getColumnName(i);
                String columnType=metaData.getColumnTypeName(i);
                int columnSize=metaData.getColumnDisplaySize(i);

                System.out.println("Column " +i+ " : " +columnName +
                        " | Type : " +columnType +
                        " | Size : " +columnSize);
            }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
