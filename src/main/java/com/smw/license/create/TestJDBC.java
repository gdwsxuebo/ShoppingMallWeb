package com.smw.license.create;
	import java.sql.Connection;  
	import java.sql.DriverManager;  
	import java.sql.PreparedStatement;  
	import java.sql.ResultSet;  
	import java.sql.SQLException;  
	import java.sql.Statement;   
	  
	public class TestJDBC {  
	    public static Connection getConnection(){  
	        String driver="com.mysql.jdbc.Driver";   //获取mysql数据库的驱动类  
	        String url="jdbc:mysql://192.168.1.69:3306/pos_2_0_gdws_misorv61"; //连接数据库（kucun是数据库名）  
	        String name="root";//连接mysql的用户名  
	        String pwd="admin@123";//连接mysql的密码  
	        try{  
	            Class.forName(driver);  
	            Connection conn=DriverManager.getConnection(url,name,pwd);//获取连接对象  
	            return conn;  
	        }catch(ClassNotFoundException e){  
	            e.printStackTrace();  
	            return null;  
	        }catch(SQLException e){  
	            e.printStackTrace();  
	            return null;  
	        }  
	    }  
	      
	    public static void closeAll(Connection conn,PreparedStatement ps,ResultSet rs){  
	        try{  
	            if(rs!=null){  
	                rs.close();  
	            }  
	        }catch(SQLException e){  
	            e.printStackTrace();  
	        }  
	        try{  
	            if(ps!=null){  
	                ps.close();  
	            }  
	        }catch(SQLException e){  
	            e.printStackTrace();  
	        }  
	        try{  
	            if(conn!=null){  
	                conn.close();  
	            }  
	        }catch(SQLException e){  
	            e.printStackTrace();      
	        }  
	    }  
	      
	    public static void main(String[] args) throws SQLException  
	    {  
	          
	        Connection cc=TestJDBC.getConnection();  
	          
	        if(!cc.isClosed())  
	  
	        System.out.println("Succeeded connecting to the Database!");  
	        Statement statement = cc.createStatement();  
	        String sql = "select * from xf_sets";  
	        ResultSet rs = statement.executeQuery(sql);  
	        while(rs.next()) {  
	            System.out.println(rs.getString("id")+"");  
	        }  
	  
	  
	    }  
	 

}
