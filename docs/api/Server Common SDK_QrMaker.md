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

# Server Common SDK API - QrMaker

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |

<div style="page-break-after: always;"></div>

# Table of Contents

- [1. makeQrImage (Object)](#1-makeqrimage-object)
- [2. makeQrImage (String)](#2-makeqrimage-string)
- [3. makeQrBitMatrix](#3-makeqrbitmatrix)

# APIs

## 1. makeQrImage (Object)

### Description
Converts the given Object into a JSON string, then into a QR code image, and returns it as a QrImageData object.
The width and height of the image are set to default values.
### Declaration

```java
public static QrImageData makeQrImage(final Object data) throws IOException, WriterException
```

### Parameters

| Name | Type   | Description  |
|------|--------|--------------|
| data | Object | Data object to be converted to QR code |

### Returns

| Type       | Description            |
|------------|------------------------|
| QrImageData| QR code image data object |

### Usage
```java
MyObject data = new MyObject();
QrImageData qrImageData = QrMaker.makeQrImage(data);
```

<br>

## 2. makeQrImage (String)

### Description
Converts the given string into a QR code image and returns it as a byte array.

### Declaration

```java
public static byte[] makeQrImage(final String contents, final String format, int width, int height) throws IOException, WriterException
```

### Parameters

| Name     | Type   | Description                             |
|----------|--------|-----------------------------------------|
| contents | String | String to convert to QR code            |
| format   | String | Image format (e.g., PNG)                |
| width    | int    | Image width                             |
| height   | int    | Image height                            |

### Returns

| Type   | Description                       |
|--------|-----------------------------------|
| byte[] | Byte array of the QR code image   |

### Usage
```java
String contents = "Hello, World!";
byte[] qrImage = QrMaker.makeQrImage(contents, "PNG", 300, 300);
```

<br>

## 3. makeQrBitMatrix

### Description
Converts the given string content into a QR code BitMatrix.

### Declaration

```java
public static BitMatrix makeQrBitMatrix(String contents, int width, int height) throws WriterException
```

### Parameters

| Name     | Type   | Description                      |
|----------|--------|----------------------------------|
| contents | String | String to convert to QR code     |
| width    | int    | Bit matrix width                 |
| height   | int    | Bit matrix height                |

### Returns

| Type     | Description               |
|----------|---------------------------|
| BitMatrix| QR code bit matrix        |

### Usage
```java
String contents = "Hello, World!";
BitMatrix bitMatrix = QrMaker.makeQrBitMatrix(contents, 300, 300);
```
