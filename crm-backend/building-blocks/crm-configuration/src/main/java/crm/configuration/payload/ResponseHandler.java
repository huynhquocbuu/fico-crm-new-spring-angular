package crm.configuration.payload;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    public static <T> ResponseEntity<ResponseWrapper<T>> success(T obj) {
        ResponseWrapper<T> wrapper = new ResponseWrapper<T>();
        wrapper.setData(obj);
        //wrapper.setStatus("Success");
        wrapper.setErrorCode("");
        wrapper.setErrorMessage("");
        return new ResponseEntity (wrapper, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ResponseWrapper<T>> responseUnSuccess(T obj, String errorCode, String errorMessage) {
        ResponseWrapper<T> wrapper = new ResponseWrapper<T>();
        wrapper.setData(obj);
        //wrapper.setStatus("UnSuccess");
        wrapper.setErrorCode(errorCode);
        wrapper.setErrorMessage(errorMessage);
        return new ResponseEntity (wrapper, HttpStatus.OK);
    }
    public static <T> ResponseEntity<ResponseWrapper<T>> responseErr(T obj, String errorCode, String errorMessage)
    {
        ResponseWrapper<T> wrapper = new ResponseWrapper<T>();
        wrapper.setData(obj);
        //wrapper.setStatus("Error");
        wrapper.setErrorCode(errorCode);
        wrapper.setErrorMessage(errorMessage);
        return new ResponseEntity (wrapper, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<ResponseWrapper<String>> responseInternalServerErr(String errorCode, String errorMessage)
    {
        ResponseWrapper<String> wrapper = new ResponseWrapper<>();
        wrapper.setData("");
        //wrapper.setStatus("Error");
        wrapper.setErrorCode(errorCode);
        wrapper.setErrorMessage(errorMessage);
        return new ResponseEntity (wrapper, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<ResponseWrapper<String>> responseAccessDeniedErr(String errorCode, String errorMessage)
    {
        ResponseWrapper<String> wrapper = new ResponseWrapper<>();
        wrapper.setData("");
        //wrapper.setStatus("Error");
        wrapper.setErrorCode(errorCode);
        wrapper.setErrorMessage(errorMessage);
        return new ResponseEntity (wrapper, HttpStatus.FORBIDDEN);
    }
}
