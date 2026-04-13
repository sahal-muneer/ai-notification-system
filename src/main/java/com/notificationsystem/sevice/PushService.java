package com.notificationsystem.sevice;

import com.notificationsystem.entity.Notification;
import org.springframework.stereotype.Service;

@Service("PUSH")
public class PushService implements DeliveryService {
    @Override
    public void sendNotification(Notification notification) {
        System.out.println("Sending PUSH: "+notification.getMessage());
    }
}
