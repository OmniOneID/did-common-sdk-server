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

Common Util SDK Error
==

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |

<div style="page-break-after: always;"></div>

# Table of Contents

- [Model](#model)
  - [Error Response](#error-response)
- [Error Code](#error-code)
  - [1. Common Util SDK](#1-common-util-sdk)
    - [1-1. Data Processing (00100 ~ 00199)](#1-1-data-processing-00100--00199)
    - [1-2. QR CODE (00200 ~ 00299)](#1-2-qr-code-00200--00299)
    - [1-3. HTTP Client (00300 ~ 00399)](#1-3-http-client-00300--00399)

# Model

## Error Response

### Description

```
Error struct for Common Util SDK. It has code and message pair.
Code starts with SSDKUTL.
```

### Declaration

```java
public class ErrorResponse {
    private final String code;
    private final String message;
}
```

### Property

| Name    | Type   | Description                        | **M/O** | **Note** |
|---------|--------|------------------------------------|---------| -------- |
| code    | String | Error code. It starts with SSDKUTL | M       |          |
| message | String | Error description                  | M       |          |

<br>

# Error Code

## 1. Common Util SDK

### 1-1. Data Processing (00100 ~ 00199)

| Error Code   | Error Message                      | Description | Action Required                                                                   |
|--------------|-------------------------------------|-------------|-----------------------------------------------------------------------------------|
| SSDKUTL00100 | Failed to Json serialize.           | -           | Check input data structure and ensure all fields are serializable.                |
| SSDKUTL00101 | Failed to Json serialize and sort.  | -           | Verify the sorting criteria and ensure all fields have comparable values.         |
| SSDKUTL00102 | Failed to Json deserialize.         | -           | Validate the JSON string format and ensure it matches the target object structure.|
| SSDKUTL00103 | Invalid date time format.           | -           | Check the date-time string and ensure it follows the expected format (e.g., ISO 8601).|

### 1-2. QR CODE (00200 ~ 00299)

| Error Code   | Error Message                                    | Description | Action Required                                                                    |
|--------------|--------------------------------------------------|-------------|------------------------------------------------------------------------------------|
| SSDKUTL00200 | Failed to generate QR code image.                | -           | Verify the input data for QR code generation and check available memory.           |
| SSDKUTL00201 | Failed to write QR code image to output stream.  | -           | Check write permissions and available disk space. Ensure output stream is open.    |
| SSDKUTL00202 | Failed to encode QR code content.                | -           | Validate the content size and character set. Ensure it's within QR code capacity.  |

### 1-3. HTTP Client (00300 ~ 00399)

| Error Code   | Error Message                | Description | Action Required                                                                         |
|--------------|------------------------------|-------------|-----------------------------------------------------------------------------------------|
| SSDKUTL00300 | Failed to send HTTP request. | -           | Check network connectivity, verify URL, and ensure proper request formation (headers, body, etc.).|