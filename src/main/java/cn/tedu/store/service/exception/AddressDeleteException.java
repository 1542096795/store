package cn.tedu.store.service.exception;


/**
 * 收获地址删除异常，删除了多次或者删除失败
 */
public class AddressDeleteException extends ServiceException{

    private static final long serialVersionUID = -7299066053366727321L;

    public AddressDeleteException() {
        super();
    }

    public AddressDeleteException(String message) {
        super(message);
    }

    public AddressDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddressDeleteException(Throwable cause) {
        super(cause);
    }

    protected AddressDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
