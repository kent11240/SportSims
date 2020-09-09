package uuu.sportsims.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import uuu.sportsims.domain.SportSimsException;

public class RDBConnection {

    private static final String driver;// = "com.mysql.jdbc.Driver";
    private static final String url;// = "jdbc:mysql://localhost:3306/sportsims?zeroDateTimeBehavior=convertToNull";
    private static final String userid;// = "root";
    private static final String password;// = "P@22w0rd";

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("uuu.sportsims.model.JDBCSetting");
        String drv = bundle.getString("jdbc.driver");
        if (drv != null) {
            driver = drv;
        } else {
            driver = "com.mysql.jdbc.Driver";
        }
        String ur = bundle.getString("jdbc.url");
        if (ur != null) {
            url = ur;
        } else {
            url = "jdbc:mysql://localhost:3306/sportsims?zeroDateTimeBehavior=convertToNull";
        }
        String uid = bundle.getString("jdbc.userid");
        if (uid != null) {
            userid = uid;
        } else {
            userid = "root";
        }
        String pwd = bundle.getString("jdbc.password");
        if (pwd != null) {
            password = pwd;
        } else {
            password = "P@22w0rd";
        }
    }

    public static Connection getConnection() throws SportSimsException {
        Connection c = null;
        try {
            //1. Load JDBC driver.
            Class.forName(driver);
            //2. 建立連線
            try {
                c = DriverManager.getConnection(url, userid, password);
                return c;
            } catch (SQLException ex) {
                Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "無法建立MySQL JDBC連線", ex);
                throw new SportSimsException("無法建立MySQL JDBC連線", ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RDBConnection.class.getName()).log(Level.SEVERE, "無法載入Driver: " + driver, ex);
            throw new SportSimsException("無法載入Driver: " + driver, ex);
        }
    }
}
