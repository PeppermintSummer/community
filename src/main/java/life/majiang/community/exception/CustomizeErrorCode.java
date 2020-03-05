package life.majiang.community.exception;

public enum CustomizeErrorCode implements ICustomizeErrorCode {
    QUESTION_NOT_FOUND("你找的问题不存在了，要不换一个试试");



    private String message;
    CustomizeErrorCode(String message){
        this.message=message;
    }

    @Override
    public String getmessage() {
        return message;
    }
}
