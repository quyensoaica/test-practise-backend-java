package com.practise.test.dto.AppData;

public class AppResponseBase {
    private int status;
    private boolean success;
    private String message;
    private Object data;
    private AppErrorBase error;

    public AppResponseBase() {
    }

    public AppResponseBase(int status, boolean success, String message, Object data, AppErrorBase error) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public AppErrorBase getError() {
        return error;
    }

    public void setError(AppErrorBase error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "AppResponseBase{" +
                "status=" + status +
                ", success=" + success +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", error=" + error +
                '}';
    }
}
