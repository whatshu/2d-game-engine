import java.sql.*;
import java.util.logging.Logger;

public class dataBaseTry {
//    jdbc:mysql://localhost:3306/serverTimezone=GMT

    public static Connection conn;
    public static ResultSet resultSet;
    public static String url = "jdbc:mysql://localhost:3306/underguidance?useSSL=FALSE&serverTimezone=UTC";
    public static String username = "root";
    public static String password = "123456";
    public static String DRIVER = "com.mysql.cj.jdbc.Driver";

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

    public static void setUrl(String url) {
        dataBaseTry.url = url;
    }

    public static void setUsername(String username) {
        dataBaseTry.username = username;
    }

    public static void setPassword(String password) {
        dataBaseTry.password = password;
    }

    public static void setDRIVER(String DRIVER) {
        dataBaseTry.DRIVER = DRIVER;
    }

    public static void establishMySQLConnection()
    {
        try{
//            Driver driver = new com.mysql.jdbc.Driver();
//            DriverManager.registerDriver(driver);
            Class.forName(DRIVER);

            conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn + " connected...");
        }
        catch (SQLException | ClassNotFoundException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    // just suitable for this pro
    public static void createTable(Statement statement, String operation)
    {
        try{
            statement.executeUpdate(operation);
        }
        catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    // insert, delete, modify
    public static void updateRecode(Statement statement, String insertSql){
        try {
            int count = statement.executeUpdate(insertSql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // TODO search (just satisfied for this project
    public static void searchRecode(ResultSet resultSet, Statement statement, String operation){
        resultSet = null;

        try {
            resultSet = statement.executeQuery(operation);

            while (resultSet.next()){
                String user = resultSet.getString("user");
                Integer time = resultSet.getInt("time");
                System.out.println("user: " + user + " time: " + time);
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

    public static void releaseStatementConncetion(Statement statement, ResultSet resultSet, Connection conn){
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

    public static void main(String[] args) {
        String tableName = "new_table";

        //1. 2.
        establishMySQLConnection();
        //3.
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        //4.
        createTable(statement,"Create Table If Not Exists Record(user varchar(50), time int);");

        updateRecode(statement,"insert into record(user, time) values(\"user1\", 10088)");

        searchRecode(resultSet, statement, "select user, time from record");

        //6. close
        releaseStatementConncetion(statement, resultSet, conn);
    }
}
