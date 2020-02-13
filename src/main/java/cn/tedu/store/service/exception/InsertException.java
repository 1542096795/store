package cn.tedu.store.service.exception;

/**
 * 数据插入数据库异常
 * @author Administrator
 *
 */
public class InsertException extends ServiceException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7122376417883008345L;

	public InsertException() {
    }

    public InsertException(String message) {
        super(message);
    }

    public InsertException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertException(Throwable cause) {
        super(cause);
    }

    public InsertException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
