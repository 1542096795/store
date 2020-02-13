package cn.tedu.store.service.exception;

/**
 * 用户名不存在异常
 */
public class UserNotFountException extends ServiceException {
    private static final long serialVersionUID = 8787719637896008642L;

    public UserNotFountException() {
        super();
    }

    public UserNotFountException(String message) {
        super(message);
    }

    public UserNotFountException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFountException(Throwable cause) {
        super(cause);
    }

    protected UserNotFountException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
