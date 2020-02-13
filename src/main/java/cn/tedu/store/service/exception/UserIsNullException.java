package cn.tedu.store.service.exception;

/**
 * 用户传入数据异常
 * 
 * @author Administrator
 *
 */
public class UserIsNullException extends ServiceException {

	/**
	 *
	 */
	private static final long serialVersionUID = 8742668100842570487L;

	public UserIsNullException() {
		super();
	}

	public UserIsNullException(String message) {
		super(message);
	}

	public UserIsNullException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserIsNullException(Throwable cause) {
		super(cause);
	}

	protected UserIsNullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
