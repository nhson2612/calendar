package com.nhson.authservice.application.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhson.authservice.application.model.Client;
import com.nhson.authservice.application.repository.ClientRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.jackson2.SecurityJackson2Modules;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.jackson2.OAuth2AuthorizationServerJackson2Module;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

@Component
public class RegisteredClientInitializer {

    private final ClientRepository clientRepository;
    private final ObjectMapper objectMapper;

    public RegisteredClientInitializer(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModules(SecurityJackson2Modules.getModules(getClass().getClassLoader()));
        this.objectMapper.registerModule(new OAuth2AuthorizationServerJackson2Module());
    }

//    @EventListener(ApplicationReadyEvent.class)
    public void createClientIfNotExists() {
            String clientId = "oidc-client";

        if (clientRepository.findByClientId(clientId).isPresent()) {
            return;
        }

        Client client = new Client();
        client.setId(UUID.randomUUID().toString());
        client.setClientId(clientId);
        client.setClientIdIssuedAt(Instant.now());
        client.setClientSecret("{noop}secret"); // hoặc mã hoá bcrypt
        client.setClientSecretExpiresAt(null); // nếu có thể hết hạn thì thêm
        client.setClientName("OIDC Client");

        // Grant types & auth methods
        client.setAuthorizationGrantTypes(String.join(",", Arrays.asList(
                AuthorizationGrantType.AUTHORIZATION_CODE.getValue(),
                AuthorizationGrantType.REFRESH_TOKEN.getValue()
        )));
        client.setClientAuthenticationMethods(ClientAuthenticationMethod.CLIENT_SECRET_BASIC.getValue());
        // URIs
        client.setRedirectUris("http://localhost:8080/login/oauth2/code/oidc-client");
        client.setPostLogoutRedirectUris("http://localhost:8080/");
        // Scopes
        client.setScopes("openid,profile");
        // ClientSettings
        Map<String, Object> clientSettings = ClientSettings.builder()
                .requireAuthorizationConsent(true)
                .build()
                .getSettings();
        client.setClientSettings(writeMap(clientSettings));
        // TokenSettings
        Map<String, Object> tokenSettings = TokenSettings.builder()
                .accessTokenTimeToLive(Duration.ofMinutes(30))
                .reuseRefreshTokens(true)
                .build()
                .getSettings();
        client.setTokenSettings(writeMap(tokenSettings));
        clientRepository.save(client);
    }

    private String writeMap(Map<String, Object> map) {
        try {
            return objectMapper.writeValueAsString(map);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to write settings map to JSON", ex);
        }
    }
}
