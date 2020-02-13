package cn.tedu.store.service.exception;


/**
 * 购物车数据已经被删除异常
 */
public class CartNotFoundException extends  ServiceException {


    private static final long serialVersionUID = -1445408879848594743L;

    public CartNotFoundException() {
        super();
    }

    public CartNotFoundException(String message) {
        super(message);
    }

    public CartNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public CartNotFoundException(Throwable cause) {
        super(cause);
    }

    protected CartNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
