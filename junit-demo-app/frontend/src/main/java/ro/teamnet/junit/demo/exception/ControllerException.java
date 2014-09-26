package ro.teamnet.junit.demo.exception;

/**
 * @author adrian.zamfirescu
 * @since 31/07/2014
 */
public class ControllerException extends Exception{

    private int httpStatusToRespond;
    private String message;

    public ControllerException(int httpStatusToRespond, String message) {
        super(message);
        this.httpStatusToRespond = httpStatusToRespond;
        this.message = message;
    }

}
