package com.eczybytes.springsecsection1.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthenticationEvent {
    @EventListener
    public void onSuccess(AuthenticationSuccessEvent successEvent) {
        successEvent.getAuthentication().getCredentials();
        log.info("Authentication is Success: {}", successEvent.getAuthentication().getName());
    }

    @EventListener
    public void onFailure(AbstractAuthenticationFailureEvent failureEvent) {
        log.error("Authentication is Failed  User : {} due to {}", failureEvent.getAuthentication().getName(),
                failureEvent.getException().getMessage());
    }


}
