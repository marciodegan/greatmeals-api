package com.greatmeals.greatmealsapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;

    private String userMessage;
    private LocalDateTime timestamp;

    public Problem(Integer status, String type, String title, String detail) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
    }

    public Problem(Integer status, String type, String title, String detail, String userMessage, LocalDateTime timestamp) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = timestamp;
    }

    public Problem(Integer status, String title) {
        this.status = status;
        this.title = title;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getStatus() {
        return status;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
