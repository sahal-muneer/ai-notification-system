package com.notificationsystem.sevice;

import com.notificationsystem.entity.Notification;

public interface DeliveryService {
    void sendNotification(Notification notification);
}
