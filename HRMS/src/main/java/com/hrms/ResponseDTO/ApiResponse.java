package com.hrms.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.ZonedDateTime;

@JsonPropertyOrder({"success", "status", "timestamp", "data"})
public class ApiResponse<T> {
    private ZonedDateTime timestamp;
    private int status;
    private boolean success;
    private T data;
    private String message;

    public ApiResponse(int status, boolean success, T data , String message) {
        this.timestamp = ZonedDateTime.now();
        this.status = status;
        this.success = success;
        this.data = data;
        this.message=message;
    }



    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}