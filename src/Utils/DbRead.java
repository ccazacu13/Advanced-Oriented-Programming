package Utils;

import oracle.jdbc.proxy.annotation.Pre;

import java.sql.*;

public class DbRead {
    private static DbRead instance;

    private Connection connection;


    private DbRead() {
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

    public static DbRead getInstance() {
        if (instance == null) {
            instance = new DbRead();
        }
        return instance;
    }

    // 1 Library
    // 2 Reader
    // 3 Book
    // 4 Librarian

    public int read_from_database(String table, Boolean print, String data)
    {
        String sqlQuery;
        if(data.equals(""))
            sqlQuery = "SELECT * FROM " + table;
        else
            sqlQuery = "SELECT * FROM " + table + " " + data;
        int status = -1;
        try{
            PreparedStatement ps = connection.prepareStatement(sqlQuery.strip());
            ResultSet rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            int counter = 0;
            while (rs.next()) {
                status = 1;
                counter++;
                if(print)
                    {
                    System.out.println();
                    System.out.printf("%d%c", counter, '.');
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        Object columnValue = rs.getObject(i);

                        // Print the data in a formatted manner
                        System.out.printf(columnName + ": " + columnValue + "\n");
                    }

                }
            }
            if (status == -1)
                status = 0;
            if (status == 1)
                status = counter;
            WriteToCSV.write("SELECT " + table);

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return status;
    }

}

