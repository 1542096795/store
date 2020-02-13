package cn.tedu.store.controller.exception;


/**
 * 上传文件类型异常
 */
public class FileTypeException extends FileUploadException {
    private static final long serialVersionUID = 5837379515616533670L;

    public FileTypeException() {
        super();
    }

    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileTypeException(Throwable cause) {
        super(cause);
    }

    protected FileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
