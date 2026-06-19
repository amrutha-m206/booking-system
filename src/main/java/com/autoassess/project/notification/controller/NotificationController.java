package com.autoassess.project.notification.controller;

import com.autoassess.project.notification.entity.Notification;
import com.autoassess.project.notification.service.NotificationService;
import com.autoassess.project.security.AuthUtil;
import com.autoassess.project.user.entity.User;
import com.autoassess.project.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Notification> getNotifications(){
        String email=authUtil.getCurrentUserEmail();
        User user=userRepository.findByEmail(email).orElseThrow(()-> new RuntimeException("User not found"));

        return notificationService.getNotifications(user.getId());

    }

    @PostMapping("/create")
    public Notification createNotification(@RequestParam String message){
        String email=authUtil.getCurrentUserEmail();
        User user=userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        return notificationService.createNotification(user.getId(),message);
    }

}
