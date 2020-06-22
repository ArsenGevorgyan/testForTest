package exceptions;

public class WebDriverFactoryException extends RuntimeException {

    static final long serialVersionUID = -7881750507138606899L;

    public WebDriverFactoryException(String strMessage) {
        super(strMessage);
    }

    public String toString() {
        return "This represents exception occurred during creation of WebDriver object by WebDriverFactory class";
    }
}
