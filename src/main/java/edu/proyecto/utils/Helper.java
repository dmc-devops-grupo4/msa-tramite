package edu.proyecto.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Helper {
    public static Date getCurrentDate() {
        // Obtener la fecha y hora actual
        LocalDateTime now = LocalDateTime.now();
        // Convertir LocalDateTime a ZonedDateTime
        ZonedDateTime zonedDateTime = now.atZone(ZoneId.systemDefault());
        // Convertir ZonedDateTime a Date
        return Date.from(zonedDateTime.toInstant());
    }
}
