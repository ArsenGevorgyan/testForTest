package exceptions;

public class BasicAuthException extends RuntimeException {

    static final long serialVersionUID = 3358365142294895833L;

    public BasicAuthException(String strMessage) {
        super(strMessage);
    }

    public String toString() {
        return "This represents exception occurred basic authentication URL creation";
    }
}
