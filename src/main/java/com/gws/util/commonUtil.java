package com.gws.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.smw.common.util.DateUtil;



public class commonUtil {
	public final static String ComConfigConid="AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
    public  static ExecutorService executorService = Executors.newCachedThreadPool();
    private static int orderNum;
	public static String formatFenToYuan( long fen) {  
	    String yuan = "0";  
	     yuan = new BigDecimal(fen).divide(new BigDecimal(100)).setScale(2).toString();  
	     return yuan;  
	    }  
	public static String fromYuanToFen(final String yuan) {  
        String fen = "0";  
            try {  
                NumberFormat format = NumberFormat.getInstance();  
                Number number = format.parse(yuan);  
                double temp = number.doubleValue() * 100.0;  
                // 默认情况下GroupingUsed属性为true 不设置为false时,输出结果为2,012  
                format.setGroupingUsed(false);  
                // 设置返回数的小数部分所允许的最大位数  
                format.setMaximumFractionDigits(0);  
                fen = format.format(temp);  
            } catch (ParseException e) {  
                e.printStackTrace();  
            }  
        return fen;  
    }
	
	public static String createFourNum(){
		  int	orderNumLen=String.valueOf(orderNum).length();
		  if(orderNumLen==5) orderNum=0;
		  String result=String.valueOf(orderNum);
		  for(int i=0;i<4-orderNumLen;i++){
			  result="0"+result;
		  }
		  orderNum++;
		  return result;
	  }
	
	  public static String createOrder(String prefix){
		  String curDate=DateUtil.format(new Date(),"yyyyMMddssHHmm");
		  curDate=curDate.substring(2, curDate.length());
		  return prefix+curDate+createFourNum();
	  }
}
