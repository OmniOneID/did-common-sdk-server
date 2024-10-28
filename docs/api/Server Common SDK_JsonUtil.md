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

# Server Common SDK API - JsonUtil

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |
    
<div style="page-break-after: always;"></div>

# Table of Contents

- [1. serializeAndSort](#1-serializeandsort)
- [2. serializeToJson](#2-serializetojson)
- [3. deserializeFromJson](#3-deserializefromjson)

    
# APIs

## 1. serializeAndSort

### Description
Serialize an object into a JSON string, sort the keys alphabetically, and remove all whitespace. This method is used to serialize the original text for signing in OpenDID.

### Declaration

```java
public static String serializeAndSort(Object obj) throws JsonProcessingException
```

### Parameters

| Name | Type   | Description           |
|------|--------|-----------------------|
| obj  | Object | Object to be serialized |

### Returns

| Type   | Description                                     |
|--------|-------------------------------------------------|
| String | JSON string with sorted keys and no whitespace |

### Usage
```java
MyObject obj = new MyObject();
String jsonString = JsonUtil.serializeAndSort(obj);
```

<br>

## 2. serializeToJson

### Description
Converts any object to a JSON string.

### Declaration

```java
public static String serializeToJson(Object obj) throws JsonProcessingException
```

### Parameters

| Name | Type   | Description       |
|------|--------|-------------------|
| obj  | Object | Object to convert |

### Returns

| Type   | Description   |
|--------|---------------|
| String | JSON String   |

### Usage
```java
MyObject obj = new MyObject();
String jsonString = JsonUtil.serializeToJson(obj);
```

<br>

## 3. deserializeFromJson

### Description
Deserializes a JSON string into an object of the specified class type.

### Declaration

```java
public static <T> T deserializeFromJson(String jsonString, Class<T> clazz) throws JsonProcessingException
```

### Parameters

| Name       | Type     | Description                      |
|------------|----------|----------------------------------|
| jsonString | String   | JSON string                      |
| clazz      | Class<T> | Class type to deserialize        |

### Returns

| Type | Description                 |
|------|-----------------------------|
| T    | Deserialized object         |

### Usage
```java
String jsonString = "{"name":"example", "age":30}";
MyObject obj = JsonUtil.deserializeFromJson(jsonString, MyObject.class);
```
