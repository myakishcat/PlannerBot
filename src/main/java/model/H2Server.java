package model;

import org.h2.tools.Server;
import java.sql.SQLException;

public class H2Server {
    public static void main() throws SQLException {
        Server server = Server.createTcpServer().start();
    }
}
