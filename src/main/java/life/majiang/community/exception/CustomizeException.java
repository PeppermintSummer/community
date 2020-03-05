package life.majiang.community.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.message=errorCode.getmessage();
    }

    public CustomizeException(String message) {
        this.message=message;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
