package cn.tedu.store.controller.exception;

/**
 * 文件上传异常是文件上传的基类异常，所有文件上传出现的异常都因该继承它
 */
public class FileUploadException extends RuntimeException{
    private static final long serialVersionUID = 6944880716443965539L;

    public FileUploadException() {
        super();
    }

    public FileUploadException(String message) {
        super(message);
    }

    public FileUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadException(Throwable cause) {
        super(cause);
    }

    protected FileUploadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
