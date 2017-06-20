package com.smw.utils;

import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.smw.common.util.CollectionUtil;
import com.smw.common.util.DateUtil;

import gdws.license.create.CreateLicense;
import gdws.license.verify.VerifyGdwsLicense;

/**
 * LicenseUtil:license认证基本工具类
 * @author yumaochun
 * @date  2016年6月15日
 * @version  jdk1.8
 */
public class LicenseUtil {
    //日志
   private static Logger logger=LoggerFactory.getLogger(LicenseUtil.class);
   //资源文件信息
   private static Properties load=null;
   //过期日期
   private static String expireDate;
   
   /**
    * getConfigInfo:获取license资源文件信息
    * @author yumaochun
    * @date 2016年6月15日
    */
   public static Properties getConfigInfo(){
       if(load==null){
           //加载license配置文件
           load=PropertiesUtil.getPropertiesByUrl("licenseConfig.properties");
           if(CollectionUtil.isEmpty(load)){
               logger.info("license配置文件不存在！");
           }
       }
       return load;
   }

    /**
     * createLicense:生成license文件
     * @author yumaochun
     * @date 2016年6月15日
     */
    public static void createLicense(){
        //创建license实体类
        CreateLicense cLicense = new CreateLicense();
        //获取license文件信息
        Properties load=getConfigInfo();
        if(CollectionUtil.isEmpty(load)){
            logger.info("license配置文件不存在！");
            return;
        }
        //获取创建license参数文件地址
        String path=load.getProperty("create_license_param_path");
        cLicense.setParam(path);
        try {
          //生成license文件
          cLicense.create();
          logger.info("生成license文件成功！");
        } catch (Exception e) {
            logger.info("生成license文件失败！");
            e.printStackTrace();
        }
        
    }
    /**
     * verifyLicense:验证license是否有效
     * @author yumaochun
     * @date 2016年6月15日
     * @return  返回：true-有效，false-失效
     */
    @SuppressWarnings("static-access")
    public static boolean verifyLicense(){
        boolean flag=false;
        //创建license验证实体类
        VerifyGdwsLicense vLicense = new VerifyGdwsLicense();
        //获取license文件信息
        Properties load=getConfigInfo();
        if(CollectionUtil.isEmpty(load)){
            logger.info("license配置文件不存在！");
            return false;
        }
        //获取创建license参数文件地址
        String license_path=load.getProperty("license_path");
        try{
            String licPath=Thread.currentThread().getContextClassLoader().getResource("license.lic").getPath();
            vLicense.setParam(license_path,licPath);
            //验证
            flag=vLicense.verify();
            //获取过期日期
            Date date=vLicense.getExpireDate();
            expireDate=DateUtil.getDateStr(date, "-");
            logger.info("license验证成功！");
        }
        catch(Exception er){
            logger.info("license验证失败！");
            er.printStackTrace();
        }
        return flag;
    }
    /**
     * 
     * getExpireDate:获取过期日期
     * @author yumaochun
     * @date 2016年7月29日
     * @return  返回：过期的日期
     */
    public static String getExpireDate(){
        return expireDate;
    }
    
    public static void main(String[] args) {
        verifyLicense();
        System.out.println("过期时间："+getExpireDate());
//        createLicense();
    }
    
   
}
