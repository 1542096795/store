package cn.tedu.store.service.exception;

/**
 * 用户名冲突异常  例如重复注册
 * 
 * @author Administrator
 *
 */
public class UsernameConflictException extends ServiceException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6213438263568123440L;

	public UsernameConflictException() {
		super();
	}

	public UsernameConflictException(String message) {
		super(message);
	}

	public UsernameConflictException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsernameConflictException(Throwable cause) {
		super(cause);
	}

	protected UsernameConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
