package com.gws.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.net.URISyntaxException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gws.pojo.GwCrmVip;
import com.gws.pojo.GwIntegral;
import com.gws.pojo.GwMenu;
import com.gws.pojo.GwV61Sql;
import com.smw.common.util.PropertiesUtil;


/**
 * 常用的String转换方法。
 *
 */
public class StringUtil {
	private static final Map<String, Object>  map=new HashMap<>();
	static{
		PropertiesUtil p=new PropertiesUtil("systemConfig.properties");
		String bestpay_sleep=p.readProperty("bestpay.sleep");
		String gdws_crm_account=p.readProperty("gdws.crm.account");
		String gdws_mis_account	=p.readProperty("gdws.mis.account");
		String gdws_mis_password=p.readProperty("gdws.mis.password");
		String gdws_crm_password=p.readProperty("gdws.crm.password");
		String gdws_mallid_synch=p.readProperty("gdws.mallid.synch");
		String gws_update_id=p.readProperty("gws.update.id");
		String gdpsUrl=p.readProperty("gdpsUrl");
		String gdws_gdps_account=p.readProperty("gdws.gdps.account");
		String gdws_gdps_password=p.readProperty("gdws.gdps.password");
		String gdws_gdps_encoding=p.readProperty("gdws.gdps.encoding");
		
		
		map.put("gws.update.id", gws_update_id);
		map.put("bestpay.sleep", bestpay_sleep);
		map.put("gdws.crm.account", gdws_crm_account);
		map.put("gdws.mis.account", gdws_mis_account);
		map.put("gdws.mis.password", gdws_mis_password);
		map.put("gdws.crm.password", gdws_crm_password);
		map.put("gdws.mallid.synch", gdws_mallid_synch);
		
		map.put("gdpsUrl", gdpsUrl);
		map.put("gdws.gdps.account", gdws_gdps_account);
		map.put("gdws.gdps.password", gdws_gdps_password);
		map.put("gdws.gdps.encoding", gdws_gdps_encoding);
		
		
		PropertiesUtil wsdlp = new PropertiesUtil("wsdl-config.properties");
		String newCrmUrl=wsdlp.readProperty("newCrmUrl");
		String misUrl=wsdlp.readProperty("misUrl");
		String phpUrl=wsdlp.readProperty("phpUrl");
		String cancelAfterVerificationUrl=wsdlp.readProperty("CancelAfterVerificationUrl");
		map.put("phpUrl", phpUrl);
		map.put("newCrmUrl", newCrmUrl);
		map.put("misUrl", misUrl);
		map.put("cancelAfterVerificationUrl", cancelAfterVerificationUrl);
		
		PropertiesUtil v61Jdbc = new PropertiesUtil("v61jdbc.properties");
		String  synchTime=v61Jdbc.readProperty("synchTime");
		String maillid=v61Jdbc.readProperty("maillid");
		map.put("synchTime", synchTime);
		map.put("maillid", maillid);
	}

	/**
	 * 转换字符串中HTML/XML敏感的字符。
	 * 
	 * @param src
	 *            源字符串
	 * @return 转换后的字符串
	 */
	public static String XMLEscape(String src) {
		if (src == null)
			return null;
		String rtnVal = src.replaceAll("&", "&amp;");
		rtnVal = rtnVal.replaceAll("\"", "&quot;");
		rtnVal = rtnVal.replaceAll("<", "&lt;");
		rtnVal = rtnVal.replaceAll(">", "&gt;");
		rtnVal = rtnVal.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
		return rtnVal;
	}

	/**
	 * 获取query中的参数值
	 * 
	 * @param query 样例：a=1&b=2，注意：不带?符号
	 * @param param 参数名，如：a
	 * @return 参数值
	 */
	public static String getParameter(String query, String param) {
		Pattern p = Pattern.compile("&" + param + "=([^&]*)");
		Matcher m = p.matcher("&" + query);
		if (m.find())
			return m.group(1);
		return null;
	}

	/**
	 * 替换字符串中的指定字符，类似String.replaceAll的方法，但去除了正则表达式的应用
	 * 
	 * @param srcText  源字符串
	 * @param fromStr  需要替换的字符串
	 * @param toStr 替换为的字符串
	 * @return 替换后的字符串
	 */
	public static String replace(String srcText, String fromStr, String toStr) {
		if (srcText == null)
			return null;
		StringBuffer rtnVal = new StringBuffer();
		String rightText = srcText;
		for (int i = rightText.indexOf(fromStr); i > -1; i = rightText
				.indexOf(fromStr)) {
			rtnVal.append(rightText.substring(0, i));
			rtnVal.append(toStr);
			rightText = rightText.substring(i + fromStr.length());
		}
		rtnVal.append(rightText);
		return rtnVal.toString();
	}

