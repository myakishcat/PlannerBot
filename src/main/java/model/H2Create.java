package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class H2Create {

    //private static final String createTableSQL = "CREATE TABLE CUSTOMER (id number, name varchar(20), age number, address varchar(20), salary number);";
    public static void main(String str) throws SQLException {
        H2Create createTableExample = new H2Create();
        createTableExample.createTable(str);
    }

    public void createTable(String str) throws SQLException {

        //System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(str);

        } catch (SQLException e) {
            // print SQL exception information
            H2JDBCUtils.printSQLException(e);
        }
    }

}
