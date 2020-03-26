package service;

import java.util.Vector;

import java.sql.*;

import entity.*;
public class JDBC 
{
	public static Vector<Commodity> sqlCommodity(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        Vector<Commodity> result = new Vector<>();
        try{
        	
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
            	Commodity t = new Commodity();
            	t.setCommodityId(Integer.parseInt(rs.getString("commodity_id")));
            	t.setCommodityName(rs.getString("commodity_name"));
            	t.setPrice(Double.parseDouble(rs.getString("commodity_price")));
            	t.setDate(rs.getString("saler_date"));
            	t.setLocation(rs.getString("saler_location"));
            	t.setSalerName(rs.getString("saler_username"));
            	t.setImgsrc(rs.getString("image_src"));
            	t.setCommodityClass(rs.getString("class"));
            	t.setIntroducation(rs.getString("commodity_introducation"));
            	t.setIsSaled(Integer.parseInt(rs.getString("issaled")));
            	t.setSchool(rs.getString("commodity_school"));
            	t.setDetail(rs.getString("detail"));
            	result.add(t);
            }
            return result;
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("数据库查询为空");
        return null;
	}
	
	public static int sqlCredit(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        Vector<Commodity> result = new Vector<>();
        try{
        	
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return Integer.parseInt(rs.getString("credit"));
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("数据库查询为空");
        return 0;
	}
	
	public static int sqlNum(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        Vector<Commodity> result = new Vector<>();
        try{
        	
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            return Integer.parseInt(rs.getString("id"));
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("数据库查询为空");
        return 0;
	}
	
	public static Vector<GeZi> sqlGeZi(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        Vector<GeZi> result = new Vector<>();
        try{
        	
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
            	GeZi t = new GeZi();
            	t.setId(Integer.parseInt(rs.getString("id")));
            	t.setLocation(rs.getString("location"));
            	t.setUsername(rs.getString("username"));
            	result.add(t);
            }
            return result;
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("数据库查询为空");
        return null;
	}
	
	public static User sqlUser(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            User user = new User();
            if(rs.next()==false)
            {
            	return null;
            }
            else
            {
            	//user.setAuthword(authword);
            	user.setCredit(Integer.parseInt(rs.getString("credit")));
            	user.setGrade(rs.getString("grade"));
            	user.setPassword(rs.getString("password"));
            	user.setPrivilege(rs.getString("privilege"));
            	user.setSchool(rs.getString("school"));
            	user.setSex(rs.getString("sex"));
            	user.setUsername(rs.getString("username"));
            	user.setIp(rs.getString("usual_ip"));
            }
            return user;
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("数据库查询为空");
        return null;
	}
	
	public static Vector<User> sqlUsers(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Vector<User> v = new Vector();
            while(rs.next())
            {
            	User user = new User();
            	//user.setAuthword(authword);
            	user.setCredit(Integer.parseInt(rs.getString("credit")));
            	user.setGrade(rs.getString("grade"));
            	user.setPassword(rs.getString("password"));
            	user.setPrivilege(rs.getString("privilege"));
            	user.setSchool(rs.getString("school"));
            	user.setSex(rs.getString("sex"));
            	user.setUsername(rs.getString("username"));
            	user.setIp(rs.getString("usual_ip"));
            	v.add(user);
            }
            return v;
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("数据库查询为空");
        return null;
	}
	
	public static Vector<Ip> sqlIp(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            Vector<Ip> v = new Vector();
            while(rs.next())
            {
            	Ip ip = new Ip();
            	ip.setIpAddr(rs.getString("ip"));
            	ip.setStatus(rs.getString("status"));
            	ip.setLasttime(rs.getString("lasttime"));
            	ip.setSum(Integer.parseInt(rs.getString("sum")));
            	v.add(ip);
            }
            return v;
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("数据库查询为空");
        return null;
	}
	
	public static void sql(String query)
	{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
        final String DB_URL = "jdbc:mysql://localhost:3306/master_database?useUnicode=true&characterEncoding=utf-8";
        final String USER = "root";
        final String PASS = "LoganloveWilla375499877$";
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = (Statement) conn.createStatement();
            stmt.executeUpdate(query);
            // 完成后关闭
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        
	}
}
