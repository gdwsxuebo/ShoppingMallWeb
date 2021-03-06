package cn.com.gowins.cpos.netty.module;

/**
 * 推送消息类型
 * title 推送标记字符串
 * content 推送内容
 * @author 
 * @version 
 */
public class PushMsg extends BaseMsg {

    private String title;
    private String content;

    public PushMsg() {
        super();
        setType(MsgType.PUSH);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
