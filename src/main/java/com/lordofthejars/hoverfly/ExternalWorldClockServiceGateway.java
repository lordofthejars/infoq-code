package com.lordofthejars.hoverfly;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ExternalWorldClockServiceGateway implements WorldClockServiceGateway {
    private OkHttpClient client;

    public ExternalWorldClockServiceGateway() {
        this.client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();
    }

    @Override
    public LocalTime getTime(String timezone) {
        final Request request = new Request.Builder()
            .url("http://worldclockapi.com/api/json/"+ timezone + "/now")
            .build();

        try (Response response = client.newCall(request).execute()) {
            
            final String content = response.body().string();
            final JsonObject worldTimeObject = Json.parse(content).asObject();
            final String currentTime = worldTimeObject.get("currentDateTime").asString();
            final DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
            LocalDateTime localDateTime = LocalDateTime.parse(currentTime, formatter);
            return localDateTime.toLocalTime();

        } catch(IOException e) {
            throw new IllegalStateException(e);
        }
    }

}