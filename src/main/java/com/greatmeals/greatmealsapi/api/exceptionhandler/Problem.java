package com.greatmeals.greatmealsapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.LocalDateTime;

import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Problem {

    private Integer status;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private LocalDateTime timestamp;
    private List<Field> fields;


    public Problem(Integer status, String type, String title, String detail) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.timestamp = LocalDateTime.now();
    }

    public Problem(Integer status, String type, String title, String detail, String userMessage) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = LocalDateTime.now();
    }

    public Problem(Integer status, String type, String title, String detail, String userMessage, List<Field> fields) {
        this.status = status;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.timestamp = LocalDateTime.now();
        this.fields = fields;
    }

    public Problem(Integer status, String title) {
        this.status = status;
        this.title = title;
        this.timestamp = LocalDateTime.now();
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

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public List<Field> getFields() {
        return fields;
    }

    public static class Field {
        private String name;
        private String userMessage;

        public Field(String name, String userMessage) {
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
