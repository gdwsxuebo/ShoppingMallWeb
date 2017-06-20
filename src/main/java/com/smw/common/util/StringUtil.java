package com.smw.common.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * 字符串处理类
 * @author fx1
 *
 */
public class StringUtil {

	private static int  x=0;
	public static String format(double n,int len)
	{
		NumberFormat fm=NumberFormat.getInstance();
		fm.setMaximumFractionDigits(len);
		String res=fm.format(n);
		res=res.replaceAll(",", "");
		String zo="";
		int dot=res.lastIndexOf(".");
		int ned=dot<0?len:(len-(res.length()-dot-1));
		zo=dot<0?".":"";
		for(int i=0;i<ned;i++)
			zo+="0";
		fm=null;
		return res+zo;
	}
	/**
	 * 是不是整数
	 * @param str  验证的整数
	 * @return  返回：true-是整数，false- 不是整数
	 */
	public static boolean isInteger(String str)
	{
		boolean res=true;
		try
		{
			Integer.parseInt(str);
		}catch(Exception e){res=false;}
		return res;
	}
	/**
	 * 是不是long型
	 * @param str 验证的long
	 * @return 返回：true-是long，false- 不是long
	 */
	public static boolean isLong(String str){
		boolean res=true;
		try
		{
			Long.parseLong(str);
		}catch(Exception e){res=false;}
		return res;
	}
	
	public static boolean isDouble(String str)
	{
		boolean res=true;
		try
		{
			Double.parseDouble(str);
		}catch(Exception e){res=false;}
		return res;
	}
	/**
	 * 判断字符串数组是否都不为空 ：都不为空返回false，有空则放回true
	 * @param strs
	 * @return
	 */
	public static boolean isHaveNUllStr(String[] strs){
		boolean res=true;
		for (String string : strs) {
			if(string!=null&&!string.equals("")){
			    res=false;
			}
			else{
				res=true;
				break;
			}
		}
		return res;
	}
	/**
	 * 验证是否为空字符串：空则返回true，否则返回false
	 * @param str
	 * @return
	 */
	public static boolean isNUllStr(String str){
		boolean res=true;
		if(str!=null&&!str.trim().equals("")){
			res=false;
		}
		return res;
	}
	
	/**
	 * 当字符串超过指定长度，截断
	 * @param str
	 * @param length 指定长度
	 * @return
	 */
	public static String cutString(String str, int length)
	{
		if(!isEmpty(str)){
			if(str.length() > length)
			{
				return str.substring(0, length);
			}
		}
		return str;
	}
	
	/**
	 * 将字符串中的小数点去掉
	 * @param string
	 * @return
	 */
	public static String round(String string)
	{
		if(!StringUtil.isEmpty(string))
		{
			if(string.indexOf(".") != -1)
			{
				string = string.substring(0, string.indexOf("."));
			}
		}
		if(StringUtil.isDigit(string))
		{
			return String.valueOf(Long.parseLong(string));
		}
		else
		{
			if(!StringUtil.isEmpty(string))
			{
				if(string.indexOf(".") != -1)
				{
					return string.substring(0, string.indexOf("."));
				}
				return string;
			}
		}
		return null;
	}
	
	/**
	 * 字符串转成int
	 * @param string
	 * @return
	 */
	public static int toInteger(String string)
	{
		NumberFormat format = NumberFormat.getNumberInstance();
		int intValue = 0;
		try {
			intValue = format.parse(string).intValue();
		} catch (ParseException e) {
		}
		return intValue;
	}
	

	/**
	 * 将字符串第一个字符转成大写
	 * @param string
	 * @return
	 */
	public static String toFirstUpperCase(String string)
	{
		if(!hasText(string)) return null;
		
		StringBuffer buf = new StringBuffer();
		buf.append(Character.toUpperCase(string.charAt(0)));
		buf.append(string.substring(1));
		
		return buf.toString();
	}
	

