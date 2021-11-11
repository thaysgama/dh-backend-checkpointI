package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ConfigurationJDBC {
    private String driverClass;
    private String jdbcUrl;
    private String username;
    private String password;

    private static final String SQL_CREATE_TABLE_DENTIST = "CREATE TABLE IF NOT EXISTS dentists (" +
            "id INT AUTO_INCREMENT PRIMARY KEY," +
            "registration_number INT NOT NULL," +
            "name VARCHAR(100) NOT NULL," +
            "surname VARCHAR(100) NOT NULL);";

    public ConfigurationJDBC(String driverClass, String jdbcUrl, String username, String password) {
        this.driverClass = driverClass;
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    public ConfigurationJDBC() {
        this.driverClass = "org.h2.Driver";
        this.jdbcUrl = "jdbc:h2:~/test";
        this.username = "";
        this.password = "";

        createTableDentist(SQL_CREATE_TABLE_DENTIST);
    }

    public void createTableDentist(String query){
        try {
            Connection connection = connectDatabase();
            Statement statement = connection.createStatement();
            statement.execute(query);
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection connectDatabase(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcUrl, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
