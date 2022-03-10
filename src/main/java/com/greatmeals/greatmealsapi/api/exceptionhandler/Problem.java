package com.greatmeals.greatmealsapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private OffsetDateTime timestamp;
    private List<Object> objects;


    public Problem(Integer status, String type, String title, String detail) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.timestamp = OffsetDateTime.now();
    }

    public Problem(Integer status, String type, String title, String detail, String userMessage) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = OffsetDateTime.now();
    }

    public Problem(Integer status, String type, String title, String detail, String userMessage, List<Object> objects) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = OffsetDateTime.now();
        this.objects = objects;
    }

    public Problem(Integer status, String title) {
        this.status = status;
        this.title = title;
        this.timestamp = OffsetDateTime.now();
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

    public OffsetDateTime getTimestamp() {
        return timestamp;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public static class Object {
        private String name;
        private String userMessage;

        public Object(String name, String userMessage) {
            this.name = name;
            this.userMessage = userMessage;
        }

        public String getName() {
            return name;
        }

        public String getUserMessage() {
            return userMessage;
        }
    }

}
