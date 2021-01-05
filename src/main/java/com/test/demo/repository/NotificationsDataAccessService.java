package com.test.demo.repository;

import com.test.demo.model.NotificationTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository("postgres")
public class NotificationsDataAccessService implements NotificationRepository{
    private JdbcTemplate jdbcTemplate;
    @Autowired
    public NotificationsDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertNotification(UUID id, NotificationTemplate template) {
        String sqlQuery = "INSERT INTO notifications(templateName, body, lang ) " + "VALUES(?, ?, ?)";
         return jdbcTemplate.update(sqlQuery,template.getTemplateName(), template.getBody(), template.getLang());


        //return 0;

    }   

    @Override
    public List<NotificationTemplate> selectAllTemplate() {
        String sqlQuery = "SELECT * FROM notifications";
       return jdbcTemplate.query(sqlQuery, (resultSet, i)-> {
            return new NotificationTemplate(
                   UUID.fromString(resultSet.getString("id")), resultSet.getString("templateName"), resultSet.getString("body"),
                    resultSet.getString("lang"));
        });
    }

    @Override
    public Optional<NotificationTemplate> selectNotificationById(UUID id)
    {
        String sqlQuery = "SELECT * FROM notifications WHERE id = ?";
        NotificationTemplate template = jdbcTemplate.queryForObject(sqlQuery, new Object[]{id}, (resultSet, i) ->{
            UUID templateId = UUID.fromString(resultSet.getString("id"));
            String templateName = resultSet.getString("templateName");
            String body = resultSet.getString("body");
            String lang = resultSet.getString("lang");
            return new NotificationTemplate(id, templateName, body, lang);
        });

        return Optional.ofNullable(template);
    }

    @Override
    public int deleNotificationById(UUID id) {
        String sqlQuery = "DELETE FROM notifications WHERE id = ?";
       return jdbcTemplate.update(sqlQuery, id);
        //return 0;
    }

    @Override
    public int updateNotificationById(UUID id, NotificationTemplate template) {
        String sqlQuery = "UPDATE notifications SET " +
                "templateName = ?, body = ?, lang = ?" +
                "WHERE id =?";
       return jdbcTemplate.update(sqlQuery,template.getTemplateName(), template.getBody(), template.getLang(), template.getId());
        //return 0;
    }
}
