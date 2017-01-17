package com.test.db.person;

import com.mysql.jdbc.Connection;
import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.sql.*;
import java.util.Calendar;

/**
 * Created by ff on 2017/1/17.
 */
public class dao {
    private final static String driver = "com.mysql.jdbc.Driver";
    private final static String url = "jdbc:mysql://localhost:3306/account";
    private final static String username = "root";
    private final static String password = "123456";
    private  static Connection conn=null;
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

    public static void addworker(String worker,String boss){
        String sql = "insert into work(worker,boss) values(?,?)";
        PreparedStatement pstmt;

        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,worker);
            pstmt.setString(2,boss);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean update(String worker,float workload){
        boolean result=false;
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);
        int time = (int)System.currentTimeMillis()/1000;

        String sql = "update work set "+day+"=? where worker=?AND date=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1,workload);
            pstmt.setString(2,worker);
            pstmt.setInt(3,time);
            result=pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String query(String name){

        String sql = "SELECT * from work where worker=?";

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            ResultSet resultSet = pstmt.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    public String resultSetToJson(ResultSet rs) throws SQLException
    {
        // json数组
        JSONArray array = new JSONArray();

        // 获取列数
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // 遍历ResultSet中的每条数据
        while (rs.next()) {
            JSONObject jsonObj = new JSONObject();

            // 遍历每一列
            for (int i = 1; i <= columnCount; i++) {
                String columnName =metaData.getColumnLabel(i);
                String value = rs.getString(columnName);
                jsonObj.put(columnName, value);
            }
            array.add(jsonObj);
        }

        return array.toString();
    }


}
