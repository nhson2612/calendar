package com.nhson.userservice.application.model;

import com.nhson.userservice.application.dto.RegisterReq;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "profile", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id"})
})
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String telegramId;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "profile_contact_methods", joinColumns = @JoinColumn(name = "profile_id"))
    @Enumerated(EnumType.STRING)
    @Column(name = "method")
    private Set<ContactMethod> contactMethods = new HashSet<>();
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CustomEvent> customEvents = new HashSet<>();
    @ElementCollection
    @CollectionTable(name = "profile_preferred_festivals", joinColumns = @JoinColumn(name = "profile_id"))
    @Column(name = "festival_code")
    private Set<Long> preferredFestivals = new HashSet<>();
    private boolean reminderEnabled;

    public UserProfile() {}

    public UserProfile(Long id, Long userId, String fullName, String email, String telegramId, Set<ContactMethod> contactMethods, Set<CustomEvent> customEvents, Set<Long> preferredFestivals, boolean reminderEnabled) {
        this.id = id;
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.telegramId = telegramId;
        this.contactMethods = contactMethods;
        this.customEvents = customEvents;
        this.preferredFestivals = preferredFestivals;
        this.reminderEnabled = reminderEnabled;
    }

    public UserProfile(RegisterReq registerReq) {
        this.fullName = registerReq.fullName();
        this.email = registerReq.email();
        this.telegramId = registerReq.telegramId();
        this.reminderEnabled = false;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public Long getUserId() {return userId;}
    public void setUserId(Long userId) {this.userId = userId;}
    public String getFullName() {return fullName;}
    public void setFullName(String fullName) {this.fullName = fullName;}
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTelegramId() {return telegramId;}
    public void setTelegramId(String telegramId) {this.telegramId = telegramId;}
    public Set<ContactMethod> getContactMethods() {return contactMethods;}
    public void setContactMethods(Set<ContactMethod> contactMethods) {this.contactMethods = contactMethods;}
    public Set<CustomEvent> getCustomEvents() {return customEvents;}
    public void setCustomEvents(Set<CustomEvent> customEvents) {this.customEvents = customEvents;}
    public Set<Long> getPreferredFestivals() {return preferredFestivals;}
    public void setPreferredFestivals(Set<Long> preferredFestivals) {this.preferredFestivals = preferredFestivals;}
    public boolean isReminderEnabled() {return reminderEnabled;}
    public void setReminderEnabled(boolean reminderEnabled) {this.reminderEnabled = reminderEnabled;}
}
