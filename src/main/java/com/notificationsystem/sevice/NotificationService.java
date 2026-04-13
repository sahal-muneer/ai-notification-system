package com.notificationsystem.sevice;

import com.notificationsystem.entity.Notification;
import com.notificationsystem.repository.NotificationRepository;
import com.notificationsystem.util.AIDecisionEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private AIDecisionEngine aiDecisionEngine;

    @Autowired
    private Map<String,DeliveryService> deliveryServiceMap;

    public Notification createNotification(Long userId, String message){

        Notification notification = new Notification();
        notification.setUserId(userId);
        notification.setMessage(message);

//        notification.setPriority("Low");
//        notification.setChannel("Email");
//        String priority = aiDecisionEngine.decisionPriority(message);
//        String channel= aiDecisionEngine.decideChannel(message);

        Map<String, String> aiResult = aiDecisionEngine.getDecision(message);

        String priority = aiResult.get("priority");
        String channel = aiResult.get("channel");


        notification.setPriority(priority);
        notification.setChannel(channel);

        notification.setStatus("Pending");
        notification.setRetryCount(0);
        notification.setCreatedAt(LocalDateTime.now());
        DeliveryService deliveryService = deliveryServiceMap.get(notification.getChannel());

        if (deliveryService != null) {
            deliveryService.sendNotification(notification);
            notification.setStatus("SENT");
            notification.setSentAt(LocalDateTime.now());
        } else {
            notification.setStatus("FAILED");
        }

        return notificationRepository.save(notification);




    }


    public List<Notification> getPendingNotification(){
        return notificationRepository.findByStatus("Pending");
    }

    public List<Notification>getUserNotifications(Long userId){
        return notificationRepository.findByUserId(userId);
    }


}
