package com.smw.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.xml.sax.InputSource;

import com.alibaba.fastjson.JSONObject;
import com.smw.common.util.PropertiesUtil;

public class ParseXmlUtil {
	/**
	 * 转换一个xml格式的字符串到json格式
	 * 
	 * @param xml
	 *            xml格式的字符串
	 * @return 成功返回json 格式的字符串;失败反回null
	 */
	@SuppressWarnings("unchecked")
	public static Map<String,String> xml2Map(String xml) {
		try {
			InputStream is = new ByteArrayInputStream(xml.getBytes("utf-8"));
			SAXBuilder sb = new SAXBuilder();
			Document doc = sb.build(is);
			Element root = doc.getRootElement();
			Map<String,String> map = new HashMap<String,String>();
			Map<String,String> result = iterateElement(root,map);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 一个迭代方法
	 * 
	 * @param element
	 *            : org.jdom.Element
	 * @return java.util.Map 实例
	 */
	@SuppressWarnings("unchecked")
	private static Map<String,String> iterateElement(Element element,Map<String,String> obj) {
		List jiedian = element.getChildren();
		Element et = null;
		List list = null;
		for (int i = 0; i < jiedian.size(); i++) {
			list = new LinkedList();
			et = (Element) jiedian.get(i);
			if (et.getChildren().size() == 0){
//				if("ERRCODE".equals(et.getName()) || "ERRMSG".equals(et.getName()) ||
//						"REQDATE".equals(et.getName()) || "REQTIME".equals(et.getName()) ||	
//						"bonus2amount".equals(et.getName()) || "bonusearn ".equals(et.getName()) ||
//						"bonusredeem ".equals(et.getName()) || "currentbonus  ".equals(et.getName()) ||
//						"traceno  ".equals(et.getName())){
					obj.put(et.getName(), et.getTextTrim());
//				}
			}else{
				iterateElement(et,obj);
			}
			//System.out.println(et.getName()+","+et.getText());
			
			/*if (et.getTextTrim().equals("")) {
				if (et.getChildren().size() == 0)
					continue;
				if (obj.containsKey(et.getName())) {
					list = (List) obj.get(et.getName());
				}
				list.add(iterateElement(et));
				obj.put(et.getName(), list);
			} else {
				if (obj.containsKey(et.getName())) {
					list = (List) obj.get(et.getName());
				}
				list.add(et.getTextTrim());
				obj.put(et.getName(), list);
			}*/
		}
		return obj;
	}

	/**
	 * xml转list
	 * @param xmlString xml字符串
	 */
	public static Map<String, List<Map<String, String>>> XmlStringReader(String xmlString){
		 //创建一个新的字符串  
        StringReader read = new StringReader(xmlString);  
        //创建新的输入源SAX 解析器将使用 InputSource 对象来确定如何读取 XML 输入  
        InputSource source = new InputSource(read);  
        //创建一个新的SAXBuilder  
        SAXBuilder saxbBuilder = new SAXBuilder();  
        try {
        	Map<String, List<Map<String, String>>> maps=new HashMap<>();
        	List<Map<String, String>>list=new ArrayList<>();
        	Map<String, String> map;
            //通过输入源构造一个Document  
            Document doc = saxbBuilder.build(source);  
            //取的根元素  
            Element root = doc.getRootElement();  
            //System.out.println(root.getName());
            List<?> node = root.getChildren();  
            for (int i = 0; i < node.size(); i++) {
            	Element element=(Element)node.get(i);
            	map=new HashMap<>();
            	//System.out.println(element.getName());
            	List<?> subNode = element.getChildren();  
            	for(int j=0;j<subNode.size();j++){
            		Element subElement=(Element)subNode.get(j);
            		map.put(subElement.getName(), subElement.getContent(0).getValue());
            	}
            	list.add(map);
			}
            maps.put(root.getName(), list);
            return maps;
        } catch (JDOMException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } 
        return null;
	}
	// 测试
	public static void main(String[] args) throws Exception {
		PropertiesUtil pu = new PropertiesUtil("wsInterface.properties");
		String data = pu.readProperty("resultParam");

		Map json = xml2Map(data);
		System.out.println(json);
//		String data = "<?xml version="1.0" encoding="utf-8"?><soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><soap:Body><ConsumeResponse xmlns="http://www.tech-trans.com.cn/"><ConsumeResult><Header><ERRCODE>1</ERRCODE><ERRMSG>Input string was not in a correct format.</ERRMSG><REQDATE>20160527</REQDATE><REQTIME>200521</REQTIME></Header><DATA><bonus2amount>0</bonus2amount><bonusearn /><bonusredeem /><currentbonus /><traceno /></DATA></ConsumeResult></ConsumeResponse></soap:Body></soap:Envelope>";
//		System.out.println(ParseXmlUtil.xml2JSON(data));
	}
}
