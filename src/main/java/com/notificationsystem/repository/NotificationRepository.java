package com.notificationsystem.repository;

import com.notificationsystem.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // Get all notifications of a user
    List<Notification> findByUserId(Long userId);

    // Get notifications by status (PENDING, SENT, FAILED)
    List<Notification> findByStatus(String status);
}