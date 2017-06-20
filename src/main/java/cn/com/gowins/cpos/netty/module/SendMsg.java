package cn.com.gowins.cpos.netty.module;

/**
 * 登录验证消息类型
 *tillId 收银机编号，唯一
 *ip  收银机ip地址
 * @author 
 * @version 
 */
public class SendMsg extends BaseMsg {
	 	private String ip;
	    private String tillId;
	    private String xfStaffcode;
	    public SendMsg() {
	        super();
	        setType(MsgType.LOGIN);
	    }

	    public String getIp() {
	        return ip;
	    }

	    public void setIp(String ip) {
	        this.ip = ip;
	    }

	    public String getTillId() {
	        return tillId;
	    }

	    public void setTillId(String tillId) {
	        this.tillId = tillId;
	    }

		public String getXfStaffcode() {
			return xfStaffcode;
		}

		public void setXfStaffcode(String xfStaffcode) {
			this.xfStaffcode = xfStaffcode;
		}
	    
	    
}
