package prm3101.group_assignment.data;

/**
 * Created by chuonghv on 1/2/18.
 */

public class ChatMessage {

    private String msgText;
    private String msgUser;

    public ChatMessage() {
    }

    public ChatMessage(String msgText, String msgUser){
        this.msgText = msgText;
        this.msgUser = msgUser;
    }

    public String getMsgText() {
        return msgText;
    }

    public String getMsgUser() {
        return msgUser;
    }
}
