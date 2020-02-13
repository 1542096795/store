package cn.tedu.store.service.exception;

/**
 * 用户收获地址超出限制异常，用户收货地址数量默认为10
 */
public class AddresssSizeLimitException extends ServiceException {
    private static final long serialVersionUID = 2855494937637341636L;

    public AddresssSizeLimitException() {
        super();
    }

    public AddresssSizeLimitException(String message) {
        super(message);
    }

    public AddresssSizeLimitException(String message, Throwable cause) {
        super(message, cause);
    }

    public AddresssSizeLimitException(Throwable cause) {
        super(cause);
    }

    protected AddresssSizeLimitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
