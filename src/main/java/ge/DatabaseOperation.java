package ge;

import java.sql.*;

public class DatabaseOperation {
//    jdbc:mysql://localhost:3306/serverTimezone=GMT

    public static Connection conn;
    public static ResultSet resultSet;
    public static String url = "jdbc:mysql://localhost:3306/gamerecode?useSSL=FALSE&serverTimezone=UTC";
    //    public static String url = "jdbc:derby: // localhost:1527/ underguidance; create = true";
    public static String username = "root";
    public static String password = "123456";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";
    public static String tableName = "gamerecode";

/*
    1. driver load
    2. connect to database
    3. get object
    4. run sql-code
    5. search result
    6. release statement before releasing connection
*/
/*
    1. insert
    2. delete
    3. modify
    4. search
*/

    public void setUrl(String url) {
        DatabaseOperation.url = url;
    }

    public void setUsername(String username) {
        DatabaseOperation.username = username;
    }

    public void setPassword(String password) {
        DatabaseOperation.password = password;
    }

    public void setDRIVER(String DRIVER) {
        DatabaseOperation.DRIVER = DRIVER;
    }

    public void setTableName(String tableName){
        DatabaseOperation.tableName = tableName;
    }

    public void establishMySQLConnection()
    {
        try{
//            Driver driver = new com.mysql.jdbc.Driver();
//            DriverManager.registerDriver(driver);
//            Class.forName(DRIVER);

            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn + " connected...");
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    // just suitable for this pro
    public void createTable(Statement statement, String operation)
    {
        try{
            statement.executeUpdate(operation);
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    // insert, delete, modify
    public void updateRecode(Statement statement, String insertSql){
        try {
            int count = statement.executeUpdate(insertSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // TODO search (just satisfied for this project
    public void searchRecode(ResultSet resultSet, Statement statement, String operation){
        resultSet = null;

        try {
            resultSet = statement.executeQuery(operation);

            while (resultSet.next()){
                String time = resultSet.getString("time");
                System.out.println("time: " + time + " ms");
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void getRecode(ResultSet resultSet, Statement statement, String operation){
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(operation);
        }catch (SQLException ex){
            System.out.println(ex);
        }
    }

    public void releaseStatementConncetion(Statement statement, ResultSet resultSet, Connection conn){
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void inputString(String s){
        //    1. driver load
        //    2. connect to database
        establishMySQLConnection();
        //    3. get object
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //    4.run sql-code
        createTable(statement,"Create Table If Not Exists " + tableName + "(time varchar(140));");

        updateRecode(statement,"insert into " + tableName + " (time) values(" + s + ")");

        searchRecode(resultSet, statement, "select time from " + tableName);

        //    6. close
        releaseStatementConncetion(statement, resultSet, conn);
    }

    public static void main(String[] args) {

    }
}
