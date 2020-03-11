package life.majiang.community.exception;

public class CustomizeException extends RuntimeException {
    private String message;
    private Integer code;
    public CustomizeException(ICustomizeErrorCode errorCode){
        this.code=errorCode.getCode();
        this.message=errorCode.getMessage();
    }

//    public CustomizeException(String message) {
//        this.message=message;
//    }


    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
