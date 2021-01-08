package com.notificationrestapi.demo.api;

import com.notificationrestapi.demo.model.NotificationTemplate;
import com.notificationrestapi.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
    NotificationRepository notificationRepository;
    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    @GetMapping
    public List<NotificationTemplate> getAllNotifications(){
        List<NotificationTemplate> allNotifications = notificationRepository.findAll();
        return allNotifications;
    }
    @GetMapping("/{id}")
    public NotificationTemplate getNotificationById(@PathVariable(value = "id") Integer templateId){
        NotificationTemplate notification = notificationRepository.findById(templateId).get();
        return notification;
    }
    @PostMapping
    public NotificationTemplate createNotification(@RequestBody NotificationTemplate notification){
        NotificationTemplate newNotification = notificationRepository.save(notification);
        return newNotification;
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<NotificationTemplate> updateNotification(@PathVariable(value = "id") Integer templateId, @RequestBody NotificationTemplate notificationInfo){
        NotificationTemplate notification = notificationRepository.findById(templateId).get();
        notification.setChannel(notificationInfo.getChannel());
        notification.setTemplate(notificationInfo.getTemplate());
        notification.setLang(notificationInfo.getLang());
        final NotificationTemplate notificationToBeUpdated = notificationRepository.save(notification);
        return ResponseEntity.ok(notificationToBeUpdated);
    }
    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteNotification(@PathVariable(value = "id") Integer templateId){
        NotificationTemplate notification = notificationRepository.findById(templateId).get();
        notificationRepository.delete(notification);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }

}
