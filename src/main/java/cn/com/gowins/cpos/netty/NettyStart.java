package cn.com.gowins.cpos.netty;

import java.text.ParseException;

public class NettyStart {
	
	public static void start(){
		//PushServer.start();
		Thread NettyStart = new Thread(new Runnable() {
            @Override
            public void run() {
                PushServer.start();
            }
        });
		NettyStart.start();
	}
	
	public static void push(){
		Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
					PushServer.push("text",null);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        t2.start();
	}

}
