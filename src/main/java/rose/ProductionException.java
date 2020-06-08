package rose;

/**
 * @author Gavin
 */
public class ProductionException extends Exception {
    private static final long serialVersionUID = 4268419428323802929L;
    
    public ProductionException() {
        super();
    }
    
    public ProductionException(String message) {
        super(message);
    }
    
    public ProductionException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public ProductionException(Throwable cause) {
        super(cause);
    }
    
    protected ProductionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
