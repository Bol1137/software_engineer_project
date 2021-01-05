package com.test.demo.services;

import com.test.demo.model.NotificationTemplate;
import com.test.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationsService {
    private NotificationRepository templateRepo;
    @Autowired
    public NotificationsService(@Qualifier("postgres") NotificationRepository templateRepo){
        this.templateRepo = templateRepo;
    }
    public void addNotification(NotificationTemplate template){
         templateRepo.insertNotification(template);
    }
    public List<NotificationTemplate>getAllTemplate(){
        return templateRepo.selectAllTemplate();
    }
    public int deleteNotification(UUID id){
        return templateRepo.deleNotificationById(id);
    }
    public int updateNotification(UUID id, NotificationTemplate newTemplate){
        return templateRepo.updateNotificationById(id, newTemplate);
    }
    public Optional<NotificationTemplate> getNotificationById(UUID id){
        return templateRepo.selectNotificationById(id);
    }
}
