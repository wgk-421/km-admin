package com.km.common.response;

import java.util.Date;

/**
 * Description: 响应参数封装
 *
 * @Author GaoKunW
 * @Date 2021/1/28
 */
public class CommonResultInfo<T> {

    private static final String SUCCESS_MSG = "success";
    private static final String FAILURE_MSG = "failure";
    private static final int SUCCESS_STATUS = 1;
    private static final int FAILURE_STATUS = 0;
    private int status = 1;
    private String message;
    private Date sysDate;
    private T data;
    private String errorCode = "";

    public CommonResultInfo() {
        this.sysDate = new Date();
    }

    public CommonResultInfo(int status, String message, T data) {
        this.sysDate = new Date();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public CommonResultInfo(String message, String errorCode) {
        this.sysDate = new Date();
        this.message = message;
        this.errorCode = errorCode;
    }

    /**
     * 成功返回结果
     *
     * @param data 参数
     */
    public static <T> CommonResultInfo<T> buildSuccess(T data) {
        return new CommonResultInfo<T>(SUCCESS_STATUS, SUCCESS_MSG, data);
    }

    public static <T> CommonResultInfo<T> buildSuccess(String msg) {
        return buildSuccess(null, msg);
    }

    public static <T> CommonResultInfo<T> buildSuccess(T data, String msg) {
        return new CommonResultInfo<T>(SUCCESS_STATUS, msg, data);
    }

    /**
     * 成功返回结果
     *
     * @return 结果
     */
    public static <T> CommonResultInfo<T> success() {
        return buildSuccess(null);
    }

    /**
     *失败且返回错误信息
     *
     * @param msg 错误信息
     * @param <T>
     * @return 结果
     */
    public static <T> CommonResultInfo<T> buildFailed(String msg) {
        return new CommonResultInfo<T>(FAILURE_STATUS, msg, null);
    }

    /**
     * 失败且返回错误信息以及错误代码
     *
     * @param msg 错误信息
     * @param errorCode 错误代码
     * @param <T>
     * @return 结果
     */
    public static <T> CommonResultInfo<T> buildFailed(String msg, String errorCode) {
        return new CommonResultInfo<T>(msg, errorCode);
    }

    /**
     * 失败
     *
     * @param <T>
     * @return 失败结果
     */
    public static <T> CommonResultInfo<T> failed() {
        return buildFailed(FAILURE_MSG);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
