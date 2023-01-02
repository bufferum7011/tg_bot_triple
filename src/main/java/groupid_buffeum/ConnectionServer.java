package groupid_buffeum;
import java.sql.*;

public class ConnectionServer {
        private static String
        //serverReg = "jdbc:dbtype://u1771357.plsk.regruhosting.ru",
        //userReg = "u1771357_solar",
        //passwordReg = "Vaq-vik-xbg-9kZ",

        serverMysql = "jdbc:mysql://localhost:3306/sls",
        userMysql = "bufferum",
        passwordMysql = "r541u";
    public static boolean getConnServer() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("====Start connection mysql...");
            DriverManager.getConnection(serverMysql, userMysql, passwordMysql);
            System.out.println("====Connected");
            return true;
        }
        catch(Exception e) { System.out.println("====Not connected"); return false; }
    }
}