	/**
	 * 按字符串字母大写的位置来
	 * 指定的分隔符分隔字符串
	 * 如:StringUtil.splitWords("TopSpeed", '_') = top_speed
	 * @param string 要分隔的字符串 ,not null
	 * @param delimiter 指定的分隔符, not null
	 * @return the words splited
	 */
	public static String splitWords(String string, char delimiter)
	{
		
		if(!hasText(string)) return null;
		
		StringBuffer buf = new StringBuffer();
		for(int i = 0; i < string.length(); i++)
		{
			if(Character.isUpperCase(string.charAt(i)))
			{
				buf.append(delimiter);
			}
			buf.append(Character.toLowerCase(string.charAt(i)));
		}
		if(buf.charAt(0) == delimiter) buf.delete(0, 1);
		
		return buf.toString();
	}
	
	/**
	 * 去掉字符串前后空格将字符串转换成大写
	 * @param string
	 * @return
	 */
	public static String toUpperCase(String string)
	{
		return trimWhitespace(string).toUpperCase();
	}
	

	/**
	 * 去掉字符串前后空格将字符串转换成小写
	 * @param string
	 * @return
	 */
	public static String toLowerCase(String string)
	{
		return trimWhitespace(string).toLowerCase();
	}
	
	/**
	 * 去掉字符串前后空格判断字符串是否为空串
	 * @param string
	 * @return
	 */
	public static boolean isEmpty(String string)
	{
		return string == null ? true : trimWhitespace(string).length() == 0;
	}

	/**
	 * 判断char是否是中文
	 * @param c
	 * @return
	 */
	public static boolean isChineseChar(char c)
	{
		if(c >= '\u4e00' && c <= '\u9fa5' )	return true;
		
		return false;
	}
	

	/**
	 * 判断指定字符串是串是数字
	 * 1.1或1-1格式都返回真
	 * @param string
	 * @return
	 */
	public static boolean isDigit(String string)
	{
		if(!hasText(string)) return false;
		string = trimWhitespace(string);
		for(int i = 0; i < string.length(); i++)
		{
			char c = string.charAt(i);
			if(Character.isDigit(c) || c == '.' || c == '-')
				continue;
			return false;
		}
		if(string.indexOf(".") != string.lastIndexOf("."))
			return false;
		if(string.indexOf("-") != string.lastIndexOf("-"))
			return false;
		
		return true;
	}
	
	
	
	


	//---------------------------------------------------------------------
	// General convenience methods for working with Strings
	//---------------------------------------------------------------------

	/**
	 * 检查给定的字符串既不是null也不是""
	 * 如：
	 * StringUtils.hasLength(null) = false
	 * StringUtils.hasLength("") = false
	 * StringUtils.hasLength(" ") = true
	 * StringUtils.hasLength("Hello") = true
	 * 
	 */
	public static boolean hasLength(String str) {
		return (str != null && str.length() > 0);
	}

