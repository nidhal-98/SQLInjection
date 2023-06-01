package org.example;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("userName:");
        String userInput = sc.nextLine();

        String url = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + "test" + ";" + "encrypt=true;"
                + "trustServerCertificate=true";

        String user = "sa";
        String pass = "root";

        Connection con = null;
        try {

            Driver driver = (Driver) Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
            DriverManager.registerDriver(driver);

            con = DriverManager.getConnection(url, user, pass);

            String sql = "SELECT * FROM users WHERE username = '" + userInput + "'";

            PreparedStatement statement = con.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                System.out.println("ID: " + id);
                System.out.println("Username: " + username);
                System.out.println("Password: " + password);
            }

            statement.close();

            con.close();
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}