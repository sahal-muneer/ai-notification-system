package com.notificationsystem.sevice;

import com.notificationsystem.entity.Notification;
import org.springframework.stereotype.Service;

@Service("EMAIL")
public class EmailService implements DeliveryService{
    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending EMAIL: " + notification.getMessage());

    }
}
