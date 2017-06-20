package com.smw.common.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

@SuppressWarnings("deprecation")
public class HttpClientUtil {

	/**
	 * 通过url获取服务器接口中返回的数据，以post方式请求
	 * 
	 * @param url          url地址
	 * @param paramMap     传递的参数集合
	 * @return   String  返回返回接口返回的数据   
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static String httpPost(String url,Map<String,Object> paramMap) throws ClientProtocolException, IOException{
		@SuppressWarnings("resource")
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		HttpEntity entity;
		//以下是post方法
        HttpPost httpPost = new HttpPost(url);//一定要改成可以提交的地址,这里用百度代替
        List <NameValuePair> nvps = new ArrayList <NameValuePair>();
        
        if(paramMap!=null){
        	//循环遍历url请求参数，放入BasicNameValuePair中
        	@SuppressWarnings("rawtypes")
			Iterator iter = paramMap.entrySet().iterator();
        	while(iter.hasNext()){
        		Map.Entry entry = (Map.Entry) iter.next();
        		String key = (String) entry.getKey();
        		String val = (String) entry.getValue();
        		nvps.add(new BasicNameValuePair(key, val));
        	}
        }
        httpPost.setEntity((HttpEntity) new UrlEncodedFormEntity(nvps, Consts.UTF_8));
        response = httpclient.execute(httpPost);
        entity = response.getEntity();
        String body = EntityUtils.toString(entity);
        //System.out.println("http post methed: " + response.getStatusLine());//这个可以打印状态
        //中断请求,接下来可以开始另一段请求
        httpPost.abort();
        //释放请求.如果释放了相当于要清空session
        httpPost.releaseConnection();
		return body;
	}
	
	/**
	 * 以get 方式从服务器接口获取数据
	 * @param url              请求的url地址
	 * @param paramMap         需要传递的参数map集合
	 * @return   string        返回数据
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String httpGet(String url,Map<String,Object> paramMap) throws ClientProtocolException, IOException{
		@SuppressWarnings("resource")
		DefaultHttpClient httpclient = new DefaultHttpClient();
		//需要传递的参数
		String paramStrs="";
        if(paramMap!=null){
        	//循环遍历url请求参数，并拼装到字符串中
        	@SuppressWarnings("rawtypes")
			Iterator iter = paramMap.entrySet().iterator();
        	while(iter.hasNext()){
        		Map.Entry entry = (Map.Entry) iter.next();
        		String key = (String) entry.getKey();
        		String val = (String) entry.getValue();
        		paramStrs+=key+"="+val+"&";
        	}
        	if(paramStrs.length()>0){
        		paramStrs=paramStrs.substring(0, paramStrs.length()-1);
        	}
        	url+="?"+paramStrs;
        } 
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        String body = "";
        HttpResponse response;
        HttpEntity entity;
        response = httpclient.execute(httpGet);
        entity = response.getEntity();
        body = EntityUtils.toString(entity);//这个就是页面源码了
        httpGet.abort();//中断请求,接下来可以开始另一段请求
        System.out.println(body);
		return body;
	}
	
	public static void main(String[] args) throws ClientProtocolException, IOException {
		
		//String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
		//Map<String, Object> paramMap=new HashMap<>();
		
		//paramMap.put("access_token", "NK5qFVrxSzpQUxWki1JsNskheRvbOtxtHrvB14N8FZJQ90MT9LP1ErX6aLQXq4gfAtsqNb9l9yuxeE979K1dySOBEdUGfiyIW8XoebBM0BwCl1B_TY4CJIg_sSvyf1aRRMMaAJADYY");
		//paramMap.put("type", "jsapi");
		//paramMap.put("secret", "d40ad1e360f0fece7989eb66eb3d5019");
		//paramMap.put("password", "123456");
		//token=NK5qFVrxSzpQUxWki1JsNskheRvbOtxtHrvB14N8FZJQ90MT9LP1ErX6aLQXq4gfAtsqNb9l9yuxeE979K1dySOBEdUGfiyIW8XoebBM0BwCl1B_TY4CJIg_sSvyf1aRRMMaAJADYY
		//ticket=kgt8ON7yVITDhtdwci0qed39dYageuxGAe8EOCSByHEj0oje04GsMYreKQ4ev3Aj5aVxQONIHvTZFIPTswzTaQ
		//httpGet(url,paramMap);
		//getToken();
		String token="8V4DjvpRIRBR4n6ulP0Y9BqkX0O7iBHcbTt17aeIuIYjkOmbfM4YEehklcBhx-gkuE03dnoAGSWCfEFnJjTbgRtWwSDuXSYAQKqiiz-93BGg7El2ipmUfUF0DXp8h0w0CCPaABAIIV";
		String url="https://api.weixin.qq.com/cgi-bin/ticket/getticket";
	    Map<String, Object> paramMap=new HashMap<String, Object>();
				
	    paramMap.put("access_token",token);
		paramMap.put("type", "jsapi");
		httpGet(url,paramMap);
	}
	/**
	 * 获取token
	 * 
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String getToken() throws ClientProtocolException, IOException{
		//公众号
		String appid="wxab279224bb20b188";
		//认证钥匙
		String secret="d40ad1e360f0fece7989eb66eb3d5019";
		//获取token的地址
		String url="https://api.weixin.qq.com/cgi-bin/token";
        Map<String, Object> paramMap=new HashMap<String, Object>();
        paramMap.put("grant_type", "client_credential");
		paramMap.put("appid", appid);
		paramMap.put("secret", secret);
		String tokenInfo=httpGet(url,paramMap);
		//System.out.println(tokenInfo);
		//Map<String,String> map=JsonUtil.getMap(tokenInfo);
		//String token=map.get("access_token");
		return tokenInfo;
	}
}
