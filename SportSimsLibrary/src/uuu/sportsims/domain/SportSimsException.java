package uuu.sportsims.domain;

public class SportSimsException extends Exception {

    /**
     * Creates a new instance of <code>SportSimsException</code> without detail
     * message.
     */
    public SportSimsException() {
    }

    /**
     * Constructs an instance of <code>SportSimsException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public SportSimsException(String msg) {
        super(msg);
    }

    public SportSimsException(String message, Throwable cause) {
        super(message, cause);
    }
}
