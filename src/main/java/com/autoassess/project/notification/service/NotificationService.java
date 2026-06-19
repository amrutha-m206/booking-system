package com.autoassess.project.notification.service;

import com.autoassess.project.notification.entity.Notification;
import com.autoassess.project.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public Notification createNotification(Long userId,String message){
        Notification notification = new Notification();

        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());

        return notificationRepository.save(notification);

    }

    public List<Notification> getNotifications(Long userId){
        return notificationRepository.findByUserId(userId);
    }
}
