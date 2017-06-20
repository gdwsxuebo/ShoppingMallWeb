package cn.com.gowins.cpos.netty;


import com.smw.common.util.BasePageResultVo;
import com.smw.utils.SetUpdateTime;

import cn.com.gowins.cpos.netty.module.BaseMsg;
import cn.com.gowins.cpos.netty.module.SendMsg;
import cn.com.gowins.cpos.netty.module.MsgType;
import cn.com.gowins.cpos.netty.module.PingMsg;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.util.ReferenceCountUtil;


/**
 * @author Administrator
 *
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<BaseMsg> {

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //channel失效，从Map中移除
        NettyChannelMap.remove((SocketChannel) ctx.channel());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        cause.printStackTrace();
        System.out.println("服务器端出现异常！");
    }

	// 这里是从客户端过来的消息
    
	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext, BaseMsg baseMsg) throws Exception {
		if (MsgType.LOGIN.equals(baseMsg.getType())) {
			SendMsg loginMsg = (SendMsg) baseMsg;
			if (loginMsg.getClientId() != null || loginMsg.getClientId() != "") {
				// 登录成功,把channel存到服务端的map中
				NettyChannelMap.add(loginMsg.getClientId(), (SocketChannel) channelHandlerContext.channel());
				System.out.println("client=" + loginMsg.getClientId() + "----code="+loginMsg.getXfStaffcode()+"---------->ip=" + loginMsg.getIp() + " 登录成功");
				PushServer.push("init", loginMsg);
			}
		} else {
			if (NettyChannelMap.get(baseMsg.getClientId()) == null) {
				// 说明未登录，或者连接断了，服务器向客户端发起登录请求，让客户端重新登录
				SendMsg loginMsg = new SendMsg();
				channelHandlerContext.channel().writeAndFlush(loginMsg);
			}
		}
		switch (baseMsg.getType()) {
		case PING:
			PingMsg pingMsg = (PingMsg) baseMsg;
			PingMsg replyPing = new PingMsg();
			if(pingMsg.getClientId()!=null){
				if(NettyChannelMap.get(pingMsg.getClientId())!=null){
					NettyChannelMap.get(pingMsg.getClientId()).writeAndFlush(replyPing);
				}	
			}
			System.out.println(pingMsg.getClientId() + "收到PING类型");
			break;
		case LOGIN:
			break;
		case PUSH:
			break;
		default:
			System.out.println("default。。");
			break;
		}
		ReferenceCountUtil.release(baseMsg);
	}

    
}