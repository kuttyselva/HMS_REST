package global.coda.hms.config;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * The type Database connection.
 */
public class DatabaseConnection {
    /**
     * The constant LOCAL_MESSAGES_BUNDLE.
     */
    public static final ResourceBundle LOCAL_MESSAGES_BUNDLE = ResourceBundle.getBundle(DatabaseQueries.SQL_QUERIES,
            Locale.getDefault());
    private static Connection connection = null;

    /*
     * creates connection to sql db and returns connection object
     */

    /**
     * Createconnection connection.
     *
     * @return connection of db.
     * @throws SQLException the sql exception
     */
    public static Connection createconnection() throws  SQLException {
        if (connection == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");

                // create connection
                String connectionUrl = LOCAL_MESSAGES_BUNDLE.getString(DatabaseQueries.CONNECTION);
                connection = DriverManager.getConnection(connectionUrl);
                return connection;
            } catch (ClassNotFoundException | SQLException exception) {
                throw new SQLException(exception);
            }
        }
        return connection;
    }

    /**
     * closes connection.
     */
    public void CloseConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
