package model;

import core.SqlQueries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class H2Insert {
    public static void main(String[] Task) throws SQLException {
        H2Insert createTableExample = new H2Insert();
        createTableExample.insertRecord(Task);
    }
    public void insertRecord(String[] Task) throws SQLException {
        try (Connection connection = H2JDBCUtils.getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SqlQueries.insertSQL)) {
            preparedStatement.setInt(1, Integer.parseInt(Task[0]));
            preparedStatement.setString(2, Task[1]);

            System.out.println(preparedStatement);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            H2JDBCUtils.printSQLException(e);
        }

    }
}
