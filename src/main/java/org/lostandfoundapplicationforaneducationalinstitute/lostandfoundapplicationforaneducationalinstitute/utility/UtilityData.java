package org.lostandfoundapplicationforaneducationalinstitute.lostandfoundapplicationforaneducationalinstitute.utility;

import java.time.LocalDate;
import java.util.UUID;

public class UtilityData {

    public static LocalDate generateCurrentDate() {
        return LocalDate.now();
    }

    public static String generateItemId() {
        return "I -"+ UUID.randomUUID();
    }

    public static String generateUserId() {
        return "U -"+ UUID.randomUUID();
    }

    public static String generateRequestId() {
        return "R -"+ UUID.randomUUID();
    }
}