	/**
	 * 连接字符串，常用与HQL语句的拼装，当左边值为空时返回右边值，当右边值为空时返回左边值，左右的值都不为空时返回左边值+连接串+右边值
	 * 
	 * @param leftStr 左边的值
	 * @param linkStr 连接字符串
	 * @param rightStr 右边的值
	 * @return 连接后的字符串
	 */
	public static String linkString(String leftStr, String linkStr, String rightStr) {
		if (isNull(leftStr)){
			return rightStr;
        }
		if (isNull(rightStr)){
			return leftStr;
        }
		return leftStr + linkStr + rightStr;
	}

	/**
	 * 判断一个字符串是否为null或空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null || str.trim().length() == 0;
	}

	/**
	 * 判断一个字符串是否为null或空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str) {
		return !isNull(str);
	}

	/**
	 * 检查给定的字符串既不是空，也不是长度为0。
	 * @param str
	 *            需检查的字符串(可以为<code>null</code>)
	 * @return 若字符串不为null或长度不为0，则返回<code>true</code>。
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 过滤字符串null
	 * 
	 * @param s  要过滤的字符串
	 * @return 过滤后的字符串
	 */
	public static String getString(String s) {
		return s == null ? "" : (s.equals("null") ? "" : s);
	}

	/**
	 * 将多个path字符串拼接起来，过滤多余的路径分隔符
	 * 
	 * @param paths 多个路径
	 * @return 拼接后的路径，最后是否带带路径分隔符由最后一个路径决定
	 */
	public static String linkPathString(String... paths) {
		if (null == paths || paths.length == 0)
			return "";

		StringBuilder sb = new StringBuilder();

		sb.append(paths[0]);

		for (int i = 1; i < paths.length; i++) {
			if (paths[i - 1].endsWith("\\") || paths[i - 1].endsWith("/")) {
				if (paths[i].startsWith("\\") || paths[i].startsWith("/")) {
					sb.append(paths[i].substring(1));
				} else {
					sb.append(paths[i]);
				}
			} else {
				if (paths[i].startsWith("\\") || paths[i].startsWith("/")) {
					sb.append(paths[i]);
				} else {
					sb.append("/").append(paths[i]);
				}
			}

		}
		return sb.toString();
	}

	/**
	 * 将字符串转换为数字，如果有错，采用缺省值
	 * @param value 字符串
	 * @param defaultValue 缺省值
	 * @return
	 */
	public static int getIntFromString(String value, int defaultValue) {
		int ret = defaultValue;
		if (StringUtil.isNotNull(value)) {
			try {
				ret = Integer.parseInt(value);
			} catch (NumberFormatException e) {
				ret = defaultValue;
			}
		}
		return ret;
	}

	/**
	 * 合并2个string[],并去掉重复项
	 * @param ary1
	 * @param ary2
	 * @return
	 */
	public static String[] mergeStringArray(String[] ary1, String[] ary2) {
		if (null == ary1)
			return ary2;
		if (null == ary2)
			return ary1;

		List<String> l1 = new ArrayList<String>(Arrays.asList(ary1));
		List<String> l2 = Arrays.asList(ary2);
		for (String s : l2) {
			if (!l1.contains(s)) {
				l1.add(s);
			}
		}
		String[] strings = new String[l1.size()];
		l1.toArray(strings);
		return strings;
	}

	// 空的数组返回null
	public static String[] emptyArray2Null(String[] ary1) {
		if (null == ary1 || ary1.length == 0) {
			return null;
		} else {
			return ary1;
		}
	}

