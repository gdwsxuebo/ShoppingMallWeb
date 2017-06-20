package com.smw.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * 
 * CollectionUtil:集合工具类
 *
 * @author yumaochun
 * @date  2016年3月5日
 * @version  jdk1.8
 *
 */
public class CollectionUtil {
	
	/**
	 * 判断对象是否为空
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object)
	{
		if(String.class.isInstance(object))
		{
			String string = (String)object;
			return string == null ? true : string.trim().length() == 0;
		}
		
		if(Collection.class.isInstance(object))
		{
			Collection col = (Collection)object;
			return col == null ? true : col.isEmpty();
		}
		
		if(Map.class.isInstance(object))
		{
			Map map = (Map)object;
			return map == null ? true : map.isEmpty();
		}
		
		if(Vector.class.isInstance(object))
		{
			Vector vector = (Vector)object;
			return vector == null ? true : vector.size() == 0;
		}
		
		if(Object[].class.isInstance(object))
		{
			Object[] obj = (Object[])object;
			return obj == null ? true : obj.length == 0;
		}
		
		return object == null;
	}
	
	/**
	 * 计算整型数组中元素和
	 * @param c
	 * @return
	 */
	public static int getCount(int[] c)
	{
		int count = 0;
		if(c != null)
		{
			for(int i = 0; i < c.length; i++)
			{
				count += c[i];
			}
		}
		return count;
	}
	//去掉list集合中的重复对象
	public static List removeDuplication(List list){
		//定义set
		Set set = new HashSet();
		set.addAll(list);
		list.clear();
		return new ArrayList(set);
	}

}
