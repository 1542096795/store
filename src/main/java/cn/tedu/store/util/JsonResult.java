package cn.tedu.store.util;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * 封装响应的JSON结果的类
 *
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
//除去json数据中的null 可以在properties中配置，详情见application.properties文件
public class JsonResult<T> implements Serializable {

    private static final long serialVersionUID = -3998756925917671284L;


    private Integer state;
    private String massage;
    private T data;

    public JsonResult(T data) {
        this.data = data;
    }

    public JsonResult(String massage) {
        this.massage = massage;
    }

    public JsonResult(Throwable throwable) {
        this.massage = throwable.getMessage();
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state, String massage, T data) {
        this.state = state;
        this.massage = massage;
        this.data = data;
    }


    @Override
    public String toString() {
        return "JsonResult{" +
                "state=" + state +
                ", massage='" + massage + '\'' +
                ", data=" + data +
                '}';
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
