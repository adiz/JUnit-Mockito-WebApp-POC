package ro.teamnet.junit.demo.exception;

/**
 * @author adrian.zamfirescu
 * @since 03/08/2014
 */
public class ServiceException extends Exception{

    private String message;

    public ServiceException(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

}
