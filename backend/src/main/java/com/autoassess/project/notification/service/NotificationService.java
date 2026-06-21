package com.autoassess.project.notification.service;

import com.autoassess.project.notification.dto.NotificationResponse;
import com.autoassess.project.notification.entity.Notification;
import com.autoassess.project.notification.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public void createNotification(Long userId,String message){
        Notification notification = new Notification();

        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setCreatedAt(LocalDateTime.now());

        notificationRepository.save(notification);

    }

    public List<NotificationResponse> getNotifications(Long userId){
        List<Notification> notifications= notificationRepository.findByUserId(userId);

        List<NotificationResponse> responses=new ArrayList<>();

        for(Notification notification:notifications){

            NotificationResponse response=new NotificationResponse();
            response.setId(notification.getId());
            response.setMessage(notification.getMessage());
            response.setCreatedAt(notification.getCreatedAt());

            responses.add(response);

        }
        return responses;
    }
}
