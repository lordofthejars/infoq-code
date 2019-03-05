package com.lordofthejars.diferenciaexample.rest;

import com.lordofthejars.diferencia.api.DiferenciaMode;
import com.lordofthejars.diferencia.core.Diferencia;
import com.lordofthejars.diferencia.junit.DiferenciaExtension;
import com.lordofthejars.diferencia.junit.api.DiferenciaConfig;
import com.lordofthejars.diferencia.junit.api.DiferenciaCore;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.lordofthejars.diferencia.assertj.DiferenciaAssertions.assertThat;

@ExtendWith(DiferenciaExtension.class)
@DiferenciaCore(primary = "http://localhost:9090", candidate = "http://localhost:9092",
    config = @DiferenciaConfig(secondary = "http://localhost:9091", noiseDetection = true, differenceMode = DiferenciaMode.SUBSET))
public class DiferenciaTest {

    private final OkHttpClient client = new OkHttpClient();

    @Test
    public void should_detect_any_possible_regression(Diferencia diferencia) throws IOException {
        
        // Given
        
        final String diferenciaUrl = diferencia.getDiferenciaUrl();

        // When

        Files.lines(Paths.get("src/test/resources/links.txt"))
                .forEach((path) -> sendRequest(diferenciaUrl, path));

        // Then

        assertThat(diferencia)
            .hasNoErrors();
    }

    private void sendRequest(String diferenciaUrl, String path) {
        final Request request = new Request.Builder()
            .addHeader("Content-Type", "application/json")
            .url(diferenciaUrl + path)
            .build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}