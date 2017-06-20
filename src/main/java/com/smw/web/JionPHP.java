package com.smw.web;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.smw.common.util.HttpClientUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.common.util.StringUtil;
import com.smw.pojo.Sets;
import com.smw.service.SetService;

/**
 * 与PHP对接
 * 
 * @author suen
 * @date 2016年8月18日-下午3:41:59
 * @version jdk1.8
 */
public class JionPHP {
	private static Logger logger = LoggerFactory.getLogger(JionPHP.class);

	/**
	 * php接口访问地址
	 */
	private static String phpUrl = "";

	static {
		PropertiesUtil p = new PropertiesUtil("wsdl-config.properties");
		phpUrl = p.readProperty("phpUrl");
	}

	/**
	 * 根据商场编码获取最新CPOS的APK
	 * 
	 * @param mallId
	 *            商场编码
	 * @return
	 */
	public static Map<String, Object> getCPOSAPK(SetService setService, String saveApkPath, String mallId) {

		String result = null;
		try {
			// 参数
			Map<String, Object> map = new HashMap<>();
			map.put("action", "checkNewestVersion");
			map.put("data", "{\"mall_no\":\"" + com.gws.util.StringUtil.getNsyhInfo("gws.update.id") + "\"}");
			result = HttpClientUtil.httpPost(phpUrl, map);
			result=result.replace("\\","");
			result=	result.substring(result.indexOf("{"), result.length());
			logger.info("PHP返回的数据"+result);
		} catch (Exception e) {
			logger.error("从PHP获取最新版本APK信息失败！ " ,e);
			throw new RuntimeException("从PHP获取最新版本APK信息失败");
		}


		// 返回值
		Map<String, Object> retMap = new HashMap<>();
		Map<String, String> map = new HashMap<>();
		// 获取本地APK信息
		Sets sets = setService.getSets("apkInfo");
		JSONObject parseObject;
		// 处理php接口返回
		if (!StringUtil.isNUllStr(result) && "0".equals((parseObject = JSON.parseObject(result)).getString("errcode"))) {
			// 获取data数据
			parseObject = JSON.parseObject(parseObject.getString("data"));
			// 版本名称
			String ver_name = parseObject.getString("ver_name");

			// 版本号
			String ver_code = parseObject.getString("ver_code");
			// 更新时间
			String update_time = parseObject.getString("update_time");
			// 下载地址
			String update_pakage = parseObject.getString("update_pakage");
			if(StringUtils.isEmpty(ver_name)||StringUtils.isEmpty(ver_code)||StringUtils.isEmpty(update_time)||StringUtils.isEmpty(update_pakage))
				throw new RuntimeException("服务端返回数据异常");
			// apk信息
			String apkInfo = ver_name + "_" + ver_code + "_" + update_time;
			if (sets == null) { sets = new Sets(); sets.setId("apkInfo");}
			File file=null;
			Boolean exists=false;
			// 看云平台的apk信息是否与本地apk信息一致，一致则无需更新,并且看该APK文件是否存在
			if (apkInfo.equals(sets.getValue()) && (exists=(file=new File(saveApkPath + "resource/apk/" + sets.getValue() + ".apk")).exists())) {
				retMap.put("flag", true);
			}
			// 不一致更新本地APK
			else {
				// 下载文件
				FileOutputStream fos = null;
				BufferedInputStream bis = null;
				HttpURLConnection httpUrl = null;
				URL url = null;
				try {
					if (!StringUtil.isNUllStr(sets.getValue())) {
						// 删除旧版本
						if (exists) {
							file.delete();
						}
					}
					byte[] buf = new byte[1024];
					int size = 0;

					url = new URL(update_pakage);
					httpUrl = (HttpURLConnection) url.openConnection();
					httpUrl.connect();
					bis = new BufferedInputStream(httpUrl.getInputStream());
					//BUG修复
					File filePatht=new File(saveApkPath + "resource/apk"); if(!filePatht.exists()) filePatht.mkdirs();
					File f=new File(saveApkPath + "resource/apk/" + apkInfo + ".apk");
					if(!f.exists()) f.createNewFile();
					fos = new FileOutputStream(saveApkPath + "resource/apk/" + apkInfo + ".apk");
					while ((size = bis.read(buf)) != -1)
						fos.write(buf, 0, size);
					httpUrl.disconnect();
					sets.setValue(apkInfo);
					setService.saveOrUpdateSets(sets);
					retMap.put("flag", true);
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("从PHP获取最新版本APK然后下载保存失败！ " + e.getMessage());
					retMap.put("flag", false);
				} finally {
					try {
						if (fos != null) {
							fos.close();
						}
						if (bis != null) {
							bis.close();
						}
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			map.put("ver_name", ver_name);
			map.put("ver_code", ver_code);
			map.put("update_time", update_time);
			// 下载地址
			map.put("downloadUrl", "ShoppingMallWeb/resource/apk/" + apkInfo + ".apk");
		}
		// 直接返回CPOS
		else {
				retMap.put("flag", false);
		}
		retMap.put("data", map);
		return retMap;
	
	}

}
