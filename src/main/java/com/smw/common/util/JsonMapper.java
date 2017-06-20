package com.smw.common.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;  
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;  
import org.codehaus.jackson.map.SerializationConfig;  
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;  
import org.codehaus.jackson.map.util.JSONPObject;  
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * JsonMapper:
 *
 * @author yumaochun
 * @date  2016年3月23日
 * @version  jdk1.8
 *
 */
public class JsonMapper {

	  private static Logger logger = LoggerFactory.getLogger(JsonMapper.class);

	  private ObjectMapper mapper;

	  public JsonMapper(Inclusion inclusion) {
	    mapper = new ObjectMapper();
	    //设置输出时包含属性的风格
	    mapper.setSerializationInclusion(inclusion);
	    //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    //禁止使用int代表Enum的order()來反序列化Enum,非常危險
	    mapper.configure(DeserializationConfig.Feature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
	  }

	  /**
	   * 创建输出全部属性到Json字符串的Mapper.
	   */
	  public static JsonMapper buildNormalMapper() {
	    return new JsonMapper(Inclusion.ALWAYS);
	  }

	  /**
	   * 创建只输出非空属性到Json字符串的Mapper.
	   */
	  public static JsonMapper buildNonNullMapper() {
	    return new JsonMapper(Inclusion.NON_NULL);
	  }

	  /**
	   * 创建只输出初始值被改变的属性到Json字符串的Mapper.
	   */
	  public static JsonMapper buildNonDefaultMapper() {
	    return new JsonMapper(Inclusion.NON_DEFAULT);
	  }

	  /**
	   * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper.
	   */
	  public static JsonMapper buildNonEmptyMapper() {
	    return new JsonMapper(Inclusion.NON_EMPTY);
	  }

	  /**
	   * 如果对象为Null, 返回"null".
	   * 如果集合为空集合, 返回"[]".
	   */
	  public String toJson(Object object) {

	    try {
	      return mapper.writeValueAsString(object);
	    } catch (IOException e) {
	      return null;
	    }
	  }

	  /**
	   * 如果JSON字符串为Null或"null"字符串, 返回Null.
	   * 如果JSON字符串为"[]", 返回空集合.
	   * 
	   * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型.
	   * @see #constructParametricType(Class, Class...)
	   */
	  public <T> T fromJson1(String jsonString, Class<T> clazz) {
	    if (StringUtils.isEmpty(jsonString)) {
	      return null;
	    }

	    try {
	      return mapper.readValue(jsonString, clazz);
	    } catch (IOException e) {
	    	return null;
	    }
	  }

	  /**
	   * 如果JSON字符串为Null或"null"字符串, 返回Null.
	   * 如果JSON字符串为"[]", 返回空集合.
	   * 
	   * 如需读取集合如List/Map, 且不是List<String>这种简单类型时,先使用函數constructParametricType构造类型.
	   * @see #constructParametricType(Class, Class...)
	   */
	  @SuppressWarnings("unchecked")
	  public <T> T fromJson(String jsonString, JavaType javaType) {
	    if (StringUtils.isEmpty(jsonString)) {
	      return null;
	    }

	    try {
	      return (T) mapper.readValue(jsonString, javaType);
	    } catch (IOException e) {
	    	logger.info(e.getMessage());
	    	return null;
	    }
	  }
	  
	  @SuppressWarnings("unchecked")
	  public <T> T fromJson(String jsonString, Class<?> parametrized, Class<?>... parameterClasses) {
	    return (T) this.fromJson(jsonString, constructParametricType(parametrized, parameterClasses));
	  }
	  
	  @SuppressWarnings("unchecked")
	  public <T> List<T> fromJsonToList(String jsonString, Class<T> classMeta){
	    return (List<T>) this.fromJson(jsonString,constructParametricType(List.class, classMeta));
	  }
	  
	  @SuppressWarnings("unchecked")
	  public <T> T fromJson(JsonNode node, Class<?> parametrized, Class<?>... parameterClasses) {
	    JavaType javaType = constructParametricType(parametrized, parameterClasses);
	    try {
	      return (T) mapper.readValue(node, javaType);
	    } catch (IOException e) {
	    	return null;
	    }
	  }
	  
	  @SuppressWarnings("unchecked")
	  public <T> T pathAtRoot(String json, String path, Class<?> parametrized, Class<?>... parameterClasses){
	    JsonNode rootNode = parseNode(json);
	    JsonNode node = rootNode.path(path);
	    return (T) fromJson(node, parametrized, parameterClasses);
	  }
	  
	  @SuppressWarnings("unchecked")
	  public <T> T pathAtRoot(String json, String path, Class<T> clazz){
	    JsonNode rootNode = parseNode(json);
	    JsonNode node = rootNode.path(path);
	    return (T) fromJson(node, clazz);
	  }

	  /**
	   * 構造泛型的Type如List<MyBean>, 则调用constructParametricType(ArrayList.class,MyBean.class)
	   *             Map<String,MyBean>则调用(HashMap.class,String.class, MyBean.class)
	   */
	  public JavaType constructParametricType(Class<?> parametrized, Class<?>... parameterClasses) {
	    return mapper.getTypeFactory().constructParametricType(parametrized, parameterClasses);
	  }

	  /**
	   * 當JSON裡只含有Bean的部分屬性時，更新一個已存在Bean，只覆蓋該部分的屬性.
	   */
	  @SuppressWarnings("unchecked")
	  public <T> T update(T object, String jsonString) {
	    try {
	      return (T) mapper.readerForUpdating(object).readValue(jsonString);
	    } catch (JsonProcessingException e) {
	      logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
	    } catch (IOException e) {
	      logger.warn("update json string:" + jsonString + " to object:" + object + " error.", e);
	    }
	    return null;
	  }

	  /**
	   * 輸出JSONP格式數據.
	   */
	  public String toJsonP(String functionName, Object object) {
	    return toJson(new JSONPObject(functionName, object));
	  }

	  /**
	   * 設定是否使用Enum的toString函數來讀寫Enum,
	   * 為False時時使用Enum的name()函數來讀寫Enum, 默認為False.
	   * 注意本函數一定要在Mapper創建後, 所有的讀寫動作之前調用.
	   */
	  public void setEnumUseToString(boolean value) {
	    mapper.configure(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING, value);
	    mapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, value);
	  }