    /**
     * 过滤脚本标签
     * @param html
     * @return
     */
	public static String clearScriptTag(String html) {
		// 过滤掉script标签
		Pattern scriptTag = Pattern
				.compile("<script[^>]*>.*(?=<\\/script>)<\\/script>");
		Matcher mTag = scriptTag.matcher(html);
		html = mTag.replaceAll("");

		// 过滤掉Dom节点事件
		String regx = "(<[^<]*)(on\\w*\\x20*=|javascript:)";
		Pattern pattern = Pattern.compile(regx, Pattern.CASE_INSENSITIVE
				+ Pattern.MULTILINE);// 不区分大小写
		Matcher matcher;
		String ts = html;
		// 此处需要循环匹配，防止恶意构造的字符串如 onclick=onclick=
		while ((matcher = pattern.matcher(ts)).find()) {
			ts = matcher.replaceAll("$1" + "_disibledevent=");
		}
		return ts;
	}
    /**
     * 根据多个字符条件来确认
     * @param str 字符串
     * @param exp 表达式 用“|”分隔
     * @return
     */
    public static int indexOf(String str,String exp){
        String[] exps=exp.split("\\|");
        int reValue=-1;
        for(String sign:exps){
            reValue=str.indexOf(sign);
            if(reValue>-1){
                return reValue;
            }
        }
        return reValue;
    }
	public static String createKey(){
		StringBuffer buff=new StringBuffer();
		String uuid=UUID.randomUUID().toString().replaceAll("-", "");
		char[] sitekeys=uuid.toCharArray(); 
		for(char s:sitekeys){
			if(s>64 && s<123){
				boolean b=new Random().nextBoolean();
				if(b) buff.append((char)(s-32));	
				else buff.append((char)s);
			}
			else buff.append((char)s);
		}
		return buff.toString();
	}
	public static String getNsyhInfo(String str){
		return String.valueOf(map.get(str));
	}
	public static String [] getNsyhIgnoreFiled(){
		String[]	ignoreFiled={"id","tillid","erro","trans_id","status","message","sign","ctime","serialVersionUID"};
		return ignoreFiled;
	};
	
	public static String [] getNsyhRefundIgnoreFiled(){
		String[]	ignoreFiled={"id","tillid","erro","trans_refund_id","status","message","sign","ctime","serialVersionUID"};
		return ignoreFiled;
	};
	
	public static JSONObject toFluxTree(String id,String text,List<Map<String, Object>> data){
		JSONObject json=new JSONObject();
		for(Map<String, Object> map:data){
			String textStr=(String) map.get(text);
			String idStr=(String) map.get(id);
			JSONObject jsonTwo=new JSONObject();
			jsonTwo.put("name", textStr);
			jsonTwo.put("type", "item");
			jsonTwo.put("additionalParameters", JSONObject.parse("{id:'"+idStr+"'}"));
			jsonTwo.put("id", idStr);
			json.put(textStr,jsonTwo);
		}
		return json;
	} 
	
	public static JSONObject toFluxTree2(Integer id,String text,List<Map<String, Object>> data){
		JSONObject json=new JSONObject();
		for(Map<String, Object> map:data){
			String textStr=(String) map.get(text);
			Integer idStr = (Integer) map.get(id);
			JSONObject jsonTwo=new JSONObject();
			jsonTwo.put("name", textStr);
			jsonTwo.put("type", "item");
			jsonTwo.put("additionalParameters", JSONObject.parse("{id:'"+idStr+"'}"));
			json.put(textStr,jsonTwo);
		}
		return json;
	} 
	
