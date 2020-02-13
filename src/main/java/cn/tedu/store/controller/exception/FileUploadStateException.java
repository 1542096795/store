package cn.tedu.store.controller.exception;

/**
 * 文件可能已经被移动或者被删除，不可访问到该文件异常
 */
public class FileUploadStateException extends FileUploadException {
    private static final long serialVersionUID = 1868896696423942465L;

    public FileUploadStateException() {
        super();
    }

    public FileUploadStateException(String message) {
        super(message);
    }

    public FileUploadStateException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileUploadStateException(Throwable cause) {
        super(cause);
    }

    protected FileUploadStateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
