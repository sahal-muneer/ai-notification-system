package com.notificationsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String message;

    private String priority;   // HIGH / LOW

    private String channel;    // EMAIL / SMS / PUSH

    private String status;     // PENDING / SENT / FAILED

    private int retryCount;

    private LocalDateTime createdAt;

    private LocalDateTime sentAt;

    // Constructors
    public Notification() {}

    // Getters & Setters

    public Long getId() { return id; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public String getPriority() { return priority; }

    public void setPriority(String priority) { this.priority = priority; }

    public String getChannel() { return channel; }

    public void setChannel(String channel) { this.channel = channel; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public int getRetryCount() { return retryCount; }

    public void setRetryCount(int retryCount) { this.retryCount = retryCount; }

    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getSentAt() { return sentAt; }

    public void setSentAt(LocalDateTime sentAt) { this.sentAt = sentAt; }
}