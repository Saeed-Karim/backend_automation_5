package database;
import java.sql.*;
import java.util.Iterator;

public class DatabaseConnection {
    public static void main(String[] args) throws SQLException {


        String url = "jdbc:oracle:thin:@techglobal.cup7q3kvh5as.us-east-2.rds.amazonaws.com:1521/TGDEVQA";
        String username = "islomjon";
        String password = "$islomjon123!";
        String query ="select * from employees";


        Connection connection = DriverManager.getConnection(url, username,password);

        System.out.println("Database Connection is Successful");

        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery(query);

        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        System.out.println("Number of columns " + resultSetMetaData.getColumnCount());
        System.out.println("Name in the first column " + resultSetMetaData.getColumnName(1));
        int i = 1;
        while (resultSet.next()){
            System.out.println(i++ + "." + resultSet.getString("FIRST_NAME") + " "
                    + resultSet.getString("LAST_NAME"));
            //System.out.println(resultSet.getString("LAST_NAME"));
        }
    }
}
