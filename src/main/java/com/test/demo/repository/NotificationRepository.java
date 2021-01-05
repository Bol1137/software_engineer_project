package com.test.demo.repository;

import com.test.demo.model.NotificationTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository {
    int insertNotification(UUID id, NotificationTemplate template);
    default int insertNotification(NotificationTemplate template){
        UUID id = UUID.randomUUID();
       return insertNotification(id, template);
    }
    List<NotificationTemplate> selectAllTemplate();
    Optional<NotificationTemplate>selectNotificationById(UUID id);
    int deleNotificationById(UUID id);
    int updateNotificationById(UUID id, NotificationTemplate template);
}
