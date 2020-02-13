package cn.tedu.store.service.exception;

/**
 * 非法数据访问，拒绝处理请求，抛出该异常
 * 如：当收货地址和当前用户不匹配时，
 */
public class AccessDeniedException extends  ServiceException {
    private static final long serialVersionUID = -6020719111234911377L;

    public AccessDeniedException() {
        super();
    }

    public AccessDeniedException(String message) {
        super(message);
    }

    public AccessDeniedException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessDeniedException(Throwable cause) {
        super(cause);
    }

    protected AccessDeniedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
