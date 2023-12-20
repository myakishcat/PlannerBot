package core;

public final class SqlQueries {
    private SqlQueries() {
    }
    //INSERT into CUSTOMER values (1, 'Ramesh', 32, 'Ahmedabad', 2000);
    //CREATE TABLE CUSTOME (id number, name varchar(20), age number, address varchar(20), salary number);
    public static final String createTableSQL = "CREATE TABLE if NOT EXISTS users (id number, name varchar(20))";
    public static final String insertSQL = "INSERT INTO users" +
            "  (id, name) VALUES " +
            " (?, ?);";
}
