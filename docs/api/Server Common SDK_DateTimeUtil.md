---
puppeteer:
    pdf:
        format: A4
        displayHeaderFooter: true
        landscape: false
        scale: 0.8
        margin:
            top: 1.2cm
            right: 1cm
            bottom: 1cm
            left: 1cm
    image:
        quality: 100
        fullPage: false
---


# Server Common SDK API - DateTimeUtil

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |

<div style="page-break-after: always;"></div>
    
# Table of Contents

<!-- TOC tocDepth:2..3 chapterDepth:2..6 -->

- [1. getCurrentUTCTimeString](#1-getcurrentutctimestring)
- [2. addHoursToCurrentTimeString](#2-addhourstocurrenttimestring)
- [3. addToCurrentTimeString](#3-addtocurrenttimestring)
- [4. isExpired](#4-isexpired)
- [5. parseUtcTimeStringToInstant](#5-parseutctimestringtoinstant)

<!-- /TOC -->

# APIs

## 1. getCurrentUTCTimeString

### Description
Returns the current UTC time as a string in ISO 8601 format.

### Declaration

```java
public static String getCurrentUTCTimeString()
```

### Returns

| Type   | Description                           |
|--------|---------------------------------------|
| String | Current UTC time in ISO 8601 format   |

### Usage
```java
String localTimeString = getCurrentUTCTimeString();
System.out.println("Current Local Time: " + localTimeString);
```

<br>

## 2. addHoursToCurrentTimeString

### Description
Adds the specified number of hours to the current time and returns the result as an ISO 8601 formatted string.

### Declaration

```java
public static String addHoursToCurrentTimeString(int hours)
```

### Parameters

| Name  | Type | Description            |
|-------|------|------------------------|
| hours | int  | Number of hours to add |

### Returns

| Type   | Description                                 |
|--------|---------------------------------------------|
| String | Updated time in ISO 8601 format             |

### Usage
```java
String newTimeWithAddedHours = addHoursToCurrentTimeString(5);
System.out.println("Time after adding 5 hours: " + newTimeWithAddedHours);
```

<br>

## 3. addToCurrentTimeString

### Description
Adds a specified unit of time to the current time and returns the result as a string in ISO 8601 format.

### Declaration

```java
public static String addToCurrentTimeString(long amountToAdd, ChronoUnit unit)
```

### Parameters

| Name        | Type      | Description                      |
|-------------|-----------|----------------------------------|
| amountToAdd | long      | Amount of time to add            |
| unit        | ChronoUnit| Unit of time to add (e.g., HOURS)|

### Returns

| Type   | Description                                |
|--------|--------------------------------------------|
| String | Updated time in ISO 8601 format            |

### Usage
```java
String newTimeWithAddedMinutes = addToCurrentTimeString(30, ChronoUnit.MINUTES);
System.out.println("Time after adding 30 minutes: " + newTimeWithAddedMinutes);
```

<br>

## 4. isExpired

### Description
Checks if the specified time has expired. This method compares the current time with the provided expiration time to determine whether the current time is after the expiration time.

### Declaration

```java
public static boolean isExpired(Instant expiredAt)
```

### Parameters

| Name      | Type    | Description                      |
|-----------|---------|----------------------------------|
| expiredAt | Instant | The expiration time to verify    |

### Returns

| Type    | Description                                  |
|---------|----------------------------------------------|
| boolean | true if expired, false otherwise             |

### Usage
```java
Instant futureTime = Instant.now().plusSeconds(60);
boolean isFutureExpired = isExpired(futureTime);
System.out.println("Is future time expired? " + isFutureExpired);

Instant pastTime = Instant.now().minusSeconds(60);
boolean isPastExpired = isExpired(pastTime);
System.out.println("Is past time expired? " + isPastExpired);
```

<br>

## 5. parseUtcTimeStringToInstant

### Description
Parses a string in ISO 8601 format into an Instant object.

### Declaration

```java
public static Instant parseUtcTimeStringToInstant(String timeString)
```

### Parameters

| Name       | Type   | Description                           |
|------------|--------|---------------------------------------|
| timeString | String | ISO 8601 format time string           |

### Returns

| Type    | Description     |
|---------|-----------------|
| Instant | Instant object  |

### Usage
```java
Instant parsedTime = parseUtcTimeStringToInstant(newTimeWithAddedHours);
```
