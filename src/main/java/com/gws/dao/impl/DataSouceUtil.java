package com.gws.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
/**
 * 
 * @author Administrator
 *数据源获取工具
 */
public class DataSouceUtil {  
    public static DataSource ds=null;
    private static DataSouceUtil instance = new DataSouceUtil();  
    private DataSouceUtil (){}  
    public static DataSouceUtil getInstance() {  
    return instance;  
    }  
	static{
		InputStream in=null;
		try {
			in=DataSouceUtil.class.getClassLoader().getResourceAsStream("v61jdbc.properties");
			Properties p=new Properties();
			p.load(in);
			ds=DruidDataSourceFactory.createDataSource(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(in!=null)
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	public Connection openConnection() throws SQLException{
		return ds.getConnection();
	}
	public DataSource getDataSoucre(){
		return ds;
	}

} 