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

# Server Common SDK API - HexUtil

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |
    
<div style="page-break-after: always;"></div>

# Table of Contents

- [1. toHexString](#1-tohexstring)

# APIs

## 1. toHexString

### Description
Converts a byte array to a hexadecimal string.

### Declaration

```java
public static String toHexString(byte[] bytes)
```

### Parameters

| Name  | Type    | Description           |
|-------|---------|-----------------------|
| bytes | byte[]  | Byte array to convert |

### Returns

| Type   | Description                                      |
|--------|--------------------------------------------------|
| String | The value of the byte array converted to a hexadecimal string |

### Usage
```java
byte[] data = {0x01, 0x2A, 0x3F};
String hex = HexUtil.toHexString(data); // returns "012a3f"
```
