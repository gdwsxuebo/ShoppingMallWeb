package cn.com.gowins.cpos.netty;

import java.text.ParseException;
import com.smw.common.util.BasePageResultVo;
import com.smw.common.util.JacksonUtil;
import com.smw.common.util.PropertiesUtil;
import com.smw.utils.SetUpdateTime;

import cn.com.gowins.cpos.netty.module.PushMsg;
import cn.com.gowins.cpos.netty.module.SendMsg;
import io.netty.channel.socket.SocketChannel;

/**
 * netty框架推送服务端
 * 
 * @author Administrator
 *
 */

public class PushServer {

	public static void start() {
		PropertiesUtil pUtil = new PropertiesUtil("systemConfig.properties");
		int port=Integer.parseInt(pUtil.readProperty("gdws.push.port"));
		try {
			new NettyServerBootstrap(port);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
    
    
    
	/**
	 * @param name 推送标记
	 * @param loginMsg连接消息类
	 * @throws ParseException
	 */
	public static void push(String name, SendMsg loginMsg) throws ParseException {
		
		if(loginMsg.getClientId()!=null||loginMsg.getClientId()!=""){
			SocketChannel channel = (SocketChannel) NettyChannelMap.get(loginMsg.getClientId());//获取推送通道连接channel
			if (channel != null) {
				PushMsg pushMsg = new PushMsg();
				pushMsg.setTitle(name);
				BasePageResultVo remes = null;
				BasePageResultVo remesset = null;
				remesset = SetUpdateTime.UpdateTillid(loginMsg,"1");
				remes = SetUpdateTime.getBasePageResultVo(loginMsg);
				pushMsg.setContent(JacksonUtil.toJSon(remes));
				channel.writeAndFlush(pushMsg);
				System.out.println(" 推送标题-------------->"+pushMsg.getTitle());
			}
		}
		
	

	}
    
    
	/**
	 * @param name
	 * @param ip
	 * @throws ParseException
	 */
	public static void pushByIp(String name, String ip) throws ParseException {

		SocketChannel channel = (SocketChannel) NettyChannelMap.get(NettyChannelMap.getmapadd(ip));

		System.out.println(NettyChannelMap.getmapadd(ip));

		if (channel != null) {
			PushMsg pushMsg = new PushMsg();
			pushMsg.setTitle(name);
			BasePageResultVo remes = null;
			BasePageResultVo remesset = null;
			// remesset=SetUpdateTime.UpdateTillid(NettyChannelMap.getmapadd(ip),
			// ip);
			SendMsg loginMsg = new SendMsg();
			loginMsg.setTillId(NettyChannelMap.getmapadd(ip));
			loginMsg.setIp(ip);
			remes = SetUpdateTime.getBasePageResultVo(loginMsg);
			pushMsg.setContent(JacksonUtil.toJSon(remes));
			channel.writeAndFlush(pushMsg);
			System.out.println(" 推送标题-------------->"+pushMsg.getTitle());
		}

	}
}
