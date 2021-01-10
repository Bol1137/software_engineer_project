package com.notificationrestapi.demo.api;

import com.notificationrestapi.demo.model.NotificationTemplate;
import com.notificationrestapi.demo.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("api/notification")
public class NotificationController {
  private static final String channelTemplate = " %s";
  private static   final String SmsTemplate ="Hey %s, your booking for item %S is complete successfully";
  private static final String mailTemplate = "Hey %s, Thank you for signing up to my weekly newsletter.";
  private static   final String lang = "English";
  private   final AtomicLong counter = new AtomicLong();



    NotificationRepository notificationRepository;
    @Autowired
    public NotificationController(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @GetMapping
    public NotificationTemplate getNotification(@RequestParam(value = "channel", defaultValue = "empty") String channel
    , @RequestParam(value = "name", defaultValue = "user")String name, @RequestParam(value = "item", defaultValue = "x") String item){

    if(channel.equalsIgnoreCase("sms")) {
        return new NotificationTemplate((int) counter.incrementAndGet(), String.format(channelTemplate, channel), String.format(SmsTemplate, name, item), lang);
        
    }else
        return new NotificationTemplate((int)counter.incrementAndGet(), String.format(channelTemplate, channel),String.format(mailTemplate, name), lang);

    }
    /*
    @GetMapping
    public List<NotificationTemplate> getAllNotifications(){

        List<NotificationTemplate> allNotifications = notificationRepository.findAll();
        return allNotifications;
    }

     */
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
