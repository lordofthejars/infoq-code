package com.lordofthejars.hoverfly;

import java.time.LocalTime;

public class SecurityResource {

    private static final LocalTime START_WORK_HOUR = LocalTime.of(7, 59);
    private static final LocalTime END_WORK_HOUR = LocalTime.of(21, 59);

    WorldClockServiceGateway worldClockServiceGateway;

    public boolean isAccessAllowed() {
        final LocalTime currentTime = worldClockServiceGateway.getTime("cet");
        return currentTime.isAfter(START_WORK_HOUR) && currentTime.isBefore(END_WORK_HOUR);
    }

}