	  /**
	   * 取出Mapper做进一步的设置或使用其他序列化API.
	   */
	  public ObjectMapper getMapper() {
	    return mapper;
	  }
	  
	  public JsonNode parseNode(String json){
	    try {
	      return mapper.readValue(json, JsonNode.class);
	    } catch (IOException e) {
	    	return null;
	    }
	  }
	  
	  /**
	   * 输出全部属性
	   * @param object
	   * @return
	   */
	  public static String toNormalJson(Object object){
	    return new JsonMapper(Inclusion.ALWAYS).toJson(object);
	  }
	  
	  /**
	   * 输出非空属性
	   * @param object
	   * @return
	   */
	  public static String toNonNullJson(Object object){
	    return new JsonMapper(Inclusion.NON_NULL).toJson(object);
	  }
	  
	  /**
	   * 输出初始值被改变部分的属性
	   * @param object
	   * @return
	   */
	  public static String toNonDefaultJson(Object object){
	    return new JsonMapper(Inclusion.NON_DEFAULT).toJson(object);
	  }
	  
	  /**
	   * 输出非Null且非Empty(如List.isEmpty)的属性
	   * @param object
	   * @return
	   */
	  public static String toNonEmptyJson(Object object){
	    return new JsonMapper(Inclusion.NON_EMPTY).toJson(object);
	  }
	  
	  public void setDateFormat(String dateFormat){
	    mapper.setDateFormat(new SimpleDateFormat(dateFormat));
	  }
	  
	  public static String toLogJson(Object object){
	    JsonMapper jsonMapper = new JsonMapper(Inclusion.NON_EMPTY);
	    jsonMapper.setDateFormat(DateUtil.getCurrentDatetime("-"));
	    return jsonMapper.toJson(object);
	  }
	  
	  /**
	   * 
	   * convertMap:将map转换为javaBean对象
	   *
	   * @date 2016年3月23日
	   * @param type          class
	   * @param map           map
	   * @return
	   * @throws IntrospectionException
	   * @throws IllegalAccessException
	   * @throws InstantiationException
	   * @throws InvocationTargetException
	   */
	  public static Object convertMap(Class type, Map map)  
	              throws IntrospectionException, IllegalAccessException,  
	             InstantiationException, InvocationTargetException {  
	          BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性  
	          Object obj = type.newInstance(); // 创建 JavaBean 对象  
	   
	          // 给 JavaBean 对象的属性赋值  
	          PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors();  
	         for (int i = 0; i< propertyDescriptors.length; i++) {  
	              PropertyDescriptor descriptor = propertyDescriptors[i];  
	              String propertyName = descriptor.getName();  
	   
	              if (map.containsKey(propertyName)) {  
	                  // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。  
	                  Object value = map.get(propertyName);  
	    
	                  Object[] args = new Object[1];  
	                 args[0] = value;  
	    
	                  descriptor.getWriteMethod().invoke(obj, args);  
	              }  
	          }  
	          return obj;  
	   }  
	 
	  /**
	   * 
	   * jsonConvertMap:将json字符串转换为map对象
	   *
	   * @author yumaochun
	   * @date 2016年4月27日
	   * @param jsonStr           待转换的json字符串
	   * @return   返回：转换后的map对象
	   */
	  public static Map<?,?> jsonConvertMap(String jsonStr){
		  Map<?, ?> map=null;
		   
		   try {
			ObjectMapper mapper = new ObjectMapper();
		    map = mapper.readValue(jsonStr, Map.class);
//		    System.out.println(map.size());
//		    Iterator<?> iterator = map.keySet().iterator();   
//		    while ( iterator.hasNext() ) {
//		     Object key = iterator.next();
//		     System.out.print(key+":");    
//		     System.out.println(map.get(key).toString());
//		    }
		   } catch (JsonParseException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch (JsonMappingException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   } catch (IOException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }

	       return map;
	  }
	  
	  /**
	   * 
	   * getParamsMapBy:将参数条件，存入map集合中（只对单一json数据处理）
	   *
	   * @author yumaochun
	   * @date 2016年4月27日
	   * @param paramArr      参数名称数组
	   * @param condition     条件参数及对应的值（json字符串）
	   * @return   返回：参数条件对应的值map
	   */
	  public static Map<String,Object> getParamsMapBy(String[] paramArr,String condition){
	    Map<String,Object> paramsMap=new HashMap<>();
		//将json字符串转换为Condition
	    JsonMapper jsonUtil=JsonMapper.buildNonDefaultMapper();
	    Map<String,Object> map=(Map<String, Object>) jsonUtil.jsonConvertMap(condition);
	    for (String param : paramArr) {
	        paramsMap.put(param, map.get(param));
		}
		return paramsMap;
	  }

      public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
    	  String s = "{ \"name\" : \"萧远山\", \"sex\" : \"男\", \"age\" : \"23\",\"address\" : \"河南郑州\"}";
    	
 		System.out.println("=="+getParamsMapBy(new String[]{"name","sex"},s));
	}  
}
