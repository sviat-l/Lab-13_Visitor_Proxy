package task2_proxy;

import java.sql.*;

public class DBConnection {
    private static DBConnection dbconnection;
    private final Connection connection;

    private DBConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:db.sqlite3");
    }

    public static DBConnection getInstance() throws SQLException {
        if(dbconnection==null){
            dbconnection = new DBConnection();
        }
        return dbconnection;
    }

    public void dbPostQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        statement.close();
    }
    public String dbGetQuery(String query) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if(resultSet.next()){
            return resultSet.getString("data");
        }
        return null;
    }
}