	/**
	 * 检查给定的字符串实际的文字
	 * 如
	 * StringUtils.hasText(null) = false
	 * StringUtils.hasText("") = false
	 * StringUtils.hasText(" ") = false
	 * StringUtils.hasText("12345") = true
	 * StringUtils.hasText(" 12345 ") = true
	 */
	public static boolean hasText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查给定的字符串中是否包含任何空格字符
	 * @param str 如果是null返回false
	 * @return 
	 */
	public static boolean containsWhitespace(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 去掉字符串前后所有空格
	 * 输入参数为null返回空
	 * @param str 
	 * @return
	 */
	public static String trim(String str)
	{
		if(!hasText(str)) return "";
		return str.trim();
	}
	
	/**
	 * 去掉字符串前后所有空格
	 * 输入参数为null返回null字符串
	 * @param str
	 * @return
	 */
	public static String trimWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * 去掉字符串前所有空格
	 * 如：
	 * StringUtil.trimLeadingWhitespace("   aaa_aa ")="aaa_aa "
	 * @param str the String to check
	 * @return the trimmed String
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(0))) {
			buf.deleteCharAt(0);
		}
		return buf.toString();
	}

	/**
	 * 去掉字符串前所有空格
	 * 如：
	 * StringUtil.trimTrailingWhitespace("   aaa_aa ")="   aaa_aa"
	 * @param str the String to check
	 * @return the trimmed String
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		while (buf.length() > 0 && Character.isWhitespace(buf.charAt(buf.length() - 1))) {
			buf.deleteCharAt(buf.length() - 1);
		}
		return buf.toString();
	}

	/**
	 * 去掉字符串中所有空格
	 * 如：StringUtil.trimAllWhitespace("   aaa   _aa ")="aaa_aa"
	 * @param str the String to check
	 * @return the trimmed String
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str);
		int index = 0;
		while (buf.length() > index) {
			if (Character.isWhitespace(buf.charAt(index))) {
				buf.deleteCharAt(index);
			}
			else {
				index++;
			}
		}
		return buf.toString();
	}


	/**
	 * 判断给定字符串前缀是否为指定字符串 忽略大小写并包括空格。
	 * 如：
	 * StringUtil.startsWithIgnoreCase("abcd","ab")=true
	 * StringUtil.startsWithIgnoreCase("abcd","Ab")=true
	 * StringUtil.startsWithIgnoreCase("abcd","cd")=false
	 * StringUtil.startsWithIgnoreCase(" abcd"," ab")=true
	 * @param str 给定字符串
	 * @param prefix 指字前缀
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (str == null || prefix == null) {
			return false;
		}
		if (str.startsWith(prefix)) {
			return true;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lcStr = str.substring(0, prefix.length()).toLowerCase();
		String lcPrefix = prefix.toLowerCase();
		return lcStr.equals(lcPrefix);
	}

	/**
	 * 判断给定字符串后缀是否为指定字符串 忽略大小写并包括空格。
	 * 如：
	 * StringUtil.startsWithIgnoreCase("abcd","cd")=true
	 * StringUtil.startsWithIgnoreCase("abcd","CD")=true
	 * StringUtil.startsWithIgnoreCase("abcd","ab")=false
	 * StringUtil.startsWithIgnoreCase(" abcd ","CD ")=true
	 * @param str 给定字符串
	 * @param prefix 指字后缀
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (str == null || suffix == null) {
			return false;
		}
		if (str.endsWith(suffix)) {
			return true;
		}
		if (str.length() < suffix.length()) {
			return false;
		}

		String lcStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lcSuffix = suffix.toLowerCase();
		return lcStr.equals(lcSuffix);
	}

	/**
	 * 计算子串在给定字符串中出现次数
	 * @param str 给定字符串如果为空或null返回0
	 * @param sub 子串如果为空或null返回0
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0, pos = 0, idx = 0;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 将一个字符串中的旧子字符串替换成新的子字符串
	 * 如：StringUtil.replace(" abcd ab ab abcd ab","ab","x")=" xcd x x xcd x"
	 * @param inString 源字符串
	 * @param oldPattern 旧子字符串
	 * @param newPattern 新子字符串
	 * @return 新字符串
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}

	/**
	 * 将给定字符串中所有出现的子字符串都删除
	 * 如：StringUtil.delete("ecd ab c"," ab")="ecd c"
	 * @param inString 指定字符串
	 * @param pattern 要删除的子字符串
	 */
	public static String delete(String inString, String pattern) {
		return replace(inString, pattern, "");
	}

	/**
	 * 删除给定字符串中所有子字符串包扩的字符
	 * 如：StringUtil.deleteAny("ecd ab ca x b"," ab")="ecdcx"
	 * @param inString 指定字符串
	 * @param pattern 要删除的字符子串
	 * 
	 * E.g. "az\n" will delete 'a's, 'z's and new lines.
	 */
	public static String deleteAny(String inString, String charsToDelete) {
		if (inString == null || charsToDelete == null) {
			return inString;
		}
		StringBuffer out = new StringBuffer();
		for (int i = 0; i < inString.length(); i++) {
			char c = inString.charAt(i);
			if (charsToDelete.indexOf(c) == -1) {
				out.append(c);
			}
		}
		return out.toString();
	}


	//---------------------------------------------------------------------
	// Convenience methods for working with formatted Strings
	//---------------------------------------------------------------------

	/**
	 * 用单引号引用给定的字符串
	 * @param str the input String (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or <code>null<code> if the input was <code>null</code>
	 */
	public static String quote(String str) {
		return (str != null ? "'" + str + "'" : null);
	}
	
	/**
	 * 去掉给定字符串中的单引号和双引号
	 * @param str the input String (e.g. "'myString'")
	 * @return the unquoted String (e.g. "myString"),
	 */
	public static String unQuote(String str)
	{
		return replace(replace(str, "'", ""), "\"", "");
	}

	/**
	 * 如果是字符串对象就增加单引号反之返回传入对象
	 * @param obj the input Object (e.g. "myString")
	 * @return the quoted String (e.g. "'myString'"),
	 * or the input object as-is if not a String
	 */
	public static Object quoteIfString(Object obj) {
		return (obj instanceof String ? quote((String) obj) : obj);
	}

	/**
	 * 截取'.'后的字符串
	 * 如：StringUtil.unqualify("this.name.is.qualified.")="";
	 * StringUtil.unqualify("this.name.is.qualified")="qualified"
	 * @param qualifiedName the qualified name
	 */
	public static String unqualify(String qualifiedName) {
		return unqualify(qualifiedName, '.');
	}

	/**
	 * 截取指定参数separator后的字符串
	 * Unqualify a string qualified by a separator character. 
	 * 例如："this:name:is:qualified" returns "qualified" if using a ':' separator.
	 *      StringUtil.unqualify("this.name.is.qualified.",'s')=".qualified."
	 * @param qualifiedName 源字符串
	 * @param separator 指定参数
	 */
	public static String unqualify(String qualifiedName, char separator) {
		return qualifiedName.substring(qualifiedName.lastIndexOf(separator) + 1);
	}

	//---------------------------------------------------------------------
	// Convenience methods for working with String arrays
	//---------------------------------------------------------------------

	/**
	 * 将指定字符串添加到源字符串数组中生成新的字符串数组
	 * @param array 源字符串数组
	 * @param str 要添加数组中的字符串
	 * @return the new array (never <code>null</code>)
	 */
	public static String[] addStringToArray(String[] array, String str) {
		if (CollectionUtil.isEmpty(array)) {
			return new String[] {str};
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * 将两个字符串数组合成一个新的字符串数组
	 * @param array1 the first array (can be <code>null</code>)
	 * @param array2 the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
	 */
	public static String[] concatenateStringArrays(String[] array1, String[] array2) {
		if (CollectionUtil.isEmpty(array1)) {
			return array2;
		}
		if (CollectionUtil.isEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 合并两个字符串数组并去掉重复元素
	 * @param array1 the first array (can be <code>null</code>)
	 * @param array2 the second array (can be <code>null</code>)
	 * @return the new array (<code>null</code> if both given arrays were <code>null</code>)
	 */
	@SuppressWarnings("unchecked")
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		if (CollectionUtil.isEmpty(array1)) {
			return array2;
		}
		if (CollectionUtil.isEmpty(array2)) {
			return array1;
		}
		List result = new ArrayList();
		result.addAll(Arrays.asList(array1));
		for (int i = 0; i < array2.length; i++) {
			String str = array2[i];
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * 反转字符串数组元素
	 * @param array the source array
	 * @return the sorted array (never <code>null</code>)
	 */
	public static String[] sortStringArray(String[] array) {
		if (CollectionUtil.isEmpty(array)) {
			return new String[0];
		}
		Arrays.sort(array);
		return array;
	}

	/**
	 * 将字符串集合转成字符串数组
	 * @param collection 字符串集合
	 * @return the String array (<code>null</code> if the passed-in
	 * Collection was <code>null</code>)
	 */
	@SuppressWarnings("unchecked")
	public static String[] toStringArray(Collection collection) {
		if (collection == null) {
			return null;
		}
		return (String[]) collection.toArray(new String[collection.size()]);
	}

	/**
	 * 去掉字符串数组中的重复元素
	 * @param array 字符串数组
	 * @return 新的字符串数组
	 */
	@SuppressWarnings("unchecked")
	public static String[] removeDuplicateStrings(String[] array) {
		if (CollectionUtil.isEmpty(array)) {
			return array;
		}
		Set set = new TreeSet();
		for (int i = 0; i < array.length; i++) {
			set.add(array[i]);
		}
		return toStringArray(set);
	}

	/**
	 * 按指定分格符分格字符串返回字符串数组
	 * @param string
	 * @param delimiter
	 * @return
	 */
	public static String[] split(String string, String delimiter)
	{
		if(hasText(string))
		{
			return string.split(delimiter);
		}
		return null;
	}


	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * Trims tokens and omits empty tokens.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter).
	 * @return an array of the tokens
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray
	 */
	public static String[] tokenizeToStringArray(String str, String delimiters) {
		return tokenizeToStringArray(str, delimiters, true, true);
	}

	/**
	 * Tokenize the given String into a String array via a StringTokenizer.
	 * <p>The given delimiters string is supposed to consist of any number of
	 * delimiter characters. Each of those characters can be used to separate
	 * tokens. A delimiter is always a single character; for multi-character
	 * delimiters, consider using <code>delimitedListToStringArray</code>
	 * @param str the String to tokenize
	 * @param delimiters the delimiter characters, assembled as String
	 * (each of those characters is individually considered as delimiter)
	 * @param trimTokens trim the tokens via String's <code>trim</code>
	 * @param ignoreEmptyTokens omit empty tokens from the result array
	 * (only applies to tokens that are empty after trimming; StringTokenizer
	 * will not consider subsequent delimiters as token in the first place).
	 * @return an array of the tokens (<code>null</code> if the input String
	 * was <code>null</code>)
	 * @see java.util.StringTokenizer
	 * @see java.lang.String#trim()
	 * @see #delimitedListToStringArray
	 */
	@SuppressWarnings("unchecked")
	public static String[] tokenizeToStringArray(
			String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens) {

		if (str == null) {
			return null;
		}
		StringTokenizer st = new StringTokenizer(str, delimiters);
		List tokens = new ArrayList();
		while (st.hasMoreTokens()) {
			String token = st.nextToken();
			if (trimTokens) {
				token = token.trim();
			}
			if (!ignoreEmptyTokens || token.length() > 0) {
				tokens.add(token);
			}
		}
		return toStringArray(tokens);
	}

	/**
	 * Take a String which is a delimited list and convert it to a String array.
	 * <p>A single delimiter can consists of more than one character: It will still
	 * be considered as single delimiter string, rather than as bunch of potential
	 * delimiter characters - in contrast to <code>tokenizeToStringArray</code>.
	 * @param str the input String
	 * @param delimiter the delimiter between elements (this is a single delimiter,
	 * rather than a bunch individual delimiter characters)
	 * @return an array of the tokens in the list
	 */
	@SuppressWarnings("unchecked")
	public static String[] delimitedListToStringArray(String str, String delimiter) {
		if (str == null) {
			return new String[0];
		}
		if (delimiter == null) {
			return new String[] {str};
		}
		List result = new ArrayList();
		if ("".equals(delimiter)) {
			for (int i = 0; i < str.length(); i++) {
				result.add(str.substring(i, i + 1));
			}
		}
		else {
			int pos = 0;
			int delPos = 0;
			while ((delPos = str.indexOf(delimiter, pos)) != -1) {
				result.add(str.substring(pos, delPos));
				pos = delPos + delimiter.length();
			}
			if (str.length() > 0 && pos <= str.length()) {
				// Add rest of String, but not in case of empty input.
				result.add(str.substring(pos));
			}
		}
		return toStringArray(result);
	}

	/**
	 * 以字符串中的“,”拆分成字符串数组
	 * @param str 源字符串
	 * @return an array of Strings, or the empty array in case of empty input
	 */
	public static String[] commaDelimitedListToStringArray(String str) {
		return delimitedListToStringArray(str, ",");
	}

	/**
	 * 以字符串中的“,”拆分成字符串数组并放入Set集合中去掉重复元素
	 * @param str the input String
	 * @return a Set of String entries in the list
	 */
	@SuppressWarnings("unchecked")
	public static Set commaDelimitedListToSet(String str) {
		Set set = new TreeSet();
		String[] tokens = commaDelimitedListToStringArray(str);
		for (int i = 0; i < tokens.length; i++) {
			set.add(tokens[i]);
		}
		return set;
	}

	/**
	 * 给定分隔符、开始字符串、结束字符串对集合中元素进行重组成新的字符串
	 *
	 * 如：List list = new ArrayList();
   	 *    list.add("ab");
     *	  list.add("AB");
     *    StringUtil.collectionToDelimitedString(list ,"_","1","32")＝"1ab32_1AB32"
	 * @param coll 集合
	 * @param delim 分隔符
	 * @param prefix 开始字符串
	 * @param suffix 结束字符串
	 */
	
	@SuppressWarnings("unchecked")
	public static String collectionToDelimitedString(Collection coll, String delim, String prefix, String suffix) {
		if (CollectionUtil.isEmpty(coll)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		Iterator it = coll.iterator();
		while (it.hasNext()) {
			sb.append(prefix).append(it.next()).append(suffix);
			if (it.hasNext()) {
				sb.append(delim);
			}
		}
		return sb.toString();
	}

	/**
	 * 给定分隔符对集合中元素进行重组成新的字符串
	 *
	 * 如：List list = new ArrayList();
   	 *    list.add("ab");
     *	  list.add("AB");
     *    StringUtil.collectionToDelimitedString(list ,"_")＝"ab_AB"
	 * @param coll 集合
	 * @param delim 分隔符
	 */
	
	@SuppressWarnings("unchecked")
	public static String collectionToDelimitedString(Collection coll, String delim) {
		return collectionToDelimitedString(coll, delim, "", "");
	}

	/**
	 * 按","分隔符对集合中元素进行重组成新的字符串
	 *
	 * 如：List list = new ArrayList();
   	 *    list.add("ab");
     *	  list.add("AB");
     *    StringUtil.collectionToCommaDelimitedString(list)＝"ab,AB"
	 * @param coll 集合
	 */
	
	@SuppressWarnings("unchecked")
	public static String collectionToCommaDelimitedString(Collection coll) {
		return collectionToDelimitedString(coll, ",");
	}

	/**
	 * 按给定的分隔符对数组分隔组成字符串
	 * @param arr 数组
	 * @param delim 分隔符号
	 */
	public static String arrayToDelimitedString(Object[] arr, String delim) {
		if (CollectionUtil.isEmpty(arr)) {
			return "";
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if (i > 0) {
				sb.append(delim);
			}
			sb.append(arr[i]);
		}
		return sb.toString();
	}

	/**
	 * 按“,”分隔符对数组分隔组成字符串
	 * @param arr 数组
	 */
	public static String arrayToCommaDelimitedString(Object[] arr) {
		return arrayToDelimitedString(arr, ",");
	}
	/**
     * Html标签替换<转成&lt; 空格转成 &nbsp; \n转成<br />
     * @param in
     * @return
     */
    public static final String escapeHTMLTags(String in) {
        return in.replaceAll("<","&lt;").replaceAll("  "," &nbsp;").replaceAll("\n","<br />");
    }

    
    /**
     * 将字符串转成int
     * @param s
     * @return
     */
    public static int parseInt(String s) {
        return parseInt(s, 0);
    }

    /**
     * 将对象转成int
     * @param s
     * @return
     */
    public static int parseInt(Object s) {
        return parseInt(s==null ? null : s.toString(), 0);
    }

    /**
     * 根据字符串s不同得到int
     * 如果字符串是数字,返回s,异常或空时返回iDefault
     * @param s
     * @param iDefault
     * @return
     */
    public static int parseInt(String s, int iDefault) {
        if (s==null || s.equals("")) return iDefault;
        try {
            s = s.replaceAll(",","");
            int l = s.indexOf(".");
            if (l>0) s = s.substring(0,l);
            return Integer.parseInt(s);
        } catch (Exception e) {
            return iDefault;
        }
    }

    /**
     * 将字符串转成long
     * @param s
     * @return
     */
    public static long parseLong(String s) {
        return parseLong(s, 0L);
    }

    /**
     * 根据字符串s不同得到long
     * 如果字符串是数字,返回s,异常或空时返回iDefault
     * @param s
     * @param iDefault
     * @return
     */
    public static long parseLong(String s, long iDefault) {
        if (s==null || s.equals("")) return iDefault;
        try {
            s = s.replaceAll(",","");
            int l = s.indexOf(".");
            if (l>0) s = s.substring(0,l);
            return Long.parseLong(s);
        } catch (Exception e) {
            return iDefault;
        }
    }
    
    /**
     * 根据条件重新组成字符串
     * StringUtil.surround(new String[]{"a","b","c"},"1234")="a1234b1234c"
     * @param ss 数组
     * @param sep 源字符串
     * @return
     */
    public static String surround(String[] ss, String sep) {
        return surround(ss, sep, "", "");
    }

    /**
     * 根据条件重新组成字符串
     * 如：StringUtil.surround(new String[]{"a","b","c"},"1234","f","i")="fai1234fbi1234fci"
     * @param ss 数组
     * @param sep 源字符串
     * @param start 开始字符
     * @param end 结束字符
     * @return
     */
    public static String surround(String[] ss, String sep, String start, String end) {
        StringBuffer ret = new StringBuffer();
        for (int i = 0; i < ss.length; i++) {
            String s = ss[i];
            ret.append(i==0 ? "" : sep).append(start).append(s).append(end);
        }
        return ret.toString();
    }

    /**
     * 字符串输出如果S为空输出def反之输出s
     * @param s
     * @param def
     * @return
     */
    public static String getDefault(String s, String def) {
        return (s == null || s.equals("")) ? def : s;
    }

    /**
     * 用字符连接字符array
     * @param s1 array
     * @param s2 连接字符
     * @return string processed
     */
    public static String concatStr(String[] s1,String s2){
    	StringBuilder ret = new StringBuilder();
    	for (int i=0; i<s1.length; i++) {
    		if (i>0) ret.append(s2);
    		ret.append(s1[i]);
    	}
    	return ret.toString();
    }

    /**
     * 将对象转成字符串并去掉前后空格
     * @param s
     * @return
     */
    public static String parseString(Object s) {
        if (s == null) return "";
        return s.toString().trim();
    }
    
    /**
     * 将制定字符串str按split分割,返回不包含except的list
     * @param str
     * @param split
     * @param except 可以为null
     * @return
     */
    public static List<String> getListBySplit(String str, String split, String except){
    	List<String> list = new ArrayList<String>();
    	String[] strs = str.split(split);
    	if(CollectionUtil.isEmpty(strs)) return list;
    	for (int i = 0; i < strs.length; i++) {
    		String s = strs[i];
    		if(StringUtil.isEmpty(s)) continue;
    		if(!StringUtil.isEmpty(except) && except.equals(s)) continue;
    		list.add(s);
		}
    	return list;
    }

    @SuppressWarnings("unchecked")
	public static void main(String[] args) {
//    	String [] a ={"x","bc"};
//    	String [] b ={"1=5","3=8","x=t","x=6","x=0"};
//    	List list = new ArrayList();
//    	list.add("ab");
//    	list.add("AB");
    	String str = ",1,2,,4,,5";
    	String[] s = str.split(",");
    	System.out.println("size = " + s.length);
    	for (int i = 0; i < s.length; i++) {
    		System.out.println(s[i]);
		}
	}
    /**
	 * 
	 * removeSameElement:去掉数组中重复的元素(重复的元素只保留一个)
	 *
	 * @author yumaochun
	 * @date 2016年4月28日
	 * @param strArr          需要处理的字符数组
	 * @return   返回：没有重复元素的数组
	 */
	public static String[] removeSameElement(String[] strArr){
		
		List<String> list = new ArrayList<String>();  
		for (int i=0; i<strArr.length; i++) {  
		   if(!list.contains(strArr[i])) {  
		       list.add(strArr[i]);  
		   }  
	    }  
	   String[] newStr =  list.toArray(new String[1]); //返回一个包含所有对象的指定类型的数组
//	   for (String elementB:newStr ) {  
//		    System.out.print(elementB + " ");  
//		} 

       return newStr;
	}
	public static int createMsec(){
		x++;
		if(x==100) x=0;
		return x;
	}
	
	/**
     * 获取最近几天的日期
     */
    public static ArrayList<String> test(int intervals) {
		ArrayList<String> pastDaysList = new ArrayList<>();
		ArrayList<String> fetureDaysList = new ArrayList<>();
		for (int i = 0; i < intervals; i++) {
			pastDaysList.add(getPastDate(i));
			fetureDaysList.add(getFetureDate(i));
		}
		return pastDaysList;
	}
    
    /**
	 * 获取过去第几天的日期
	 */
	public static String getPastDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past-1);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}

	/**
	 * 获取未来 第 past 天的日期
	 */
	public static String getFetureDate(int past) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String result = format.format(today);
		return result;
	}
	
	
}


