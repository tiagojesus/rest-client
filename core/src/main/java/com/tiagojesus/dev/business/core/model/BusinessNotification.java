package com.tiagojesus.dev.business.core.model;

public class BusinessNotification {
    private String code;
    private String description;

    public BusinessNotification() {
    }

    public BusinessNotification(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        String toStringText = "{\"code\":\"%s\", \"description\":\"%s\"}";
        return String.format(toStringText, code, description);
    }
}
