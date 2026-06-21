package com.autoassess.project.notification.repository;

import com.autoassess.project.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findByUserId(Long userId);

}
