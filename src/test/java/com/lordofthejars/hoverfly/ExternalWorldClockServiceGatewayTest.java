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

    private static final String OUTPUT = "{\n"
        + "   \"$id\":\"1\",\n"
        + "   \"currentDateTime\":\"2019-03-12T10:54+01:00\",\n"
        + "   \"utcOffset\":\"01:00:00\",\n"
        + "   \"isDayLightSavingsTime\":false,\n"
        + "   \"dayOfTheWeek\":\"Tuesday\",\n"
        + "   \"timeZoneName\":\"Central Europe Standard Time\",\n"
        + "   \"currentFileTime\":131968616698822965,\n"
        + "   \"ordinalDate\":\"2019-71\",\n"
        + "   \"serviceResponse\":null\n"
        + "}";

    @Test
    public void should_get_time_from_external_service() {

        /**hoverfly.simulate(
            SimulationSource.dsl(
                service("http://worldclockapi.com")
                    .get("/api/json/cet/now")
                    .willReturn(success(OUTPUT, "application/json"))
            )
        );**/


        // Given

        final WorldClockServiceGateway worldClockServiceGateway = new ExternalWorldClockServiceGateway();

        // When
        LocalTime time = worldClockServiceGateway.getTime("cet");

        // Then
        Assertions.assertThat(time).isNotNull();

    }

}