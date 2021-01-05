package com.test.demo.model;

import java.util.UUID;

public class NotificationTemplate {
    private UUID id;
    private String templateName;
    private String body;
    private String lang;

    public NotificationTemplate() {
    }

    public NotificationTemplate(UUID id, String templateName, String body, String lang) {
        this.id = id;
        this.templateName = templateName;
        this.body = body;
        this.lang = lang;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
