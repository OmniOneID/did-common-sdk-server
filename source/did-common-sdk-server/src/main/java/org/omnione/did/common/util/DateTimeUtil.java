package org.omnione.did.common.util;

import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

/**
 * Utility class for date and time operations.
 * This class provides methods for working with date and time in UTC format.
 */

public class DateTimeUtil {

    /**
     * Return the current UTC time as a string in ISO 8601 format.
     * This method returns the current time in UTC as a string in the format "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'".
     * @return The current UTC time as a string in ISO 8601 format.
     */
    public static String getCurrentUTCTimeString() {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC"));
        ZonedDateTime localNow = utcNow.withZoneSameInstant(ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return localNow.format(formatter);
    }

    /**
     * Return the current UTC time as a ZonedDateTime object.
     * This method returns the current time in UTC as a ZonedDateTime object.
     * @return The current UTC time as a ZonedDateTime object.
     */
    public static ZonedDateTime getCurrentUTCTime() {
        return ZonedDateTime.now(ZoneId.of("UTC"));
    }

    /**
     * Add the specified number of hours to the current time and return the result as a string in ISO 8601 format.
     * @param hours The number of hours to add to the current time.
     * @return The resulting time as a string in ISO 8601 format.
     */
    public static String addHoursToCurrentTimeString(int hours) {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC")).plus(hours, ChronoUnit.HOURS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return utcNow.format(formatter);
    }

    /**
     * Add the specified number of years to the current time and return the result as a ZonedDateTime object.
     * @param years The number of years to add to the current time.
     * @return The resulting time as a ZonedDateTime object.
     */
    public static ZonedDateTime addYearsToCurrentTime(int years) {
        return ZonedDateTime.now(ZoneId.of("UTC")).plusYears(years);
    }

    /**
     * Add the specified amount of time to the current time and return the result as a string in ISO 8601 format.
     * @param amountToAdd The amount of time to add to the current time.
     * @param unit The unit of time to add (e.g., ChronoUnit.HOURS, ChronoUnit.DAYS).
     * @return The resulting time as a string in ISO 8601 format.
     */
    public static String addToCurrentTimeString(long amountToAdd, ChronoUnit unit) {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC")).plus(amountToAdd, unit);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return utcNow.format(formatter);
    }

    /**
     * Checks if the given Instant has expired.
     *
     * This method compares the current time (Instant.now()) with the provided
     * expiration time (expiredAt) and determines if the current time is after
     * the expiration time.
     *
     * @param expiredAt The expiration time to check against.
     * @return True if the expiration time has passed, false otherwise.
     *
     */
    public static boolean isExpired(Instant expiredAt) {
        Instant now = Instant.now();

        if (now.isAfter(expiredAt)) {
            return true;
        };
        return false;
    }

    /**
     * Parse a UTC date time string to an Instant.
     * This method parses a UTC date time string in ISO 8601 format and returns the corresponding Instant object.
     * @param timeString The UTC date time string to parse.
     * @return The Instant object representing the parsed date time.
     */
    public static Instant parseUtcTimeStringToInstant(String timeString) {
        return Instant.parse(timeString);
    }


    /**
     * Convert a UTC date time string to a formatted string.
     *
     * This method takes a UTC date time string as input and returns the date time string
     * in the format "yyyy-MM-dd HH:mm:ss".
     * @param utcDateTime The UTC date time string to convert.
     * @return The formatted date time string.
     */
    public static String convertUtcToFormattedString(String utcDateTime) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(utcDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        return zonedDateTime.format(formatter);
    }

    /**
     * Convert a UTC date time string to a formatted string.
     *
     * This method takes a UTC date time string and a format string as input
     * and returns the date time string in the specified format.
     * @param utcDateTime The UTC date time string to convert.
     * @param format The format string to use for the conversion.
     */
    public static String convertUtcToFormattedString(String utcDateTime, String format) {
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(utcDateTime);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);

        return zonedDateTime.format(formatter);
    }

    /**
     * Check if the second date-time string is later than the first date-time string.
     *
     * This method takes two date-time strings in UTC format and returns true if the second
     * date-time is later than the first date-time.
     * @param firstUtcDateTime The first date-time string in UTC format.
     * @param secondUtcDateTime The second date-time string in UTC format.
     * @return True if the second date-time is later than the first date-time, false otherwise.
     *
     */
    public static boolean isSecondDateTimeLater(String firstUtcDateTime, String secondUtcDateTime) {
        try {
            ZonedDateTime firstDateTime = ZonedDateTime.parse(firstUtcDateTime);
            ZonedDateTime secondDateTime = ZonedDateTime.parse(secondUtcDateTime);

            return secondDateTime.isAfter(firstDateTime);
        } catch (DateTimeParseException e) {
            System.out.println("Error: One of the date-time strings is not valid: " + e.getMessage());
            throw new CommonSdkException(ErrorCode.INVALID_DATE_TIME);
        }
    }

    /**
     * Return the maximum possible UTC time as an Instant.
     *
     * This method provides the Instant representing the farthest future date, which
     * is useful to represent a non-expiring or permanent time.
     *
     * @return Instant representing the maximum UTC time.*
     */
    public static Instant getMaxUTCTime() {
        return LocalDateTime.of(9999, 12, 31, 23, 59, 59).toInstant(ZoneOffset.UTC);
    }

    public static String addMinutesToCurrentTimeString(int minutes) {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC")).plus(minutes, ChronoUnit.MINUTES);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return utcNow.format(formatter);
    }

    public static String addSecondsToCurrentTimeString(int seconds) {
        ZonedDateTime utcNow = ZonedDateTime.now(ZoneId.of("UTC")).plus(seconds, ChronoUnit.SECONDS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'");
        return utcNow.format(formatter);
    }
}
