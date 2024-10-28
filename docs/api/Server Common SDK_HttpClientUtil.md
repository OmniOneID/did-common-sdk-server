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

# Server Common SDK API - HttpClientUtil

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |

<div style="page-break-after: always;"></div>

# Table of Contents

- [1. getData](#1-getdata)
- [2. getData (with Class Type)](#2-getdata-with-class-type)
- [3. postData](#3-postdata)
- [4. postData (with Class Type)](#4-postdata-with-class-type)

    
# APIs

## 1. getData

### Description
 Sends an HTTP GET request to the given URL and returns the response body as a string.

### Declaration

```java
public static String getData(String url) throws IOException, InterruptedException, HttpClientException
```

### Parameters

| Name | Type   | Description  |
|------|--------|--------------|
| url  | String | URL to request |

### Returns

| Type   | Description      |
|--------|------------------|
| String | Response body    |

### Usage
```java
String url = "https://reqres.in/api/users?page=2";
String response = HttpClientUtil.getData(url);
System.out.println("GET Response: " + response);
```

<br>

## 2. getData (with Class Type)

### Description
Sends an HTTP GET request to the given URL and parses the response body into the specified class type.

### Declaration

```java
public static <T> T getData(String url, Class<T> responseType) throws IOException, InterruptedException, HttpClientException
```

### Parameters

| Name         | Type     | Description          |
|--------------|----------|----------------------|
| url          | String   | Requested URL        |
| responseType | Class<T> | Class type to parse the response |

### Returns

| Type | Description         |
|------|---------------------|
| T    | Parsed response body |

### Usage
```java
String url = "https://reqres.in/api/users?page=2";
MyResponse response = HttpClientUtil.getData(url, MyResponse.class);
System.out.println("GET Response: " + response);
```

<br>

## 3. postData

### Description
Sends an HTTP POST request with the specified request body to the given URL and returns the response body as a string.

### Declaration

```java
public static String postData(String url, String requestBody) throws IOException, InterruptedException, HttpClientException
```

### Parameters

| Name        | Type   | Description       |
|-------------|--------|-------------------|
| url         | String | URL to request    |
| requestBody | String | Request body      |

### Returns

| Type   | Description      |
|--------|------------------|
| String | Response body    |

### Usage
```java
String url = "https://reqres.in/api/users";
String requestBody = "{\"name\":\"manager\", \"job\": \"manager\"}";
String response = HttpClientUtil.postData(url, requestBody);
System.out.println("POST Response: " + response);
```

<br>

## 4. postData (with Class Type)

### Description
Sends an HTTP POST request to the given URL with the specified request body and parses the response body into the specified class type.

### Declaration

```java
public static <T> T postData(String url, String requestBody, Class<T> responseType) throws IOException, InterruptedException, HttpClientException
```

### Parameters

| Name        | Type     | Description                        |
|-------------|----------|------------------------------------|
| url         | String   | Request URL                        |
| requestBody | String   | Request Body                       |
| responseType| Class<T> | Class type for parsing the response|

### Returns

| Type | Description           |
|------|-----------------------|
| T    | Parsed Response Body  |

### Usage
```java
String url = "https://reqres.in/api/users";
String requestBody = "{\"name\":\"manager\", \"job\": \"manager\"}";
MyResponse response = HttpClientUtil.postData(url, requestBody, MyResponse.class);
System.out.println("POST Response: " + response);
```
