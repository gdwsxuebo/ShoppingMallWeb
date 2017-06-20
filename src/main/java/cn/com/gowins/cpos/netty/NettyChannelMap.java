package cn.com.gowins.cpos.netty;

import io.netty.channel.Channel;
import io.netty.channel.socket.SocketChannel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.smw.common.util.BasePageResultVo;
import com.smw.utils.SetUpdateTime;

import cn.com.gowins.cpos.netty.module.SendMsg;

/**
 * @author 
 * @version
 */
public class NettyChannelMap {
    private static Map<String,SocketChannel> map=new ConcurrentHashMap<>();
    
    private static HashMap<String,String> mapadd = new HashMap<String,String>();
    
    
   

	public static HashMap<String, String> getMapadd() {
		return mapadd;
	}


	public static void setMapadd(HashMap<String, String> mapadd) {
		NettyChannelMap.mapadd = mapadd;
	}


	public static void add(String clientId,SocketChannel socketChannel){
        map.put(clientId,socketChannel);
    }
	
	
	public static void mapadd(String ip,String clientId){
        mapadd.put(ip, clientId);
    }

    public static Channel get(String clientId){
        return map.get(clientId);
    }
    
    
    public static String getmapadd(String ip){
        return mapadd.get(ip);
    }
    

    public static void remove(SocketChannel socketChannel){

        for (Map.Entry entry:map.entrySet()){
            if (entry.getValue()==socketChannel){
            	
                String key = (String) entry.getKey();
                BasePageResultVo remesset = null;
                SendMsg loginMsg = new SendMsg();
                loginMsg.setTillId(key);
                //loginMsg.setIp("127.0.0.1");
				remesset = SetUpdateTime.UpdateTillid(loginMsg,"0");
                System.out.println("通道"+ key +"已被移除。");
                System.out.println("通道"+ key +"已被移除。");
                map.remove(key);
                
                
            }
        }
    }

}