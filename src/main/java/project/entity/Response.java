package project.entity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class Response {


    String errorMessage;
    String errorCode;
    String simpleErrorMessage;
    Boolean hasError;
    Boolean isSuccess;
    Object result;

    public Response( String errorMessage, String errorCode, String simpleErrorMessage, Boolean hasError, Boolean isSuccess, Object result) {
        super();
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
        this.simpleErrorMessage = simpleErrorMessage;
        this.hasError = hasError;
        this.isSuccess = isSuccess;
        this.result = result;
    }


}
