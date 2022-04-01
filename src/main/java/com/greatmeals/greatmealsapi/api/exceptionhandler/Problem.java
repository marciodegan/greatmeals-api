package com.greatmeals.greatmealsapi.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDateTime;

import java.time.OffsetDateTime;
import java.util.List;

@ApiModel("Problema")
@JsonInclude(Include.NON_NULL)
public class Problem {

    @ApiModelProperty(example = "400", position = 1)
    private Integer status;

    @ApiModelProperty(example = "https://greatmeals.com.br/dados-inválidos", position = 2)
    private String type;

    @ApiModelProperty(example = "Dados invalidos", position = 3)
    private String title;

    @ApiModelProperty(example = "Um ou mais campos estão invalidos.", position = 4 )
    private String detail;

    @ApiModelProperty(example = "Um ou mais campos estão invalidos.", position = 5)
    private String userMessage;

    @ApiModelProperty(example = "2022-03-31T21:31:46Z", position = 6)
    private OffsetDateTime timestamp;

    @ApiModelProperty(value = "Lista de objetos ou campos que geraram o erro (opcional)", position = 7)
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

    @ApiModel("ObjetoProblema")
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
