package com.smw.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * BaseCodeUtil:基础编号生成辅助工具
 *
 * @author yumaochun
 * @date  2016年4月7日
 * @version  jdk1.8
 *
 */
public class BaseCodeUtil {
	
	 
	/**
	 * 
	 * getSettlementCode:根据合同号和账期序号，生成费用代码单号
	 *
	 * @date 2016年4月7日
	 * @param code              合同号
	 * @param costCodeId        费用代码id
	 * @param index             序号
	 * @return   返回：费用代码单号
	 */
	public static String getSettlementCode(String code,int costCodeId,int index){
		//生成结算单号 =合同号+费用代码id（4位）+序号（4位）
		String settlementCode=code+getFourCode(costCodeId)+getFourCode(index);
		return settlementCode;
	}
	
	/**
	 * 
	 * getSettlementId:生成结算单号
	 *
	 * @date 2016年4月14日
	 * @param code            合同号
	 * @param accountPayable    账期
	 * @param settlementGroupCode   结算组别编号
	 * @return
	 */
	public static String getSettlementId(String code,String accountPayable,String settlementGroupCode){
		int settlementGroupId=Integer.parseInt(settlementGroupCode);//结算组别id
		String settlementId=""+code+getFourCode(settlementGroupId)+accountPayable;
		return settlementId;
	}
	
	public static void main(String[] args) {
		
		System.out.println(getFourCode(4));
	}
	/**
	 * 
	 * getFourCode:根据数字生成4位长度的数字字符串（补位）
	 *
	 * @date 2016年4月7日
	 * @param index      需要转换的数字
	 * @return  返回：返回四位数字（补位）
	 */
	public static String getFourCode(int index){
		
		return getFixedLengthStr(index,4);
	}
	/**
	 * 
	 * getFixedLengthStr:将数值，生成以0补位的指定长度的字符串
	 *
	 * @date 2016年4月7日
	 * @param num      需要转换的数值
	 * @param len      生成的字符串的长度
	 * @return   返回：生成补位的字符串
	 */
	public static String getFixedLengthStr(int num,int len){
		String replaceNum="0";//补位的字符
		String newNum=String.format("%"+replaceNum+len+"d", num);
		return newNum;
	}
	
	


}
