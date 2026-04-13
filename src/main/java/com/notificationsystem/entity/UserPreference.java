package com.notificationsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserPreference {

    @Id
    private Long userId;

    private boolean emailEnabled;
    private boolean smsEnabled;
    private boolean pushEnabled;

    // Constructors
    public UserPreference() {}

    // Getters & Setters

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId; }

    public boolean isEmailEnabled() { return emailEnabled; }

    public void setEmailEnabled(boolean emailEnabled) { this.emailEnabled = emailEnabled; }

    public boolean isSmsEnabled() { return smsEnabled; }

    public void setSmsEnabled(boolean smsEnabled) { this.smsEnabled = smsEnabled; }

    public boolean isPushEnabled() { return pushEnabled; }

    public void setPushEnabled(boolean pushEnabled) { this.pushEnabled = pushEnabled; }
}