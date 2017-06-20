package cn.com.gowins.cpos.netty.module;

/**
 * 消息基本信息
 *
 * @author 
 * @version 
 */
public class Constants {
    private static String clientId;

    public static String getClientId() {
        return clientId;
    }

    public static void setClientId(String clientId) {
        Constants.clientId = clientId;
    }
}
