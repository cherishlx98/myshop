package com.lx.gateway;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 响应结果-包含一些信息：是否成功、错误码、错误对象T等
 * @param <T>
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ResponseResult<T> {
    private Boolean success;
    private Integer errorCode;
    private String errorMessage;
    private T data;

    public ResponseResult() {
    }

    /**
     * 静态泛型方法，生成一个任意类型，默认成功返回对象
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> generateSuccessResponse(){
        ResponseResult<T> result = new ResponseResult<T>();
        result.setSuccess(true);
        return result;
    }

    public ResponseResult(Boolean success, Integer errorCode, String errorMessage, T data) {
        this.success = success;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
