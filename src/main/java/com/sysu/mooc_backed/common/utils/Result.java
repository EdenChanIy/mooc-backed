package com.sysu.mooc_backed.common.utils;

public class Result<T> {
    /*状态码*/
    private Integer status;
    /*提示信息*/
    private String msg;
    /*数据主体*/
    private T data;

    private Result(T data){
        this.status = 200;
        this.msg = "";
        this.data = data;
    }

    private Result(String msg){
        this.status = 403;
        this.msg = msg;
        this.data = (T)"";
    }

    private Result(String msg, int status){
        this.status = status;
        this.msg = msg;
        this.data = (T)"";
    }

    /**
     * 成功时调用
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }

    /**
     * 失败时调用
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg){
        return new Result<T>(msg);
    }

    /**
     * 异常时调用
     * @param msg
     * @param status
     * @param <T>
     * @return
     */
    public static <T> Result<T> error(String msg, int status){
        return new Result<T>(msg, status);
    }

    public Integer getStatus(){
        return status;
    }

    public String getMsg(){
        return msg;
    }

    public T getData(){
        return data;
    }
}
