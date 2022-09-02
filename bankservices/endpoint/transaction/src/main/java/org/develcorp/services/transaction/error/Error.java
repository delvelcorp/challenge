package org.develcorp.services.transaction.error;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Error implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonProperty("code")
    private String code;

    @JsonProperty("detail")
    private String detail;

    public Error(String code, String detail) {
        this.code = code;
        this.detail = detail;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code='" + code + '\'' +
                ", detail='" + detail + '\'' +
                '}';
    }
}
