package com.test.demo.api;

import com.test.demo.model.NotificationTemplate;
import com.test.demo.services.NotificationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;



@RequestMapping("api/notifications")
@RestController
public class NotificationsController {
    private NotificationsService templateService;
    @Autowired
    public NotificationsController(@RequestBody NotificationsService templateService){
        this.templateService = templateService;
    }
    @PostMapping("/newTemplate")
    public void addNotification(@RequestBody NotificationTemplate newTemplate){
        templateService.addNotification(newTemplate);
    }
    @GetMapping
    public List<NotificationTemplate> readNotification(){
        return templateService.getAllTemplate();
    }
    @GetMapping(path = "{id}")
    public NotificationTemplate readNotificationsById(@PathVariable("id") UUID id){
        return templateService.getNotificationById(id)
                .orElse(null);
    }
    @DeleteMapping(path = "{id}")
    public void deleteNotificationsById(@PathVariable("id") UUID id){
        templateService.deleteNotification(id);
    }
    @PutMapping(path = "/{id}")
    public void updateNotifications(@PathVariable("id") UUID id, @RequestBody NotificationTemplate newtemplate) {
        templateService.updateNotification(id, newtemplate);
    }
}
