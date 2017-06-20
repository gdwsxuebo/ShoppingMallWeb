package com.gws.service.impl;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.gws.util.commonUtil;
import com.smw.common.util.DateUtil;
import com.smw.common.util.HttpClientUtil;
import com.smw.pojo.Sets;
import com.smw.service.SetService;
import com.smw.web.JionPHP;
@Service
public class GwUpdatePosService {

	private static Logger logger = LoggerFactory.getLogger(JionPHP.class);
	@Resource SetService setService;
	
	public SetService getSetService() {
		return setService;
	}
	public void setSetService(SetService setService) {
		this.setService = setService;
	}
	public JSONObject getUpdateInfo(HttpServletRequest request){
		String basePath=request.getSession().getServletContext().getRealPath("/");
		JSONObject res=new JSONObject();
		String result = null;
		try {
			// 参数
			Map<String, Object> map = new HashMap<>();
			map.put("action", "checkNewestVersion");
			map.put("data", "{\"mall_no\":\"" + com.gws.util.StringUtil.getNsyhInfo("gws.update.id") + "\"}");
			result = HttpClientUtil.httpPost(com.gws.util.StringUtil.getNsyhInfo("phpUrl"), map);
			result=result.replace("\\","");
			result=	result.substring(result.indexOf("{"), result.length());
			logger.info("PHP返回的数据"+result);
		} catch (Exception e) {
			logger.error("从PHP获取最新的更新信息失败！ " ,e);
			throw new RuntimeException("从PHP获取最新的更新信息失败");
		}
		JSONObject json=JSONObject.parseObject(result);
		String errcode=json.getString("errcode");
		if(!"0".equals(errcode)) throw new RuntimeException("PHP 服务端返回值异常");
		JSONObject jsonData=json.getJSONObject("data");
		String ver_name=jsonData.getString("ver_name");
		String ver_code=jsonData.getString("ver_code");
		String update_time=jsonData.getString("update_time");
		String update_pakage=jsonData.getString("update_pakage");
		Sets cposUp=setService.getSets("cposUpInfo");
		if(cposUp==null){
			AsyncDownloadFile(basePath,update_pakage,ver_name,ver_code,update_time);
			logger.info("没有更新文件,下载中");
			throw new RuntimeException("没有更新文件,下载中");
		}
		String cposUpInfo=cposUp.getValue();
		JSONObject setJson=JSONObject.parseObject(cposUpInfo);
		String set_ver_name=setJson.getString("ver_name");
		String set_ver_code=setJson.getString("ver_code");
		String set_update_time=setJson.getString("update_time");
		Integer state=setJson.getInteger("state");
		if(set_ver_name.equals(ver_name)&&set_ver_code.equals(ver_code)){
			if(state==0){logger.info("文件下载中");throw new RuntimeException("文件下载中");}
			File f=new File(basePath +setJson.getString("downloadFile"));
			if(!f.exists()) {
				AsyncDownloadFile(basePath,update_pakage,ver_name,ver_code,update_time);
				logger.info("有坏人把文件删除了,重新服务端获取更新文件");
				throw new RuntimeException("有坏人把文件删除了,重新服务端获取更新文件");
			}
			String path = request.getContextPath();
			String urlBasePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path + "/";
			res.put("ver_name", set_ver_name);res.put("ver_code", set_ver_code);res.put("downloadUrl",urlBasePath+setJson.getString("downloadFile"));
			res.put("update_time", set_update_time);
			return res;
		}
		else{
			AsyncDownloadFile(basePath,update_pakage,ver_name,ver_code,update_time);
			logger.info("PHP服务端有新的版本，pos后台正在更新中");
			throw new RuntimeException("PHP服务端有新的版本，pos后台正在更新中");
		}


	}
	public void AsyncDownloadFile(final String basePath, final String update_pakage, final String ver_name, final String ver_code, final String update_time){
		commonUtil.executorService.submit(new Runnable() {
			@Override
			public void run() {
				SetService setService=getSetService();
				Sets set=new Sets();
				set.setId("cposUpInfo");
				JSONObject upInfoJson=new JSONObject();
				upInfoJson.put("ver_name", ver_name);
				upInfoJson.put("ver_code", ver_code);
				upInfoJson.put("ctime", DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
				upInfoJson.put("downloadUrl", update_pakage);
				upInfoJson.put("update_time", update_time);
				upInfoJson.put("state", 0);
				set.setValue(upInfoJson.toJSONString());
				setService.saveOrUpdateSets(set);
				logger.info("下载文件开始");
				// 下载文件
				FileOutputStream fos = null;
				BufferedInputStream bis = null;
				HttpURLConnection httpUrl = null;
				URL url = null;
				try {
					byte[] buf = new byte[1024];
					int size = 0;
					url = new URL(update_pakage);
					httpUrl = (HttpURLConnection) url.openConnection();
					httpUrl.connect();
					bis = new BufferedInputStream(httpUrl.getInputStream());
					//BUG修复
					File filePatht=new File(basePath + "resource/apk"); if(!filePatht.exists()) filePatht.mkdirs();
					String filecompletePath=basePath + "resource/apk/" + com.gws.util.StringUtil.getUrlFileName(update_pakage);
					File f=new File(filecompletePath);
					if(!f.exists()) f.createNewFile();
					fos = new FileOutputStream(filecompletePath);
					while ((size = bis.read(buf)) != -1)
						fos.write(buf, 0, size);
					httpUrl.disconnect();
					Sets cposUp=setService.getSets("cposUpInfo");
					String cposUpInfo=cposUp.getValue();
					JSONObject resJson=JSONObject.parseObject(cposUpInfo);
					resJson.put("downloadFile", "resource/apk/" + com.gws.util.StringUtil.getUrlFileName(update_pakage));
					resJson.put("state", 1);
					cposUp.setValue(resJson.toJSONString());
					setService.saveOrUpdateSets(cposUp);
					logger.info("下载文件结束");
				} catch (Exception e) {
					e.printStackTrace();
					logger.error("从PHP获取最新版本APK然后下载保存失败！ " ,e);
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
		});


	}
}
