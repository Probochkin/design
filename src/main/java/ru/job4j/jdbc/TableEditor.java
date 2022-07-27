package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private  static Connection connection;

    private  Properties properties;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception{
        Class.forName(properties.getProperty("hibernate.connection.driver_class"));
        String url = properties.getProperty("hibernate.connection.url");
        String login =  properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        connection = DriverManager.getConnection(url, login, password);

    }

    public void createTable(String tableName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "create table if not exists " + tableName + "(%s, %s);",
                    "id serial primary key",
                    "name text"
            );
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
        public void dropTable(String tableName) {
            try (Statement statement = connection.createStatement()) {
                String sql = String.format(
                        "drop table %s;", tableName
                );
                statement.execute(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE %s ADD %s  %s;", tableName, columnName, type
            );
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void dropColumn(String tableName, String columnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE  %s DROP  %s;",tableName, columnName
            );
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try (Statement statement = connection.createStatement()) {
            String sql = String.format(
                    "ALTER TABLE  %s  RENAME  %s  TO  %s ;", tableName ,columnName, newColumnName
            );
            statement.execute(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        try(InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("database.properties")) {

            properties.load(in);
        }
        try ( TableEditor tableEditor = new TableEditor(properties)) {
            tableEditor.createTable("demo_table");
            System.out.println(getTableScheme(connection, "demo_table"));
            tableEditor.addColumn("demo_table","newcolum","text");
            System.out.println(getTableScheme(connection, "demo_table"));
            tableEditor.renameColumn("demo_table","newcolum","newcolum1");
            System.out.println(getTableScheme(connection, "demo_table"));
            tableEditor.dropColumn("demo_table","newcolum1");
            System.out.println(getTableScheme(connection, "demo_table"));
            tableEditor.dropTable("demo_table");
        }
        }



}
