package com.notificationrestapi.demo.repository;

import com.notificationrestapi.demo.model.NotificationTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationTemplate, Integer> {

}
