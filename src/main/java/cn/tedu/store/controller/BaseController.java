package cn.tedu.store.controller;

import cn.tedu.store.controller.exception.*;
import cn.tedu.store.service.exception.*;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cn.tedu.store.util.JsonResult;

/**
 * 控制器类的基类
 */
public class BaseController {

    /**
     * 响应成功的标识码
     */
    public static final Integer SUCCESS = 200;

    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    public JsonResult<Void> handlerException(Throwable e) {
        JsonResult<Void> jr = new JsonResult<>(e);

        if (e instanceof UsernameConflictException) {
            // 4000-用户名冲突，例如用户名已被注册
            jr.setState(4000);
            jr.setMassage(e.getMessage());
        } else if (e instanceof UserNotFountException) {
            // 4001-用户用户不存在异常
            jr.setState(4001);
            jr.setMassage(e.getMessage());
        } else if (e instanceof PasswordNotMatchException) {
            // 4002-登录密码错误异常
            jr.setState(4002);
            jr.setMassage(e.getMessage());
        } else if (e instanceof InsertException) {
            // 5000-插入数据异常
            jr.setState(5000);
            jr.setMassage(e.getMessage());
        } else if (e instanceof UpdateException) {
            // 5000-更新数据异常
            jr.setState(5001);
            jr.setMassage(e.getMessage());
        } else if (e instanceof FileEmptyException) {
            //6000 ——文件上传为空，或者上传文件为空的异常
            jr.setState(6000);
            jr.setMassage(e.getMessage());
        } else if (e instanceof FileSizeException) {
            //6001 ——上传的文件过大
            jr.setState(6001);
            jr.setMassage(e.getMessage());
        } else if (e instanceof FileTypeException) {
            //6002——文件上传的类型不对，如头像因该是图片  不因该上传MP4格式的文件。
            jr.setState(6002);
            jr.setMassage(e.getMessage());
        } else if (e instanceof FileUploadStateException) {
            //6003——文件可能已经被移动或者被删除，不可访问到该文件异常
            jr.setState(6003);
            jr.setMassage(e.getMessage());
        } else if (e instanceof FileUploadIOException) {
            //6004——文件出现读写异常
            jr.setState(6004);
            jr.setMassage(e.getMessage());
        } else if (e instanceof AddresssSizeLimitException) {
            //7000——默认地址添加超出限额异常
            jr.setState(7000);
            jr.setMassage(e.getMessage());
        }
        else if (e instanceof AccessDeniedException) {
            //7000——用户与当前收货地址不匹配异常
            jr.setState(7001);
            jr.setMassage(e.getMessage());
        } else if (e instanceof AddressNotFoundException) {
            //7000——收货地址没有找到异常
            jr.setState(7002);
            jr.setMassage(e.getMessage());
        }else if (e instanceof ProductNotFoundException) {
            //8000——产品信息为找到异常
            jr.setState(8000);
            jr.setMassage(e.getMessage());
        }
        return jr;
    }

}
