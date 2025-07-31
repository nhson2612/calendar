package com.nhson.userservice.rabbitmq.listenter;

import com.nhson.userservice.application.repository.UserProfileRepository;
import com.nhson.userservice.rabbitmq.event.UserAccountCreationFailedEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ProfileListener {

    private static final Log log = LogFactory.getLog(ProfileListener.class);
    private final UserProfileRepository userProfileRepository;

    public ProfileListener(UserProfileRepository userProfileRepository) {
        this.userProfileRepository = userProfileRepository;
    }

    @RabbitListener(queues = "${custom.rabbitmq.queues.userFailed}")
    public void rollbackProfile(UserAccountCreationFailedEvent event) {
        Long profileId = event.getProfileId();
        try {
            userProfileRepository.deleteById(profileId);
            log.info("✅ Rollback thành công hồ sơ: " + profileId);
        } catch (Exception e) {
            log.error("❌ Không thể rollback hồ sơ: " + profileId, e);
        }
    }
}