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

# Server Common SDK API - DidValidator

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |

<div style="page-break-after: always;"></div>

# Table of Contents

- [1. isValidDid](#1-isvaliddid)
- [2. isValidDidKeyUrl](#2-isvaliddidkeyurl)

# APIs

## 1. isValidDid

### Description
Validates the format of the given DID string.

### Declaration

```java
public static boolean isValidDid(String did)
```

### Parameters

| Name | Type   | Description      |
|------|--------|------------------|
| did  | String | DID string to be verified |

### Returns

| Type    | Description                                |
|---------|--------------------------------------------|
| boolean | true if the DID is valid, otherwise false |

### Usage
```java
String did = "did:omn:abcdefghijklmn";
boolean isValid = DidValidator.isValidDid(did); // returns true if the DID is valid
```

<br>

## 2. isValidDidKeyUrl

### Description
Verifies the format of the given DID Key URL string.

### Declaration

```java
public static boolean isValidDidKeyUrl(String didKeyUrl)
```

### Parameters

| Name      | Type   | Description                          |
|-----------|--------|--------------------------------------|
| didKeyUrl | String | DID Key URL string to be verified    |

### Returns

| Type    | Description                                |
|---------|--------------------------------------------|
| boolean | true if valid, false otherwise             |

### Usage
```java
String didKeyUrl = "did:omn:raon?version=1#pin";
boolean isValid = DidValidator.isValidDidKeyUrl(didKeyUrl); // returns true if the DID Key URL is valid
```
