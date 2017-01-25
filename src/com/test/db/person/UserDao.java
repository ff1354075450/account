package com.test.db.person;

import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by ff on 2017/1/18.
 */
public class UserDao {

    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/account";
    private final static String username = "root";
    private final static String password = "123456";
    private static Connection conn = null;

    static {
        try {
            Class.forName(driver);
            conn = (Connection) DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static boolean addUser(String name,String password){

        String sql = "INSERT INTO user(name,password) VALUE (?,?)";
        boolean result = true;
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }

    
    public static boolean checkPassword(String name,String password){

        String sql = "SELECT password from user where name=?";
        boolean result = false;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String psw = rs.getString("password");
                if(psw.equals(password)){
                    result=true;
                    break;
                }
            }
            ps.close();
        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return  result;
    }
}
