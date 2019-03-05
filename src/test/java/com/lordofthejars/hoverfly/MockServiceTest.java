package com.lordofthejars.hoverfly;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockServiceTest {

    @Mock
    WorldClockServiceGateway worldClockServiceGateway;

    @Test
    public void should_deny_access_if_not_working_hour() {

        // Given

        when(worldClockServiceGateway.getTime("cet")).thenReturn(LocalTime.of(0,0));

        SecurityResource securityResource = new SecurityResource();
        securityResource.worldClockServiceGateway = worldClockServiceGateway;

        // When

        boolean access = securityResource.isAccessAllowed();

        // Then

        assertThat(access).isFalse();

    }
    
}