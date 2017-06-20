package com.smw.utils;

import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * fun:获取资源文件配置信息工具类(PropertiesUtil)
 * 
 * @author yumaochun
 *
 */
public class PropertiesUtil {
	
	//日志
	private static Logger logger=Logger.getLogger(PropertiesUtil.class);
	
	//文件对象
	private static Properties load = new Properties();
	
	/**
	 * 初始化配置文件信息
	 * 
	 * @param url   文件地址
	 */
	public PropertiesUtil(String url){
		getPropertiesByUrl(url);
	}
	
	/**
	 * fun：根据属性配置文件名，获取配置文件对象
	 * 
	 * @param url
	 * @return
	 */
    public static Properties getPropertiesByUrl(String url){
    	Properties load = new Properties();
    	try {
			load.load(PropertiesUtil.class.getClassLoader().getResourceAsStream(url));
			logger.info("加载资源文件成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	return load;
    }
    
//   
//    
//	static{
//    	try {
//			load.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("base_info.properties"));
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	/**
	 * fun:获取资源文件
	 * 
	 * @return
	 */
	public static Properties getProperties(){
		return load;
	}
	/**
	 * fun:根据key,获取资源文件value值
	 * 
	 * @param key        配置文件属性名
	 * @return  value    返回：配置文件属性值
	 */
	public static String getValue(String key){
		String val="";
		try {
			val=load.getProperty(key);
		} catch (Exception e) {
			logger.info("获取资源文件配置键【"+key+"】异常！");
		}
		return val;
	}
	
	
	public static void main(String[] args) {
		//PropertiesUtil propertiesUtil=new PropertiesUtil("language_en_US.properties");
		String url=Thread.currentThread().getContextClassLoader().getResource("sso-config.properties").getPath();
	
		System.out.println("=="+url);
	}
}
