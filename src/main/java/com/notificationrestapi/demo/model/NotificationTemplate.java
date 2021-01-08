package com.notificationrestapi.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "notificationTemplate")
public class NotificationTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer templateId;
    @Column(name = "channel", nullable = false)
    private String channel;
    @Column(name = "template", nullable = false)
    private String template;
    @Column(name = "lang", nullable = false)
    private String lang;

    public NotificationTemplate() {
    }

    public NotificationTemplate(Integer templateId, String channel, String template, String lang) {
        this.templateId = templateId;
        this.channel = channel;
        this.template = template;
        this.lang = lang;
    }

    public Integer getTemplateId() {
        return templateId;
    }

    public void setTemplateId(Integer templateId) {
        this.templateId = templateId;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    @Override
    public String toString(){
        return "Template [templateId=" + templateId + ", channel=" + channel + ",template=" + template +
                ",lang=" + lang + "]";
    }
}
