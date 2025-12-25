package repository;

import exception.DBException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() {
        try {
            Context context = new InitialContext();
            Context tomcatContext = (Context) context.lookup("java:comp/env");
            DataSource ds = (DataSource) tomcatContext.lookup("jdbc/postgres");
            return ds.getConnection();
        } catch (SQLException | NamingException e) {
            try {
                throw new DBException(e);
            } catch (DBException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

