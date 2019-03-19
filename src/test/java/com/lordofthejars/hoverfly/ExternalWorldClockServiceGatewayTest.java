package com.lordofthejars.hoverfly;

import io.specto.hoverfly.junit5.HoverflyExtension;
import io.specto.hoverfly.junit5.api.HoverflySimulate;
import java.time.LocalTime;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(HoverflyExtension.class)
@HoverflySimulate(source = 
    @HoverflySimulate.Source(value = "target/hoverfly/simulation.json", 
                             type = HoverflySimulate.SourceType.FILE),
                             enableAutoCapture=true)
public class ExternalWorldClockServiceGatewayTest {

    @Test
    public void should_get_time_from_external_service() {
        
        // Given

        final WorldClockServiceGateway worldClockServiceGateway = new ExternalWorldClockServiceGateway();

        // When
        LocalTime time = worldClockServiceGateway.getTime("cet");

        // Then
        Assertions.assertThat(time).isNotNull();

    }

}