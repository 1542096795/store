package cn.tedu.store.service.exception;


/**
 * 删除操作错误异常类
 */
public class DeleteException extends ServiceException {
    private static final long serialVersionUID = 4443953246778514588L;

    public DeleteException() {
        super();
    }

    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeleteException(Throwable cause) {
        super(cause);
    }

    protected DeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
