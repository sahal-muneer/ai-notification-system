package com.notificationsystem.controller;


import com.notificationsystem.entity.Notification;
import com.notificationsystem.sevice.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PostMapping
    public Notification createNotification(@RequestParam Long userId, @RequestParam String message){
        return notificationService.createNotification(userId, message);
    }

    @GetMapping("/user/{userId}")
    public List<Notification>getUserNotifications(@PathVariable Long userId){
        return notificationService.getUserNotifications(userId);
    }

    @GetMapping("/pending")
    public List<Notification> getPendingNotification(){
        return notificationService.getPendingNotification();
    }
}
