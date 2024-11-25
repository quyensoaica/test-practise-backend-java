package com.practise.test.dto.AppData;

public class AppErrorBase {
    private String message;
    private String errorDetail;

    public AppErrorBase() {
    }

    public AppErrorBase(String message, String errorDetail) {
        this.message = message;
        this.errorDetail = errorDetail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }

    @Override
    public String toString() {
        return "AppErrorBase{" +
                "message='" + message + '\'' +
                ", errorDetail='" + errorDetail + '\'' +
                '}';
    }
}
