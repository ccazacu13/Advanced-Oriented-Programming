package Utils;

import java.sql.*;

public class DbWrite {
    private static DbWrite instance;
    public Statement statement;

    private Connection connection;

    private DbWrite() {
        String url = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "c##proiectmds";
        String password = "password";

        Connection connection = null;
        try {
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static DbWrite getInstance() {
        if (instance == null) {
            instance = new DbWrite();
        }
        return instance;
    }

    // 1 Library
    // 2 Reader
    // 3 Book
    // 4 Librarian
    public void add_to_database(String table, String data) {
        String sqlQuery = "INSERT INTO " + table.toUpperCase() + " VALUES(" + data + ")";
        try {
            PreparedStatement ps = this.connection.prepareStatement(sqlQuery.strip());
            ps.executeUpdate();
            WriteToCSV.write("INSERT " + table);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update_database(String table, String data){
        String sqlQuery = "UPDATE " + table.toUpperCase() + " " + data;
        try {
            PreparedStatement ps = this.connection.prepareStatement(sqlQuery.strip());
            ps.executeUpdate();
            WriteToCSV.write("UPDATE " + table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void remove_from_database(String table, String data){
        String sqlQuery = "DELETE FROM " + table.toUpperCase() + " " + data;
        try {
            PreparedStatement ps = this.connection.prepareStatement(sqlQuery.strip());
            ps.executeUpdate();
            WriteToCSV.write("DELETE " + table);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
