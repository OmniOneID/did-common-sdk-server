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

# Server Common SDK API - DidUtil

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |

<div style="page-break-after: always;"></div>

# Table of Contents

- [1. extractDid](#1-extractdid)

# APIs

## 1. extractDid

### Description
Extracts a DID from the given DID URL by removing query or fragment components.

### Declaration

```java
public static String extractDid(String didKeyUrl)
```

### Parameters

| Name      | Type   | Description                                                                 |
|-----------|--------|-----------------------------------------------------------------------------|
| didKeyUrl | String | Full DID Key URL string including query parameters or fragments              |

### Returns

| Type   | Description                                                 |
|--------|-------------------------------------------------------------|
| String | Pure DID without query parameters or fragments              |

### Usage
```java
String didKeyUrl = "did:example:123456?version=2#pin";
String did = DidUtil.extractDid(didKeyUrl); // returns "did:example:123456"
```
