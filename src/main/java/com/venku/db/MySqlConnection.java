package com.venku.db;



import java.sql.*;
import java.util.logging.Logger;

public class MySqlConnection {

    Connection connection = null;
    PreparedStatement statement = null;

    Logger logger = Logger.getLogger(MySqlConnection.class.getName());

    public static void main(String[] args) {
        new MySqlConnection().getDataFromMySql();
    }

    private void getDataFromMySql() {
        makeJDBCConnection();
        //addDataToDB("venku", 40000, "Emp-001", "SoftwareEngineer");
       // getDataFromDB();
        getDataFromDBForEmployee("Emp-002");
    }

    private void getDataFromDBForEmployee(String empId) {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM employee e  where e.empId = " + "'" + empId + "'" ;

            statement = connection.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = statement.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                String empId1 = rs.getString("empId");
                String designation = rs.getString("designation");
                // Simply Print the results
                System.out.format("%s, %s, %s, %s\n", name, salary, empId1, designation);
            }
        } catch (

                SQLException e) {
            e.printStackTrace();
        }


    }

    private void addDataToDB(String name, int salary, String empId, String designation) {

        try {
            String insertQueryStatement = "INSERT  INTO  Employee  VALUES  (?,?,?,?)";

            statement = connection.prepareStatement(insertQueryStatement);
            statement.setString(1, name);
            statement.setInt(2, salary);
            statement.setString(3, empId);
            statement.setString(4, designation);
            statement.executeUpdate();
            logger.info(" added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getDataFromDB() {

        try {
            // MySQL Select Query Tutorial
            String getQueryStatement = "SELECT * FROM employee";

            statement = connection.prepareStatement(getQueryStatement);

            // Execute the Query, and get a java ResultSet
            ResultSet rs = statement.executeQuery();

            // Let's iterate through the java ResultSet
            while (rs.next()) {
                String name = rs.getString("name");
                int salary = rs.getInt("salary");
                String empId = rs.getString("empId");
                String designation = rs.getString("designation");
                // Simply Print the results
                System.out.format("%s, %s, %s, %s\n", name, salary, empId, designation);
            }
        } catch (

                SQLException e) {
            e.printStackTrace();
        }

    }

    private void makeJDBCConnection() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            logger.info("Congrats - Seems your MySQL JDBC Driver Registered!");
        } catch (ClassNotFoundException e) {
            logger.info("Sorry, couldn't found JDBC driver. Make sure you have added JDBC Maven Dependency Correctly");
            e.printStackTrace();
            return;
        }

        try {
            // DriverManager: The basic service for managing a set of JDBC drivers.
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/venku", "root", "BD@1283");
            if (connection != null) {
                logger.info("Connection Successful! Enjoy. Now it's time to push data");
            } else {
                logger.info("Failed to make connection!");
            }
        } catch (SQLException e) {
            logger.info("MySQL Connection Failed!");
            e.printStackTrace();
            return;
        }

    }


}
