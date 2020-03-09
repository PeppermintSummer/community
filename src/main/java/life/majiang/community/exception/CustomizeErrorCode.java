package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND(2001,"你找的问题不存在了，要不换一个试试"),
    TARGET_PARAM_NOT_FOUND(2002,"未选中任何问题或评论进行回复！"),
    NO_LOGIN(2003,"当前操作需要登录，请登录后重试！"),
    SYSTEM_ERROR(2004,"服务器端冒烟了！要不然过会再试试！"),
    TYPE_PARAM_WRONG(2005,"评论类型错误或者不存在！"),
    COMMENT_NOT_FOUND(2006,"回复的评论不存在了，要不换个试试！");

    @Override
    public String getmessage() {
        return message;
    }
    private String message;
    private Integer code;

    CustomizeErrorCode(Integer code, String message) {
        this.message = message;
        this.code = code;
    }

//    CustomizeErrorCode(String message){
//        this.message=message;
//    }


    @Override
    public Integer getcode() {
        return code;
    }
}
