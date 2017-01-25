package com.test.db.person;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;

import java.sql.*;
import java.util.Calendar;

/**
 * Created by ff on 2017/1/17.
 */
public class Workdao {
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

    /**
     * 新增一条记录
     * @param worker
     * @param boss
     */
    public static boolean addworker(String worker,String boss){
        boolean result = false;
        String sql = "insert into work(worker,boss,date) values(?,?,?)";
        PreparedStatement pstmt;
        Calendar now = Calendar.getInstance();
        String date = now.get(Calendar.YEAR)+""+(now.get(Calendar.MONTH)+1);

        try {
            pstmt=conn.prepareStatement(sql);
            pstmt.setString(1,worker);
            pstmt.setString(2,boss);
            pstmt.setString(3,date);
            result =pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
//            e.printStackTrace();
        }
        return result;
    }

    /**
     * 更新表中的数据
     * @param worker
     * @param workload
     * @return
     */
    public static boolean update(String worker,String boss,float workload){
        boolean result=true;
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_MONTH);

        String time = now.get(Calendar.YEAR)+""+(now.get(Calendar.MONTH)+1);
        String lastTime =queryLastTime(worker);
        //判断是否到了下一个月,如果是则新建一条记录
        if(Integer.parseInt(lastTime) < Integer.parseInt(time)){
            addworker(worker,boss);
        }

        String sql = "UPDATE work set day"+day+"=? WHERE worker=? AND date=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1,workload);
            pstmt.setString(2,worker);
            pstmt.setString(3,time);
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            result=false;
            e.printStackTrace();
        }

        //修改总天数
        Float sum = querySum(worker,time);
        sum = sum +workload;

        sql = "UPDATE work SET sum=?WHERE worker=? AND date=?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setFloat(1,sum);
            pstmt.setString(2,worker);
            pstmt.setString(3,time);
            pstmt.execute();
            pstmt.close();
        } catch (SQLException e) {
            result=false;
            e.printStackTrace();
        }


        return result;
    }


    public static JSONArray query(String wroker,String boss){

        String sql = "SELECT * from work where worker=? AND boss=?";
        JSONArray result;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,wroker);
            pstmt.setString(2,boss);
            ResultSet resultSet = pstmt.executeQuery();
            result =resultSetToJson(resultSet);
            pstmt.close();
        } catch (SQLException e) {
            result=null;
            e.printStackTrace();
        }
        return result;
    }

    private static String queryLastTime(String worker){

        String sql = "SELECT date from work where worker=? ORDER BY id DESC ";
        String result = null;

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,worker);
            ResultSet resultSet = pstmt.executeQuery();
            resultSet.next();
            result = resultSet.getString("date");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static JSONArray queryWorkersSum(){
        JSONArray result=null;
        String sql = "select worker,sum,workkind from work ORDER BY workkind DESC ,sum DESC ";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet set = pstmt.executeQuery();
            result = resultSetToJson(set);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    private static Float querySum(String name,String date){

        String sql = "select sum from work where worker=? AND date=?";
        Float sum= Float.valueOf(0);

        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,name);
            pstmt.setString(2,date);
            ResultSet set = pstmt.executeQuery();
            while (set.next()){
                sum=set.getFloat("sum");
            }
            pstmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sum;
    }


    public static  JSONArray resultSetToJson(ResultSet rs) throws SQLException
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
        return array;
    }

}
