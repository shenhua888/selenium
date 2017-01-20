package com.test.first_maven.base.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

import com.mysql.jdbc.Statement;



public class MySqlConnect {

	    private Statement statement;
	    private Connection connection;
	    private ResultSet result = null;
	    
	    public MySqlConnect() {
	        try
	        {
	            Class.forName(MySqlValues.DRIVER_MYSQL);     //加载JDBC驱动
	            System.out.println("Driver Load Success.");

	            connection = DriverManager.getConnection(MySqlValues.URL);    //创建数据库连接对象
	            statement = (Statement) connection.createStatement();       //创建Statement对象
	        } catch (Exception e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    public void closeConnect() {
	        try
	        {
	        	result.close();
	        	statement.close();
	        	connection.close();

	        } catch (SQLException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }
	    /*
	     * 根据sql查询数据库，返回一个结果集
	     * 输    入:SQL语句
	     * 返回值:ResultSet 查询结果
	     */
	    public ResultSet query(String sql) {
	        
	        try
	        {
	            result = statement.executeQuery(sql);
	        } catch (SQLException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }

	        return result;
	    }
	    
	    /*
	     * 获取查询结果，存入set
	     * 输    入:结果集(数据表)
	     * 返回值:list
	     */
	    public ArrayList getResultSet(ResultSet result) {
	    	ArrayList list = new ArrayList();
	        try
	        {
	            while(result.next()) {
	            	list.add(result.getString(1));
	            }
	        } catch (SQLException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return list;
	    }

	    /*
	     * 打印UserInfo表的数据
	     * 输    入:结果集(数据表)
	     * 返回值:空
	     */
	    public void printUserInfo(ResultSet result) {
	        try
	        {
	            while(result.next()) {
	                System.out.println("userNname:" + result.getString(1) 
	                        + ", password:" + result.getString(2));
	            }
	        } catch (SQLException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

	    
	    /*
	     * 执行数据操作
	     * 输    入:SQL语句
	     * 返回值:空
	     */
	    public void executeSql(String sql) {
	        try
	        {
	            statement.execute(sql);
	        } catch (SQLException e)
	        {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	    }

//	    public static void main(String[] args)
//	    {
//	        // TODO Auto-generated method stub
//
//	        String sql = "SELECT job_number FROM sys_user WHERE userid IN (SELECT ASSIGNEE_ FROM act_ru_task WHERE proc_inst_id_ IN (SELECT actinstid FROM bpm_pro_run_his WHERE STATUS = 1 AND PROCESSNAME NOT IN ('面试安排', '考试预约', '入职准备', '入职确认')))";
//	        MySqlConnect db = new MySqlConnect();
//	        ResultSet result = db.query(sql);
//	        ArrayList<String> list = db.getResultSet(result);
//	        for (String s : list) {
//	        	System.out.println(s);
//	        }
//	    }

	}