	public static String createUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}

	public static String createFilterUrl(String filters) {
		int i=0;StringBuffer buff=new StringBuffer();
		buff.append(" where ");
		//{"groupOp":"OR","rules":[{"field":"店铺ID","op":"eq","data":"2"},{"field":"店铺ID","op":"ne","data":"2"}]}
		JSONObject json=JSONObject.parseObject(filters);
		String or=json.getString("groupOp");
		JSONArray array=json.getJSONArray("rules");
		Iterator<Object> itr=array.iterator();
		while(itr.hasNext()){
			JSONObject rule=(JSONObject) itr.next();
			String field=rule.getString("field");
			//String op=rule.getString("op");
			String data=rule.getString("data");
			String re=field+" like "+"'%"+data+"%'";
			if(i==0) buff.append(" "+re+"");
			else buff.append(" "+or+" "+re+" ");
			i++;
		}
		return buff.toString();
	}

	public static GwV61Sql getV61Sql(){
		JAXBContext ctx;GwV61Sql stu=null;
		try {
			ctx = JAXBContext.newInstance(GwV61Sql.class);
		    Unmarshaller um = ctx.createUnmarshaller();
	        stu = (GwV61Sql) um.unmarshal(new StringReader(FileTOString("V61Sql.xml")));
	         return stu;
		} catch (JAXBException e) {
			throw new RuntimeException("解析V61SQL 文件失败 "+e.getMessage());
		}
		finally {
			
		}
	}
	
	public static GwMenu getMenu(){
		JAXBContext ctx;GwMenu stu=null;
		try {
			ctx = JAXBContext.newInstance(GwMenu.class);
			Unmarshaller um = ctx.createUnmarshaller();
			stu = (GwMenu) um.unmarshal(new StringReader(FileTOStringUTF8("menu.xml")));
			return stu;
		} catch (JAXBException e) {
			throw new RuntimeException("解析menu 文件失败 "+e.getMessage());
		}
		finally {
			
		}
	}
	
	public static	String FileTOString(String  path) {
        try {
        	        	
        	FileReader reader=new FileReader(new File(StringUtil.class.getResource("/").toURI())+"/"+path);
            @SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String content;
            while((content = bufferedReader.readLine() )!=null){
                stringBuilder.append(content);
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

    }
	
	
	public static	String FileTOStringUTF8(String  path) {
        try {
        	
        	File file = new File(new File(StringUtil.class.getResource("/").toURI())+"/"+path);       	
        	FileInputStream inputStream = new FileInputStream(file);
        	InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
            @SuppressWarnings("resource")
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder = new StringBuilder();
            String content;
            while((content = bufferedReader.readLine() )!=null){
                stringBuilder.append(content);
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (URISyntaxException e) {
			e.printStackTrace();
			return null;
		}

    }
	
	
	public static String formatData(String strData){
		String result=strData.substring(0,4)+"-"+strData.substring(4,6)+"-"+strData.substring(6,8);
		return result;
	}
	
	public static GwCrmVip CreateVipInfoPojo(JSONObject json){
		JSONObject status=json.getJSONObject("status");
		JSONObject data=json.getJSONObject("data");
		GwCrmVip gwCrmVip=new GwCrmVip();
		gwCrmVip.setERRCODE(status.getString("code"));
		gwCrmVip.setERRMSG(status.getString("msg"));
		gwCrmVip.setXf_vipcode(data.getString("vipCode"));
		gwCrmVip.setXf_surname(data.getString("userName"));
		gwCrmVip.setXf_givenname(data.getString("userName"));
		gwCrmVip.setXf_telephone(data.getString("phone"));
		if(0==data.getInteger("sex"))		gwCrmVip.setXf_sex("女");
		else gwCrmVip.setXf_sex("男");
		gwCrmVip.setXf_grade(data.getString("gradeName"));
		gwCrmVip.setXf_bonus(String.valueOf(data.getInteger("credit")));
	return gwCrmVip;
}
	  public static boolean isEmpty(Object obj) {
		    if (obj == null) {
		      return true;
		    }
		    if ((obj instanceof String)) {
		      if (("NULL".equals(obj.toString().trim().toUpperCase())) || 
		        ("".equals(obj.toString().trim()))) {
		        return true;
		      }
		      return false;
		    }
		    if ((obj instanceof Collection))
		      return ((Collection)obj).size() == 0;
		    if ((obj instanceof Map))
		      return ((Map)obj).size() == 0;
		    if (obj.getClass().isArray()) {
		      return Array.getLength(obj) == 0;
		    }
		    return false;
		  }
		public static boolean fileIsExists(String relPath,  String fileName){
			 boolean flag=false;
				Path path=Paths.get(relPath);
				 try(DirectoryStream<Path> stream = Files.newDirectoryStream(path)){
			            for(Path e : stream){
			            	if(e.toFile().isFile() && e.getFileName().toString().contains(fileName)) flag=true;
			            }
			        }catch(IOException e){
			           e.printStackTrace(); 
			        }
				 return flag;
			
		}
		public static String getFileEndName(String url){
			return url.substring(url.lastIndexOf("."), url.length());
		}
		public static String getUrlFileName(String url){
			return url.substring(url.lastIndexOf("/")+1, url.length());
		};
		
		public static Object createGwIntegral(JSONObject jsonData) {
			Integer theIntegral=jsonData.getInteger("theIntegral");
			Integer credit=jsonData.getInteger("credit");
			
			GwIntegral res=new GwIntegral().setBonusearn(String.valueOf(theIntegral))
					.setCurrentbonus(String.valueOf(credit)).setBonusredeem("0");
			
			return res;
		}

}
