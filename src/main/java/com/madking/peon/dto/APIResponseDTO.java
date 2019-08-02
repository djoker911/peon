package com.madking.peon.dto;

public class APIResponseDTO<T> {
    private boolean success;
    private int status;
    private String msg;
    private T data;

    public APIResponseDTO() {
        this.success = Boolean.FALSE.booleanValue();
        this.msg = null;
        this.data = null;
        this.status = 500;
    }

    public APIResponseDTO(boolean success, int code, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
        this.status = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean status) {
        this.success = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int code) {
        this.status = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
