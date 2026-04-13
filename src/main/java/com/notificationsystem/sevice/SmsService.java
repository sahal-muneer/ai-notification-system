package com.notificationsystem.sevice;

import com.notificationsystem.entity.Notification;
import org.springframework.stereotype.Service;

@Service("SMS")
public class SmsService implements DeliveryService{

    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending SMS: " + notification.getMessage());
    }
}
