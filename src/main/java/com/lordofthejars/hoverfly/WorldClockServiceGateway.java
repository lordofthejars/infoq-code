package com.lordofthejars.hoverfly;

import java.time.LocalTime;

public interface WorldClockServiceGateway {
    LocalTime getTime(String tiemzone);
}