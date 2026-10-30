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

# Server Common SDK API

- Topic: Server Common SDK API
- Author: OmniOne
- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | Changes                 |
| ------- | ---------- | ----------------------- |
| v1.0.0  | 2024-09-02 | Initial Draft           |


<div style="page-break-after: always;"></div>

# Table of Contents
- [1. APIs - Utils](#1-apis---utils)
    - [1.1 DateTimeUtil](#11-datetimeutil)
    - [1.2 DidUtil](#12-didutil)
    - [1.3 DidValidator](#13-didvalidator)
    - [1.4 GsonSerializationUtil](#14-gsonserializationutil)
    - [1.5 HexUtil](#15-hexutil)
    - [1.6 HttpClientUtil](#16-httpclientutil)
    - [1.7 IdGenerator](#17-idgenerator)
    - [1.8 JsonUtil](#18-jsonutil)
    - [1.9 NonceGenerator](#19-noncegenerator)
    - [1.10 QrMaker](#110-qrmaker)
    - [1.11 QrUtil](#111-qrutil)
    - [1.12 ValidationUtil](#112-validationutil)
    - [1.13 BaseMultibaseUtil](#113-basemultibaseutil)
    - [1.14 BaseDigestUtil](#114-basedigestutil)
    - [1.15 BaseCoreVcUtil](#115-basecorevcutil)
    - [1.16 BaseBlockChainUtil](#116-baseblockchainutil)
    - [1.17 BaseCryptoUtil](#117-basecryptoutil)
    - [1.18 BaseCoreDidUtil](#118-basecoredidutil)
    - [1.19 BaseAccessTokenUtil](#119-baseaccesstokenutil)
    - [1.20 BaseWalletUtil](#120-basewalletutil)
- [2. APIs - Domains](#2-apis---domains)
    - [2.1 DidDocCryptoHelper](#21-diddoccryptohelper)
    - [2.2 EcdhCryptoHelper](#22-ecdhcryptohelper)
    - [2.3 SignatureCryptoHelper](#23-signaturecryptohelper)
- [3. APIs - Services](#3-apis---services)
    - [3.1 EcdhService](#31-ecdhservice)
    - [3.2 SignatureService](#32-signatureservice)
    - [3.3 TokenService](#33-tokenservice)
    - [3.4 VcService](#34-vcservice)
    - [3.5 WalletService](#35-walletservice)
- [4. Data Models](#4-data-models)
- [5. Enumerator](#5-enumerator)
- [6. APIs - Exceptions](#6-apis---exceptions)
    - [6.1 CommonSdkException](#61-commonsdkexception)
    - [6.2 HttpClientException](#62-httpclientexception)
    - [6.3 ErrorCode](#63-errorcode)
    - [6.4 ErrorResponse](#64-errorresponse)
- [7. Reference Classes](#7-reference-classes)

# 1. APIs - Utils

## 1.1 DateTimeUtil

## 1.1.1 getCurrentUTCTimeString

### Class Name
`DateTimeUtil`

### Function Name
`getCurrentUTCTimeString`

### Function Introduction
`Returns the current UTC time as a string in ISO 8601 format ("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'").`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Current UTC time string in ISO 8601 format | M | |

### Function Declaration
```java
static String getCurrentUTCTimeString()
```

### Function Usage
```java
String utcTime = DateTimeUtil.getCurrentUTCTimeString();
```
<br>

## 1.1.2 getCurrentUTCTime

### Class Name
`DateTimeUtil`

### Function Name
`getCurrentUTCTime`

### Function Introduction
`Returns the current UTC time as a ZonedDateTime object.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| ZonedDateTime | Current UTC time object | M | |

### Function Declaration
```java
static ZonedDateTime getCurrentUTCTime()
```

### Function Usage
```java
ZonedDateTime utcTime = DateTimeUtil.getCurrentUTCTime();
```
<br>

## 1.1.3 addHoursToCurrentTimeString

### Class Name
`DateTimeUtil`

### Function Name
`addHoursToCurrentTimeString`

### Function Introduction
`Returns the result of adding the specified hours to the current time as a string in ISO 8601 format.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| hours | int | Hours to add | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Calculated time string | M | |

### Function Declaration
```java
static String addHoursToCurrentTimeString(int hours)
```

### Function Usage
```java
String futureTime = DateTimeUtil.addHoursToCurrentTimeString(5);
```
<br>

## 1.1.4 addYearsToCurrentTime

### Class Name
`DateTimeUtil`

### Function Name
`addYearsToCurrentTime`

### Function Introduction
`Returns the result of adding the specified years to the current time as a ZonedDateTime object.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| years | int | Years to add | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| ZonedDateTime | Calculated time object | M | |

### Function Declaration
```java
static ZonedDateTime addYearsToCurrentTime(int years)
```

### Function Usage
```java
ZonedDateTime futureTime = DateTimeUtil.addYearsToCurrentTime(1);
```
<br>

## 1.1.5 addToCurrentTimeString

### Class Name
`DateTimeUtil`

### Function Name
`addToCurrentTimeString`

### Function Introduction
`Returns the result of adding the specified amount of time unit to the current time as a string in ISO 8601 format.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| amountToAdd | long | Amount of time to add | M | |
| unit | ChronoUnit | Time unit (e.g., ChronoUnit.HOURS) | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Calculated time string | M | |

### Function Declaration
```java
static String addToCurrentTimeString(long amountToAdd, ChronoUnit unit)
```

### Function Usage
```java
String futureTime = DateTimeUtil.addToCurrentTimeString(30, ChronoUnit.MINUTES);
```
<br>

## 1.1.6 isExpired

### Class Name
`DateTimeUtil`

### Function Name
`isExpired`

### Function Introduction
`Checks if the input time is before the current time (i.e., expired).`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| expiredAt | Instant | Expiration time | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| boolean | Expiration status (true: expired) | M | |

### Function Declaration
```java
static boolean isExpired(Instant expiredAt)
```

### Function Usage
```java
boolean expired = DateTimeUtil.isExpired(expirationTime);
```
<br>

## 1.1.7 parseUtcTimeStringToInstant

### Class Name
`DateTimeUtil`

### Function Name
`parseUtcTimeStringToInstant`

### Function Introduction
`Parses a UTC date-time string and returns it as an Instant object.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| timeString | String | UTC date-time string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| Instant | Parsed Instant object | M | |

### Function Declaration
```java
static Instant parseUtcTimeStringToInstant(String timeString)
```

### Function Usage
```java
Instant instant = DateTimeUtil.parseUtcTimeStringToInstant("2024-01-01T12:00:00Z");
```
<br>

## 1.1.8 convertUtcToFormattedString

### Class Name
`DateTimeUtil`

### Function Name
`convertUtcToFormattedString`

### Function Introduction
`Converts a UTC date-time string to a string in "yyyy-MM-dd HH:mm:ss" format.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| utcDateTime | String | UTC date-time string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Formatted string | M | |

### Function Declaration
```java
static String convertUtcToFormattedString(String utcDateTime)
```

### Function Usage
```java
String formatted = DateTimeUtil.convertUtcToFormattedString("2024-01-01T12:00:00Z");
```
<br>

## 1.1.9 convertUtcToFormattedString (Custom Format)

### Class Name
`DateTimeUtil`

### Function Name
`convertUtcToFormattedString`

### Function Introduction
`Converts a UTC date-time string to a string in the specified format.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| utcDateTime | String | UTC date-time string | M | |
| format | String | Format string for conversion | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Formatted string | M | |

### Function Declaration
```java
static String convertUtcToFormattedString(String utcDateTime, String format)
```

### Function Usage
```java
String formatted = DateTimeUtil.convertUtcToFormattedString("2024-01-01T12:00:00Z", "yyyyMMdd");
```
<br>

## 1.1.10 isSecondDateTimeLater

### Class Name
`DateTimeUtil`

### Function Name
`isSecondDateTimeLater`

### Function Introduction
`Checks if the second date-time is later than the first date-time.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| firstUtcDateTime | String | First UTC date-time string | M | |
| secondUtcDateTime | String | Second UTC date-time string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| boolean | true if the second time is later | M | |

### Function Declaration
```java
static boolean isSecondDateTimeLater(String firstUtcDateTime, String secondUtcDateTime)
```

### Function Usage
```java
boolean isLater = DateTimeUtil.isSecondDateTimeLater("2024-01-01T10:00:00Z", "2024-01-01T12:00:00Z");
```
<br>

## 1.1.11 getMaxUTCTime

### Class Name
`DateTimeUtil`

### Function Name
`getMaxUTCTime`

### Function Introduction
`Returns the maximum UTC time (9999-12-31 23:59:59) as an Instant object.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| Instant | Max UTC time Instant object | M | |

### Function Declaration
```java
static Instant getMaxUTCTime()
```

### Function Usage
```java
Instant maxTime = DateTimeUtil.getMaxUTCTime();
```
<br>

## 1.1.12 addMinutesToCurrentTimeString

### Class Name
`DateTimeUtil`

### Function Name
`addMinutesToCurrentTimeString`

### Function Introduction
`Returns the result of adding the specified minutes to the current time as a string in ISO 8601 format.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| minutes | int | Minutes to add | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Calculated time string | M | |

### Function Declaration
```java
static String addMinutesToCurrentTimeString(int minutes)
```

### Function Usage
```java
String futureTime = DateTimeUtil.addMinutesToCurrentTimeString(30);
```
<br>

## 1.1.13 addSecondsToCurrentTimeString

### Class Name
`DateTimeUtil`

### Function Name
`addSecondsToCurrentTimeString`

### Function Introduction
`Returns the result of adding the specified seconds to the current time as a string in ISO 8601 format.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| seconds | int | Seconds to add | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Calculated time string | M | |

### Function Declaration
```java
static String addSecondsToCurrentTimeString(int seconds)
```

### Function Usage
```java
String futureTime = DateTimeUtil.addSecondsToCurrentTimeString(30);
```
<br>

## 1.2 DidUtil

## 1.2.1 extractDid

### Class Name
`DidUtil`

### Function Name
`extractDid`

### Function Introduction
`Extracts the pure DID from a DID Key URL by removing query parameters or fragments.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didKeyUrl | String | Full DID Key URL | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Extracted DID | M | |

### Function Declaration
```java
static String extractDid(String didKeyUrl)
```

### Function Usage
```java
String did = DidUtil.extractDid("did:example:1234?version=1");
```
<br>

## 1.2.2 extractKeyId

### Class Name
`DidUtil`

### Function Name
`extractKeyId`

### Function Introduction
`Extracts the Key ID (part after the fragment) from a DID Key URL.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didKeyUrl | String | Full DID Key URL | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Extracted Key ID | M | |

### Function Declaration
```java
static String extractKeyId(String didKeyUrl)
```

### Function Usage
```java
String keyId = DidUtil.extractKeyId("did:example:1234#key-1");
```
<br>

## 1.3 DidValidator

## 1.3.1 isValidDid

### Class Name
`DidValidator`

### Function Name
`isValidDid`

### Function Introduction
`Validates if the given DID string format is valid.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| did | String | DID string to validate | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| boolean | true if valid | M | |

### Function Declaration
```java
static boolean isValidDid(String did)
```

### Function Usage
```java
boolean isValid = DidValidator.isValidDid("did:example:1234");
```
<br>

## 1.3.2 isValidDidKeyUrl

### Class Name
`DidValidator`

### Function Name
`isValidDidKeyUrl`

### Function Introduction
`Validates if the given DID Key URL string format is valid.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didKeyUrl | String | DID Key URL string to validate | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| boolean | true if valid | M | |

### Function Declaration
```java
static boolean isValidDidKeyUrl(String didKeyUrl)
```

### Function Usage
```java
boolean isValid = DidValidator.isValidDidKeyUrl("did:example:1234?versionId=1#key-1");
```
<br>

## 1.4 GsonSerializationUtil

## 1.4.1 createGsonWithAttributeTypeSerializer

### Class Name
`GsonSerializationUtil`

### Function Name
`createGsonWithAttributeTypeSerializer`

### Function Introduction
`Creates a Gson object with custom serialization/deserialization registered for AttributeDef.ATTR_TYPE.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| Gson | Custom configured Gson object | M | |

### Function Declaration
```java
static Gson createGsonWithAttributeTypeSerializer()
```

### Function Usage
```java
Gson gson = GsonSerializationUtil.createGsonWithAttributeTypeSerializer();
```
<br>

## 1.4.2 parseCredentialSchema

### Class Name
`GsonSerializationUtil`

### Function Name
`parseCredentialSchema`

### Function Introduction
`Parses a JSON string and returns it as a CredentialSchema object.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| json | String | CredentialSchema JSON string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| CredentialSchema | Parsed CredentialSchema object | M | |

### Function Declaration
```java
static CredentialSchema parseCredentialSchema(String json)
```

### Function Usage
```java
CredentialSchema schema = GsonSerializationUtil.parseCredentialSchema(jsonString);
```
<br>

## 1.5 HexUtil

## 1.5.1 toHexString

### Class Name
`HexUtil`

### Function Name
`toHexString`

### Function Introduction
`Converts a byte array to a hexadecimal string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| bytes | byte[] | Byte array to convert | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Hexadecimal string | M | |

### Function Declaration
```java
static String toHexString(byte[] bytes)
```

### Function Usage
```java
String hex = HexUtil.toHexString(byteArray);
```
<br>

## 1.6 HttpClientUtil

## 1.6.1 getData

### Class Name
`HttpClientUtil`

### Function Name
`getData`

### Function Introduction
`Sends an HTTP GET request to the given URL and returns the response as a string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| url | String | Request URL | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Response body string | M | |

### Function Declaration
```java
static String getData(String url) throws HttpClientException
```

### Function Usage
```java
String response = HttpClientUtil.getData("http://example.com/api");
```
<br>

## 1.6.2 getData (Typed)

### Class Name
`HttpClientUtil`

### Function Name
`getData`

### Function Introduction
`Sends an HTTP GET request to the given URL and returns the response parsed as the specified class type.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| url | String | Request URL | M | |
| responseType | Class&lt;T&gt; | Class type to map the response to | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| T | Parsed response object | M | |

### Function Declaration
```java
static <T> T getData(String url, Class<T> responseType) throws HttpClientException
```

### Function Usage
```java
MyResponse response = HttpClientUtil.getData("http://example.com/api", MyResponse.class);
```
<br>

## 1.6.3 postData

### Class Name
`HttpClientUtil`

### Function Name
`postData`

### Function Introduction
`Sends an HTTP POST request to the given URL and returns the response as a string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| url | String | Request URL | M | |
| requestBody | String | Request body string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Response body string | M | |

### Function Declaration
```java
static String postData(String url, String requestBody) throws IOException, InterruptedException, HttpClientException
```

### Function Usage
```java
String response = HttpClientUtil.postData("http://example.com/api", "{\"key\":\"value\"}");
```
<br>

## 1.6.4 postData (Typed)

### Class Name
`HttpClientUtil`

### Function Name
`postData`

### Function Introduction
`Sends an HTTP POST request to the given URL and returns the response parsed as the specified class type.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| url | String | Request URL | M | |
| requestBody | String | Request body string | M | |
| responseType | Class&lt;T&gt; | Class type to map the response to | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| T | Parsed response object | M | |

### Function Declaration
```java
static <T> T postData(String url, String requestBody, Class<T> responseType) throws HttpClientException
```

### Function Usage
```java
MyResponse response = HttpClientUtil.postData("http://example.com/api", "{\"key\":\"value\"}", MyResponse.class);
```
<br>

## 1.7 IdGenerator

## 1.7.1 generateTxId

### Class Name
`IdGenerator`

### Function Name
`generateTxId`

### Function Introduction
`Generates a random Transaction ID (UUID).`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Generated Transaction ID | M | |

### Function Declaration
```java
static String generateTxId()
```

### Function Usage
```java
String txId = IdGenerator.generateTxId();
```
<br>

## 1.7.2 generateOfferId

### Class Name
`IdGenerator`

### Function Name
`generateOfferId`

### Function Introduction
`Generates a random Offer ID (UUID).`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Generated Offer ID | M | |

### Function Declaration
```java
static String generateOfferId()
```

### Function Usage
```java
String offerId = IdGenerator.generateOfferId();
```
<br>

## 1.8 JsonUtil

## 1.8.1 serializeAndSort

### Class Name
`JsonUtil`

### Function Name
`serializeAndSort`

### Function Introduction
`Serializes an object to a JSON string, sorts keys, and removes whitespace (for signature payload generation).`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| obj | Object | Object to serialize | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Sorted and whitespace-removed JSON string | M | |

### Function Declaration
```java
static String serializeAndSort(Object obj)
```

### Function Usage
```java
String sortedJson = JsonUtil.serializeAndSort(myObject);
```
<br>

## 1.8.2 serializeToJson

### Class Name
`JsonUtil`

### Function Name
`serializeToJson`

### Function Introduction
`Serializes an object to a JSON string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| obj | Object | Object to serialize | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | JSON string | M | |

### Function Declaration
```java
static String serializeToJson(Object obj)
```

### Function Usage
```java
String json = JsonUtil.serializeToJson(myObject);
```
<br>

## 1.8.3 deserializeFromJson

### Class Name
`JsonUtil`

### Function Name
`deserializeFromJson`

### Function Introduction
`Deserializes a JSON string into an object of the specified class type.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| jsonString | String | JSON string | M | |
| clazz | Class&lt;T&gt; | Class type to deserialize to | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| T | Deserialized object | M | |

### Function Declaration
```java
static <T> T deserializeFromJson(String jsonString, Class<T> clazz)
```

### Function Usage
```java
MyObject obj = JsonUtil.deserializeFromJson(jsonString, MyObject.class);
```
<br>

## 1.8.4 deserializeFromJson (TypeReference)

### Class Name
`JsonUtil`

### Function Name
`deserializeFromJson`

### Function Introduction
`Deserializes a JSON string into an object of a complex generic type using TypeReference.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| jsonString | String | JSON string | M | |
| typeReference | TypeReference&lt;T&gt; | TypeReference to deserialize to | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| T | Deserialized object | M | |

### Function Declaration
```java
static <T> T deserializeFromJson(String jsonString, TypeReference<T> typeReference)
```

### Function Usage
```java
List<String> list = JsonUtil.deserializeFromJson(jsonString, new TypeReference<List<String>>(){});
```
<br>

## 1.9 NonceGenerator

## 1.9.1 generate16ByteNonce

### Class Name
`NonceGenerator`

### Function Name
`generate16ByteNonce`

### Function Introduction
`Generates a random 16-byte Nonce.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated 16-byte Nonce | M | |

### Function Declaration
```java
static byte[] generate16ByteNonce()
```

### Function Usage
```java
byte[] nonce = NonceGenerator.generate16ByteNonce();
```
<br>

## 1.10 QrMaker

## 1.10.1 makeQrImage (Object)

### Class Name
`QrMaker`

### Function Name
`makeQrImage`

### Function Introduction
`Serializes an object to JSON and generates a QR code image, returning it as a QrImageData object.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | Object | Data object to convert to QR code | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| QrImageData | Generated QR image data | M | |

### Function Declaration
```java
static QrImageData makeQrImage(final Object data)
```

### Function Usage
```java
QrImageData qrData = QrMaker.makeQrImage(myObject);
```
<br>

## 1.10.2 makeQrImage (Raw)

### Class Name
`QrMaker`

### Function Name
`makeQrImage`

### Function Introduction
`Generates a QR code image (byte array) from string content.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| contents | String | String content to convert to QR code | M | |
| format | String | Image format (e.g., "PNG") | M | |
| width | int | Image width | M | |
| height | int | Image height | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated QR image byte array | M | |

### Function Declaration
```java
static byte[] makeQrImage(final String contents, final String format, int width, int height)
```

### Function Usage
```java
byte[] qrBytes = QrMaker.makeQrImage("data", "png", 300, 300);
```
<br>

## 1.10.3 makeQrBitMatrix

### Class Name
`QrMaker`

### Function Name
`makeQrBitMatrix`

### Function Introduction
`Generates a QR code BitMatrix from string content.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| contents | String | String content to convert to QR code | M | |
| width | int | Image width | M | |
| height | int | Image height | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| BitMatrix | Generated QR BitMatrix | M | |

### Function Declaration
```java
static BitMatrix makeQrBitMatrix(String contents, int width, int height) throws WriterException
```

### Function Usage
```java
BitMatrix matrix = QrMaker.makeQrBitMatrix("data", 300, 300);
```
<br>

## 1.11 QrUtil

## 1.11.1 convertQrImage

### Class Name
`QrUtil`

### Function Name
`convertQrImage`

### Function Introduction
`Converts data to a QR code and returns it as a Base64 encoded string (Data URI Scheme).`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | String | Data string to convert to QR code | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Base64 encoded QR image string | M | |

### Function Declaration
```java
static String convertQrImage(String data)
```

### Function Usage
```java
String base64Qr = QrUtil.convertQrImage("data");
```
<br>

## 1.12 ValidationUtil

## 1.12.1 validate

### Class Name
`ValidationUtil`

### Function Name
`validate`

### Function Introduction
`Validates the object's validity (Jakarta Bean Validation).`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| obj | T | Object to validate | M | |

### Output Parameters
n/a (Throws exception if invalid)

### Function Declaration
```java
static <T> void validate(T obj)
```

### Function Usage
```java
ValidationUtil.validate(myObject);
```
<br>


## 1.16 BaseBlockChainUtil

## 1.16.1 connect

### Class Name
`BaseBlockChainUtil`

### Function Name
`connect`

### Function Introduction
`Connects to the blockchain network.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| config | BlockChainConfig | Blockchain configuration info | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| BlockChainManagerInterface | Blockchain manager interface | M | |

### Function Declaration
```java
static BlockChainManagerInterface connect(BlockChainConfig config) throws CommonSdkException
```

### Function Usage
```java
BlockChainManagerInterface manager = BaseBlockChainUtil.connect(config);
```
<br>

## 1.16.2 registerDidDocument

### Class Name
`BaseBlockChainUtil`

### Function Name
`registerDidDocument`

### Function Introduction
`Registers a DID Document to the blockchain.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | Blockchain manager | M | |
| did | String | DID to register | M | |
| didDocument | DidDocument | DID Document object to register | M | |

### Output Parameters
n/a (Throws exception if failed)

### Function Declaration
```java
static void registerDidDocument(BlockChainManagerInterface blockChain, String did, DidDocument didDocument) throws CommonSdkException
```

### Function Usage
```java
BaseBlockChainUtil.registerDidDocument(manager, did, doc);
```
<br>

## 1.16.3 getDidDocument

### Class Name
`BaseBlockChainUtil`

### Function Name
`getDidDocument`

### Function Introduction
`Retrieves a DID Document from the blockchain.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | Blockchain manager | M | |
| did | String | DID to retrieve | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| DidDocument | Retrieved DID Document | M | |

### Function Declaration
```java
static DidDocument getDidDocument(BlockChainManagerInterface blockChain, String did) throws CommonSdkException
```

### Function Usage
```java
DidDocument doc = BaseBlockChainUtil.getDidDocument(manager, did);
```
<br>

## 1.16.4 registerVcMetadata

### Class Name
`BaseBlockChainUtil`

### Function Name
`registerVcMetadata`

### Function Introduction
`Registers VC metadata to the blockchain.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | Blockchain manager | M | |
| vcId | String | VC identifier | M | |
| vcMetadata | VcMetadata | VC metadata | M | |

### Output Parameters
n/a (Throws exception if failed)

### Function Declaration
```java
static void registerVcMetadata(BlockChainManagerInterface blockChain, String vcId, VcMetadata vcMetadata) throws CommonSdkException
```

### Function Usage
```java
BaseBlockChainUtil.registerVcMetadata(manager, vcId, meta);
```
<br>

## 1.16.5 getVcMetadata

### Class Name
`BaseBlockChainUtil`

### Function Name
`getVcMetadata`

### Function Introduction
`Retrieves VC metadata from the blockchain.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | Blockchain manager | M | |
| vcId | String | VC identifier to retrieve | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VcMetadata | Retrieved VC metadata | M | |

### Function Declaration
```java
static VcMetadata getVcMetadata(BlockChainManagerInterface blockChain, String vcId) throws CommonSdkException
```

### Function Usage
```java
VcMetadata meta = BaseBlockChainUtil.getVcMetadata(manager, vcId);
```
<br>

## 1.16.6 updateDidDocStatus

### Class Name
`BaseBlockChainUtil`

### Function Name
`updateDidDocStatus`

### Function Introduction
`Updates the status of a DID Document.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | Blockchain manager | M | |
| did | String | Target DID | M | |
| status | DidDocStatus | Status to update to | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| DidDocument | Updated DID Document | M | |

### Function Declaration
```java
static DidDocument updateDidDocStatus(BlockChainManagerInterface blockChain, String did, DidDocStatus status) throws CommonSdkException
```

### Function Usage
```java
BaseBlockChainUtil.updateDidDocStatus(manager, did, DidDocStatus.Deactivated);
```
<br>

## 1.16.7 updateVcStatus

### Class Name
`BaseBlockChainUtil`

### Function Name
`updateVcStatus`

### Function Introduction
`Updates the status of a VC.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | Blockchain manager | M | |
| vcId | String | Target VC identifier | M | |
| status | VcStatus | Status to update to | M | |

### Output Parameters
n/a (Throws exception if failed)

### Function Declaration
```java
static void updateVcStatus(BlockChainManagerInterface blockChain, String vcId, VcStatus status) throws CommonSdkException
```

### Function Usage
```java
BaseBlockChainUtil.updateVcStatus(manager, vcId, VcStatus.Revoked);
```
<br>

## 1.17 BaseCryptoUtil

## 1.17.1 generateKeyPair

### Class Name
`BaseCryptoUtil`

### Function Name
`generateKeyPair`

### Function Introduction
`Generates a key pair using the specified ECC curve type.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| curveType | EccCurveType | ECC curve type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| KeyPairInterface | Generated key pair interface | M | |

### Function Declaration
```java
static KeyPairInterface generateKeyPair(EccCurveType curveType) throws CommonSdkException
```

### Function Usage
```java
KeyPairInterface pair = BaseCryptoUtil.generateKeyPair(EccCurveType.Secp256r1);
```
<br>

## 1.17.2 generateSharedSecret

### Class Name
`BaseCryptoUtil`

### Function Name
`generateSharedSecret`

### Function Introduction
`Generates a shared secret using the public and private keys.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| publicKey | byte[] | Other party's public key | M | |
| privateKey | byte[] | Own private key | M | |
| curveType | EccCurveType | ECC curve type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated shared secret | M | |

### Function Declaration
```java
static byte[] generateSharedSecret(byte[] publicKey, byte[] privateKey, EccCurveType curveType) throws CommonSdkException
```

### Function Usage
```java
byte[] secret = BaseCryptoUtil.generateSharedSecret(pubKey, privKey, EccCurveType.Secp256r1);
```
<br>

## 1.17.3 generateNonce

### Class Name
`BaseCryptoUtil`

### Function Name
`generateNonce`

### Function Introduction
`Generates a random nonce of the specified length.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| length | int | Length of nonce to generate | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated nonce | M | |

### Function Declaration
```java
static byte[] generateNonce(int length) throws CommonSdkException
```

### Function Usage
```java
byte[] nonce = BaseCryptoUtil.generateNonce(16);
```
<br>

## 1.17.4 verifySignature

### Class Name
`BaseCryptoUtil`

### Function Name
`verifySignature`

### Function Introduction
`Verifies a digital signature using the public key.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| encodedPublicKey | String | Encoded public key | M | |
| signature | String | Signature to verify | M | |
| message | byte[] | Original message | M | |
| curveType | EccCurveType | ECC curve type | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
static void verifySignature(String encodedPublicKey, String signature, byte[] message, EccCurveType curveType) throws CommonSdkException
```

### Function Usage
```java
BaseCryptoUtil.verifySignature(pubKeyStr, sigStr, msgBytes, EccCurveType.Secp256r1);
```
<br>

## 1.17.5 compressPublicKey

### Class Name
`BaseCryptoUtil`

### Function Name
`compressPublicKey`

### Function Introduction
`Compresses a public key.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| publicKey | byte[] | Uncompressed public key | M | |
| curveType | EccCurveType | ECC curve type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Compressed public key | M | |

### Function Declaration
```java
static byte[] compressPublicKey(byte[] publicKey, EccCurveType curveType) throws CommonSdkException
```

### Function Usage
```java
byte[] compressed = BaseCryptoUtil.compressPublicKey(pubKey, EccCurveType.Secp256r1);
```
<br>

## 1.17.6 decompressPublicKey

### Class Name
`BaseCryptoUtil`

### Function Name
`decompressPublicKey`

### Function Introduction
`Decompresses a compressed public key.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| compressedKey | byte[] | Compressed public key | M | |
| curveType | EccCurveType | ECC curve type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Decompressed public key | M | |

### Function Declaration
```java
static byte[] decompressPublicKey(byte[] compressedKey, EccCurveType curveType) throws CommonSdkException
```

### Function Usage
```java
byte[] uncompressed = BaseCryptoUtil.decompressPublicKey(compKey, EccCurveType.Secp256r1);
```
<br>

## 1.17.7 mergeSharedSecretAndNonce

### Class Name
`BaseCryptoUtil`

### Function Name
`mergeSharedSecretAndNonce`

### Function Introduction
`Merges the shared secret and nonce to generate a session key.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| sharedSecret | byte[] | Shared secret | M | |
| nonce | byte[] | Nonce | M | |
| cipherType | SymmetricCipherType | Symmetric cipher type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated session key | M | |

### Function Declaration
```java
static byte[] mergeSharedSecretAndNonce(byte[] sharedSecret, byte[] nonce, SymmetricCipherType cipherType) throws CommonSdkException
```

### Function Usage
```java
byte[] sessionKey = BaseCryptoUtil.mergeSharedSecretAndNonce(secret, nonce, SymmetricCipherType.AES_256_CBC);
```
<br>

## 1.17.8 encrypt

### Class Name
`BaseCryptoUtil`

### Function Name
`encrypt`

### Function Introduction
`Encrypts data using a symmetric key.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| plainText | byte[] | Plaintext data to encrypt | M | |
| key | byte[] | Encryption key | M | |
| iv | byte[] | Initialization Vector (IV) | M | |
| cipherType | SymmetricCipherType | Symmetric cipher type | M | |
| paddingType | SymmetricPaddingType | Padding type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Encrypted data | M | |

### Function Declaration
```java
static byte[] encrypt(byte[] plainText, byte[] key, byte[] iv, SymmetricCipherType cipherType, SymmetricPaddingType paddingType) throws CommonSdkException
```

### Function Usage
```java
byte[] encrypted = BaseCryptoUtil.encrypt(plain, key, iv, SymmetricCipherType.AES_256_CBC, SymmetricPaddingType.PKCS5);
```
<br>

## 1.17.9 decrypt

### Class Name
`BaseCryptoUtil`

### Function Name
`decrypt`

### Function Introduction
`Decrypts encrypted data using a symmetric key.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| cipherText | byte[] | Ciphertext data to decrypt | M | |
| key | byte[] | Decryption key | M | |
| iv | byte[] | Initialization Vector (IV) | M | |
| cipherType | SymmetricCipherType | Symmetric cipher type | M | |
| paddingType | SymmetricPaddingType | Padding type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Decrypted data | M | |

### Function Declaration
```java
static byte[] decrypt(byte[] cipherText, byte[] key, byte[] iv, SymmetricCipherType cipherType, SymmetricPaddingType paddingType) throws CommonSdkException
```

### Function Usage
```java
byte[] decrypted = BaseCryptoUtil.decrypt(encrypted, key, iv, SymmetricCipherType.AES_256_CBC, SymmetricPaddingType.PKCS5);
```
<br>

## 1.18 BaseCoreDidUtil

## 1.18.1 parseDidDoc (String)

### Class Name
`BaseCoreDidUtil`

### Function Name
`parseDidDoc`

### Function Introduction
`Parses a DID Document JSON string and returns a DidManager.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDocJson | String | DID Document JSON string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| DidManager | DidManager instance | M | |

### Function Declaration
```java
static DidManager parseDidDoc(String didDocJson) throws CommonSdkException
```

### Function Usage
```java
DidManager manager = BaseCoreDidUtil.parseDidDoc(jsonStr);
```
<br>

## 1.18.2 parseDidDoc (Object)

### Class Name
`BaseCoreDidUtil`

### Function Name
`parseDidDoc`

### Function Introduction
`Parses a DID Document object.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDocument | DidDocument | DID Document object | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| DidManager | DidManager instance | M | |

### Function Declaration
```java
static DidManager parseDidDoc(DidDocument didDocument) throws CommonSdkException
```

### Function Usage
```java
DidManager manager = BaseCoreDidUtil.parseDidDoc(doc);
```
<br>

## 1.18.3 getVerificationMethod (Manager)

### Class Name
`BaseCoreDidUtil`

### Function Name
`getVerificationMethod`

### Function Introduction
`Retrieves a Verification Method from DidManager by Key ID.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didManager | DidManager | DidManager instance | M | |
| keyId | String | Key ID to retrieve | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VerificationMethod | Retrieved Verification Method | M | |

### Function Declaration
```java
static VerificationMethod getVerificationMethod(DidManager didManager, String keyId) throws CommonSdkException
```

### Function Usage
```java
VerificationMethod method = BaseCoreDidUtil.getVerificationMethod(manager, "key-1");
```
<br>

## 1.18.4 getVerificationMethod (Document)

### Class Name
`BaseCoreDidUtil`

### Function Name
`getVerificationMethod`

### Function Introduction
`Retrieves a Verification Method from DidDocument by Key ID.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDocument | DidDocument | DidDocument object | M | |
| keyId | String | Key ID to retrieve | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VerificationMethod | Retrieved Verification Method | M | |

### Function Declaration
```java
static VerificationMethod getVerificationMethod(DidDocument didDocument, String keyId) throws CommonSdkException
```

### Function Usage
```java
VerificationMethod method = BaseCoreDidUtil.getVerificationMethod(doc, "key-1");
```
<br>

## 1.18.5 getPublicKey

### Class Name
`BaseCoreDidUtil`

### Function Name
`getPublicKey`

### Function Introduction
`Retrieves the public key corresponding to a specific Key ID from the DID Document.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDocument | DidDocument | DidDocument object | M | |
| keyId | String | Key ID to retrieve | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Public key (Multibase or other stored format) | M | |

### Function Declaration
```java
static String getPublicKey(DidDocument didDocument, String keyId) throws CommonSdkException
```

### Function Usage
```java
String pubKey = BaseCoreDidUtil.getPublicKey(doc, "key-1");
```
<br>

## 1.18.6 verifyDidDocKeyProofs

### Class Name
`BaseCoreDidUtil`

### Function Name
`verifyDidDocKeyProofs`

### Function Introduction
`Verifies the Key Proofs inside the DID Document.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDocument | DidDocument | DID Document to verify | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
static void verifyDidDocKeyProofs(DidDocument didDocument) throws CommonSdkException
```

### Function Usage
```java
BaseCoreDidUtil.verifyDidDocKeyProofs(doc);
```
<br>

## 1.18.7 createDidDocument

### Class Name
`BaseCoreDidUtil`

### Function Name
`createDidDocument`

### Function Introduction
`Creates a new DID Document.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didManager | DidManager | DidManager instance | M | |
| did | String | DID to create | M | |
| controller | String | Controller DID | M | |
| didKeyInfos | List&lt;DidKeyInfo&gt; | List of key information | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| DidDocument | Created DID Document | M | |

### Function Declaration
```java
static DidDocument createDidDocument(DidManager didManager, String did, String controller, List<DidKeyInfo> didKeyInfos) throws CommonSdkException
```

### Function Usage
```java
DidDocument doc = BaseCoreDidUtil.createDidDocument(manager, did, controller, keys);
```
<br>

## 1.18.8 signAndAddProof

### Class Name
`BaseCoreDidUtil`

### Function Name
`signAndAddProof`

### Function Introduction
`Signs the DID Document and adds a Proof.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didManager | DidManager | DidManager containing the document | M | |
| walletManager | WalletManagerInterface | Wallet manager to use for signing | M | |
| signingKeyIds | List&lt;String&gt; | List of Key IDs to sign with | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| DidDocument | Signed DID Document | M | |

### Function Declaration
```java
static DidDocument signAndAddProof(DidManager didManager, WalletManagerInterface walletManager, List<String> signingKeyIds) throws CommonSdkException
```

### Function Usage
```java
DidDocument signedDoc = BaseCoreDidUtil.signAndAddProof(manager, wallet, keyIds);
```
<br>

## 1.19 BaseAccessTokenUtil

## 1.19.1 splitJwtToken

### Class Name
`BaseAccessTokenUtil`

### Function Name
`splitJwtToken`

### Function Introduction
`Splits a JWT token into Header, Payload, and Signature components.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| jwtToken | String | JWT token string to split | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| JwtComponents | Split JWT components | M | |

### Function Declaration
```java
static JwtComponents splitJwtToken(String jwtToken) throws CommonSdkException
```

### Function Usage
```java
JwtComponents components = BaseAccessTokenUtil.splitJwtToken(tokenStr);
```
<br>

## 1.19.2 verifyJwtSignature

### Class Name
`BaseAccessTokenUtil`

### Function Name
`verifyJwtSignature`

### Function Introduction
`Verifies the JWT token signature.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| jwtComponents | JwtComponents | JWT components | M | |
| publicKey | String | Public key to verify with (Multibase) | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
static void verifyJwtSignature(JwtComponents jwtComponents, String publicKey) throws CommonSdkException
```

### Function Usage
```java
BaseAccessTokenUtil.verifyJwtSignature(components, pubKey);
```
<br>

## 1.19.3 validateTokenAndExtractPii

### Class Name
`BaseAccessTokenUtil`

### Function Name
`validateTokenAndExtractPii`

### Function Introduction
`Validates the token and extracts PII (Personally Identifiable Information) from the payload.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| accessToken | String | Access token to validate | M | |
| didDocument | DidDocument | DID Document for signature verification | M | |
| keyId | String | Key ID for signature verification | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Extracted PII (JSON string) | M | |

### Function Declaration
```java
static String validateTokenAndExtractPii(String accessToken, DidDocument didDocument, String keyId) throws CommonSdkException
```

### Function Usage
```java
String pii = BaseAccessTokenUtil.validateTokenAndExtractPii(token, doc, "key-1");
```
<br>

## 1.19.4 generateAccessToken

### Class Name
`BaseAccessTokenUtil`

### Function Name
`generateAccessToken`

### Function Introduction
`Generates an access token.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| payload | String | Token payload (JSON string) | M | |
| walletManager | WalletManagerInterface | Wallet manager | M | |
| keyId | String | Key ID to sign with | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Generated access token (JWT) | M | |

### Function Declaration
```java
static String generateAccessToken(String payload, WalletManagerInterface walletManager, String keyId) throws CommonSdkException
```

### Function Usage
```java
String token = BaseAccessTokenUtil.generateAccessToken(payloadJson, wallet, "key-1");
```
<br>

## 1.19.5 verifyAccessToken

### Class Name
`BaseAccessTokenUtil`

### Function Name
`verifyAccessToken`

### Function Introduction
`Verifies the validity of an access token.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| accessToken | String | Access token to verify | M | |
| didDocument | DidDocument | DID Document for verification | M | |
| keyId | String | Key ID for verification | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
static void verifyAccessToken(String accessToken, DidDocument didDocument, String keyId) throws CommonSdkException
```

### Function Usage
```java
BaseAccessTokenUtil.verifyAccessToken(token, doc, "key-1");
```
<br>

## 1.20 BaseWalletUtil

## 1.20.1 createWallet

### Class Name
`BaseWalletUtil`

### Function Name
`createWallet`

### Function Introduction
`Creates a new wallet.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| walletPath | String | Path to save the wallet | M | |
| password | char[] | Wallet password | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| WalletManagerInterface | Created wallet manager | M | |

### Function Declaration
```java
static WalletManagerInterface createWallet(String walletPath, char[] password) throws CommonSdkException
```

### Function Usage
```java
WalletManagerInterface wallet = BaseWalletUtil.createWallet("./wallet.db", "pass".toCharArray());
```
<br>

## 1.20.2 loadWallet

### Class Name
`BaseWalletUtil`

### Function Name
`loadWallet`

### Function Introduction
`Loads an existing wallet.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| walletPath | String | Path to the wallet file | M | |
| password | char[] | Wallet password | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| WalletManagerInterface | Loaded wallet manager | M | |

### Function Declaration
```java
static WalletManagerInterface loadWallet(String walletPath, char[] password) throws CommonSdkException
```

### Function Usage
```java
WalletManagerInterface wallet = BaseWalletUtil.loadWallet("./wallet.db", "pass".toCharArray());
```
<br>

## 1.20.3 generateKey

### Class Name
`BaseWalletUtil`

### Function Name
`generateKey`

### Function Introduction
`Generates a new key within the wallet.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| walletManager | WalletManagerInterface | Wallet manager | M | |
| keyId | String | Key ID to generate | M | |
| algType | String | Algorithm type (e.g., "Secp256r1") | M | |

### Output Parameters
n/a (Throws exception if failed)

### Function Declaration
```java
static void generateKey(WalletManagerInterface walletManager, String keyId, String algType) throws CommonSdkException
```

### Function Usage
```java
BaseWalletUtil.generateKey(wallet, "key-1", "Secp256r1");
```
<br>

## 1.20.4 sign

### Class Name
`BaseWalletUtil`

### Function Name
`sign`

### Function Introduction
`Signs data to generate a Compact Signature.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| walletManager | WalletManagerInterface | Wallet manager | M | |
| keyId | String | Key ID to sign with | M | |
| data | byte[] | Data to sign | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated signature data | M | |

### Function Declaration
```java
static byte[] sign(WalletManagerInterface walletManager, String keyId, byte[] data) throws CommonSdkException
```

### Function Usage
```java
byte[] sig = BaseWalletUtil.sign(wallet, "key-1", dataBytes);
```
<br>


# 2. APIs - Domains

## 2.1 DidDocCryptoHelper

## 2.1.1 verifyDidDocumentSignature

### Class Name
`DidDocCryptoHelper`

### Function Name
`verifyDidDocumentSignature`

### Function Introduction
`Verifies the signature of the DID Document.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDocument | DidDocument | DID Document to verify | M | |
| verificationMethodId | String | Verification Method ID to use for verification | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
static void verifyDidDocumentSignature(DidDocument didDocument, String verificationMethodId) throws CommonSdkException
```

### Function Usage
```java
DidDocCryptoHelper.verifyDidDocumentSignature(didDoc, "key-1");
```
<br>

## 2.1.2 generateInvokedDidDoc

### Class Name
`DidDocCryptoHelper`

### Function Name
`generateInvokedDidDoc`

### Function Introduction
`Generates an InvokedDidDoc object.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| invokerDidDoc | DidDocument | Invoker (Caller) DID Document | M | |
| ownerDidDoc | DidDocument | Owner (Target) DID Document | M | |
| proofType | ProofType | Proof type | M | |
| certificateUrl | String | Certificate URL (Optional) | O | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| InvokedDidDoc | Generated InvokedDidDoc object | M | |

### Function Declaration
```java
static InvokedDidDoc generateInvokedDidDoc(DidDocument invokerDidDoc, DidDocument ownerDidDoc, ProofType proofType, String certificateUrl) throws CommonSdkException
```

### Function Usage
```java
InvokedDidDoc invoked = DidDocCryptoHelper.generateInvokedDidDoc(invoker, owner, ProofType.Secp256k1Signature2019, "http://cert");
```
<br>

## 2.1.3 signInvokedDidDoc

### Class Name
`DidDocCryptoHelper`

### Function Name
`signInvokedDidDoc`

### Function Introduction
`Signs an InvokedDidDoc.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| unsignedInvokedDidDoc | InvokedDidDoc | Unsigned InvokedDidDoc | M | |
| walletManager | WalletManagerInterface | Wallet manager | M | |
| keyId | String | Key ID to sign with | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| InvokedDidDoc | Signed InvokedDidDoc | M | |

### Function Declaration
```java
static InvokedDidDoc signInvokedDidDoc(InvokedDidDoc unsignedInvokedDidDoc, WalletManagerInterface walletManager, String keyId) throws CommonSdkException
```

### Function Usage
```java
InvokedDidDoc signed = DidDocCryptoHelper.signInvokedDidDoc(unsigned, walletMgr, "key-1");
```
<br>

## 2.2 EcdhCryptoHelper

## 2.2.1 generateSessionKey

### Class Name
`EcdhCryptoHelper`

### Function Name
`generateSessionKey`

### Function Introduction
`Generates a session key via ECDH.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| clientPublicKey | byte[] | Client public key | M | |
| serverPrivateKey | byte[] | Server private key | M | |
| mergedNonce | byte[] | Merged Nonce | M | |
| cipherType | SymmetricCipherType | Symmetric cipher type | M | |
| curveType | EccCurveType | Elliptic curve type | M | |
| applyNonceHashing | boolean | Whether to apply Nonce hashing | O | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated session key | M | |

### Function Declaration
```java
static byte[] generateSessionKey(byte[] clientPublicKey, byte[] serverPrivateKey, byte[] mergedNonce, SymmetricCipherType cipherType, EccCurveType curveType) throws CommonSdkException
```

### Function Usage
```java
byte[] sessionKey = EcdhCryptoHelper.generateSessionKey(pubKey, priKey, nonce, cipherType, curveType);
```
<br>

## 2.2.2 verifySessionKey

### Class Name
`EcdhCryptoHelper`

### Function Name
`verifySessionKey`

### Function Introduction
`Verifies if the generated session key matches the expected key.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| sessionKey | byte[] | Generated session key | M | |
| expectedSessionKey | byte[] | Expected session key | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| boolean | true if they match | M | |

### Function Declaration
```java
static boolean verifySessionKey(byte[] sessionKey, byte[] expectedSessionKey) throws CommonSdkException
```

### Function Usage
```java
boolean isValid = EcdhCryptoHelper.verifySessionKey(key1, key2);
```
<br>

## 2.2.3 selectCipherType

### Class Name
`EcdhCryptoHelper`

### Function Name
`selectCipherType`

### Function Introduction
`Selects a cipher type supported by the server from the candidate list.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| candidate | Candidate | Cipher candidate list | M | |
| serverCipherType | SymmetricCipherType | Server's preferred cipher type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| SymmetricCipherType | Selected cipher type | M | |

### Function Declaration
```java
static SymmetricCipherType selectCipherType(Candidate candidate, SymmetricCipherType serverCipherType) throws CommonSdkException
```

### Function Usage
```java
SymmetricCipherType selected = EcdhCryptoHelper.selectCipherType(candidate, serverCipherType);
```
<br>

## 2.3 SignatureCryptoHelper

## 2.3.1 verifySignature

### Class Name
`SignatureCryptoHelper`

### Function Name
`verifySignature`

### Function Introduction
`Verifies a digital signature.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| encodedPublicKey | String | Encoded public key | M | |
| signature | String | Signature to verify | M | |
| message | byte[] | Original message | M | |
| proofType | ProofType | Proof type | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
static void verifySignature(String encodedPublicKey, String signature, byte[] message, ProofType proofType) throws CommonSdkException
```

### Function Usage
```java
SignatureCryptoHelper.verifySignature(pubKey, sig, msg, type);
```
<br>

## 2.3.2 generateSignatureMessage

### Class Name
`SignatureCryptoHelper`

### Function Name
`generateSignatureMessage`

### Function Introduction
`Generates a signature message (hash of Canonical JSON).`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | Object | Data object to sign | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated signature message hash | M | |

### Function Declaration
```java
static byte[] generateSignatureMessage(Object data) throws CommonSdkException
```

### Function Usage
```java
byte[] msgHash = SignatureCryptoHelper.generateSignatureMessage(data);
```
<br>

## 2.3.3 removeProofValue

### Class Name
`SignatureCryptoHelper`

### Function Name
`removeProofValue`

### Function Introduction
`Removes the Proof value from the data to restore it to the pre-signed state.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | T | Data to remove Proof from | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| T | Data with Proof removed (Deep Copy) | M | |

### Function Declaration
```java
static <T> T removeProofValue(T data) throws CommonSdkException
```

### Function Usage
```java
MyObject cleaned = SignatureCryptoHelper.removeProofValue(signedData);
```
<br>

## 2.3.4 extractSignatureMessage

### Class Name
`SignatureCryptoHelper`

### Function Name
`extractSignatureMessage`

### Function Introduction
`Removes the Proof from the data and extracts the signature message.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | T | Data to extract signature message from | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated signature message hash | M | |

### Function Declaration
```java
static <T> byte[] extractSignatureMessage(T data) throws CommonSdkException
```

### Function Usage
```java
byte[] msgHash = SignatureCryptoHelper.extractSignatureMessage(signedData);
```
<br>

# 3. APIs - Services



## 3.1 EcdhService

## 3.1.1 processEcdhRequest

### Class Name
`EcdhService`

### Function Name
`processEcdhRequest`

### Function Introduction
`Processes ECDH request data to generate an acceptor response (AccEcdh) containing the server's key and nonce.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| ecdhReqData | EcdhReqData | ECDH request data | M | |
| serverCipherType | SymmetricCipherType | Server cipher type | M | |
| serverPaddingType | SymmetricPaddingType | Server padding type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| AccEcdh | ECDH accept response data | M | |

### Function Declaration
```java
AccEcdh processEcdhRequest(EcdhReqData ecdhReqData, SymmetricCipherType serverCipherType, SymmetricPaddingType serverPaddingType) throws CommonSdkException
```

### Function Usage
```java
AccEcdh accEcdh = ecdhService.processEcdhRequest(reqData, cipherType, paddingType);
```
<br>

## 3.1.2 verifyEcdhRequest

### Class Name
`EcdhService`

### Function Name
`verifyEcdhRequest`

### Function Introduction
`Validates the ECDH request data and the client DID.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| ecdhReqData | EcdhReqData | ECDH request data | M | |
| expectedClientDid | String | Expected client DID | M | |

### Output Parameters
n/a (Throws exception if validation fails)

### Function Declaration
```java
void verifyEcdhRequest(EcdhReqData ecdhReqData, String expectedClientDid) throws CommonSdkException
```

### Function Usage
```java
ecdhService.verifyEcdhRequest(reqData, "did:example:client");
```
<br>

## 3.1.3 generateSessionKey

### Class Name
`EcdhService`

### Function Name
`generateSessionKey`

### Function Introduction
`Generates a session key via ECDH key exchange.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| ecdhReqData | EcdhReqData | ECDH request data | M | |
| serverPrivateKey | byte[] | Server private key | M | |
| mergedNonce | byte[] | Merged Nonce (Client Nonce + Server Nonce) | M | |
| serverCipherType | SymmetricCipherType | Server cipher type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated session key | M | |

### Function Declaration
```java
byte[] generateSessionKey(EcdhReqData ecdhReqData, byte[] serverPrivateKey, byte[] mergedNonce, SymmetricCipherType serverCipherType) throws CommonSdkException
```

### Function Usage
```java
byte[] sessionKey = ecdhService.generateSessionKey(reqData, priKey, nonce, cipherType);
```
<br>

## 3.2 SignatureService

## 3.2.1 generateProof

### Class Name
`SignatureService`

### Function Name
`generateProof`

### Function Introduction
`Generates a Proof (signature) for the data.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | Object | Data to sign | M | |
| proofPurpose | ProofPurpose | Proof purpose | M | |
| proofType | ProofType | Proof type | M | |
| verificationMethod | String | Verification method (Key ID) | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| Proof | Generated Proof object | M | |

### Function Declaration
```java
Proof generateProof(Object data, ProofPurpose proofPurpose, ProofType proofType, String verificationMethod) throws CommonSdkException
```

### Function Usage
```java
Proof proof = signatureService.generateProof(data, purpose, type, "key-1");
```
<br>

## 3.2.2 verifyProof

### Class Name
`SignatureService`

### Function Name
`verifyProof`

### Function Introduction
`Verifies the Proof contained in the data.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | Object | Data to verify | M | |
| proof | Proof | Proof object to verify | M | |
| didDocument | DidDocument | Signer's DID Document | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
void verifyProof(Object data, Proof proof, DidDocument didDocument) throws CommonSdkException
```

### Function Usage
```java
signatureService.verifyProof(data, proof, didDoc);
```
<br>

## 3.2.3 verifyDidDocumentProof

### Class Name
`SignatureService`

### Function Name
`verifyDidDocumentProof`

### Function Introduction
`Verifies the Proof of the DID Document itself.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDocument | DidDocument | DID Document to verify | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
void verifyDidDocumentProof(DidDocument didDocument) throws CommonSdkException
```

### Function Usage
```java
signatureService.verifyDidDocumentProof(didDoc);
```
<br>

## 3.3 TokenService

## 3.3.1 generateAccessToken

### Class Name
`TokenService`

### Function Name
`generateAccessToken`

### Function Introduction
`Generates an Access Token.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| tokenData | TokenData | Token data | M | |
| expirationSeconds | long | Expiration time (seconds) | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Generated Access Token string | M | |

### Function Declaration
```java
String generateAccessToken(TokenData tokenData, long expirationSeconds) throws CommonSdkException
```

### Function Usage
```java
String token = tokenService.generateAccessToken(data, 3600);
```
<br>

## 3.3.2 verifyAccessToken

### Class Name
`TokenService`

### Function Name
`verifyAccessToken`

### Function Introduction
`Verifies an Access Token and returns the token data.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| token | String | Access Token to verify | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| TokenData | Verified token data | M | |

### Function Declaration
```java
TokenData verifyAccessToken(String token) throws CommonSdkException
```

### Function Usage
```java
TokenData data = tokenService.verifyAccessToken(tokenStr);
```
<br>

## 3.3.3 refreshToken

### Class Name
`TokenService`

### Function Name
`refreshToken`

### Function Introduction
`Refreshes the token using a Refresh Token.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| refreshToken | String | Refresh Token | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Refreshed Access Token | M | |

### Function Declaration
```java
String refreshToken(String refreshToken) throws CommonSdkException
```

### Function Usage
```java
String newToken = tokenService.refreshToken(refreshTokenStr);
```
<br>

## 3.4 VcService

## 3.4.1 issueCredential

### Class Name
`VcService`

### Function Name
`issueCredential`

### Function Introduction
`Issues a Verifiable Credential (VC).`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| issueProfile | IssueProfile | Issue profile | M | |
| credentialSubject | CredentialSubject | Subject information to be included in the VC | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VerifiableCredential | Issued VC object | M | |

### Function Declaration
```java
VerifiableCredential issueCredential(IssueProfile issueProfile, CredentialSubject credentialSubject) throws CommonSdkException
```

### Function Usage
```java
VerifiableCredential vc = vcService.issueCredential(profile, subject);
```
<br>

## 3.4.2 verifyCredential

### Class Name
`VcService`

### Function Name
`verifyCredential`

### Function Introduction
`Verifies the signature and validity of a VC.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| vc | VerifiableCredential | VC to verify | M | |
| issuerDidDoc | DidDocument | Issuer's DID Document | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
void verifyCredential(VerifiableCredential vc, DidDocument issuerDidDoc) throws CommonSdkException
```

### Function Usage
```java
vcService.verifyCredential(vc, issuerDoc);
```
<br>

## 3.4.3 revokeCredential

### Class Name
`VcService`

### Function Name
`revokeCredential`

### Function Introduction
`Revokes a VC.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| vcId | String | VC ID to revoke | M | |
| reason | String | Revocation reason | M | |

### Output Parameters
n/a

### Function Declaration
```java
void revokeCredential(String vcId, String reason) throws CommonSdkException
```

### Function Usage
```java
vcService.revokeCredential("vc:1234", "Reason");
```
<br>

## 3.5 WalletService

## 3.5.1 createWallet

### Class Name
`WalletService`

### Function Name
`createWallet`

### Function Introduction
`Creates a new wallet.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| walletFilePath | String | Wallet file path | M | |
| password | String | Wallet password | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| WalletManagerInterface | Created WalletManagerInterface | M | |

### Function Declaration
```java
WalletManagerInterface createWallet(String walletFilePath, String password) throws CommonSdkException
```

### Function Usage
```java
WalletManagerInterface wallet = walletService.createWallet("/path/to/wallet", "password");
```
<br>

## 3.5.2 loadWallet

### Class Name
`WalletService`

### Function Name
`loadWallet`

### Function Introduction
`Loads an existing wallet.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| walletFilePath | String | Wallet file path | M | |
| password | String | Wallet password | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| WalletManagerInterface | Loaded WalletManagerInterface | M | |

### Function Declaration
```java
WalletManagerInterface loadWallet(String walletFilePath, String password) throws CommonSdkException
```

### Function Usage
```java
WalletManagerInterface wallet = walletService.loadWallet("/path/to/wallet", "password");
```
<br>

## 3.5.3 generateKey

### Class Name
`WalletService`

### Function Name
`generateKey`

### Function Introduction
`Generates a new key within the wallet.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| keyId | String | Key ID to generate | M | |
| curveType | EccCurveType | Elliptic curve type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Generated Key ID | M | |

### Function Declaration
```java
String generateKey(String keyId, EccCurveType curveType) throws CommonSdkException
```

### Function Usage
```java
String newKeyId = walletService.generateKey("key-1", EccCurveType.Secp256r1);
```
<br>

## 3.5.4 sign

### Class Name
`WalletService`

### Function Name
`sign`

### Function Introduction
`Generates a signature using a key from the wallet.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| keyId | String | Key ID to use for signing | M | |
| data | byte[] | Data to sign | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated signature data | M | |

### Function Declaration
```java
byte[] sign(String keyId, byte[] data) throws CommonSdkException
```

### Function Usage
```java
byte[] signature = walletService.sign("key-1", dataBytes);
```
<br>


# 5. APIs - Data Models

## 5.1 Enums

## 5.1.1 EccCurveType

### Enum Name
`EccCurveType`

### Enum Description
`Elliptic curve type enum used for ECC.`

### Enum Values
| Value | Description |
|---|---|
| Secp256k1 | Secp256k1 curve |
| Secp256r1 | Secp256r1(P-256) curve |

<br>

## 4.2 ECDH Data

## 4.2.1 AccEcdh

### Class Name
`AccEcdh`

### Class Description
`Class containing ECDH Accept response data.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| server | String | Server DID | M | |
| serverNonce | String | Server Nonce | M | |
| publicKey | String | Server public key | M | |
| cipher | SymmetricCipherType | Symmetric cipher type | M | |
| padding | SymmetricPaddingType | Symmetric padding type | M | |
| proof | Proof | Proof object | M | |

<br>

## 4.2.2 EcdhReqData

### Class Name
`EcdhReqData`

### Class Description
`Class containing ECDH request data.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| client | String | Client DID | M | |
| clientNonce | String | Client Nonce | M | |
| curve | EccCurveType | Elliptic curve type | M | |
| publicKey | String | Client public key | M | |
| candidate | Candidate | Cipher candidate list | M | |
| proof | Proof | Proof object | M | |

<br>

## 4.3 Signature Data

## 4.3.1 Proof

### Class Name
`Proof`

### Class Description
`Class containing digital signature (Proof) information.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| type | ProofType | Proof type | M | |
| created | String | Creation timestamp (UTC ISO 8601) | M | |
| verificationMethod | String | Verification method ID | M | |
| proofPurpose | ProofPurpose | Proof purpose | M | |
| proofValue | String | Signature value (Multibase encoded) | M | |

<br>

## 4.4 Token Data

## 4.4.1 TokenData

### Class Name
`TokenData`

### Class Description
`Token data class used in Token Service.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| expirationTime | long | Expiration time (timestamp) | M | |

<br>

## 4.5 VC Data

## 4.5.1 IssueProfile

### Class Name
`IssueProfile`

### Class Description
`Class containing VC issuance profile information.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| id | String | Profile ID | M | |
| type | ProfileType | Profile type | M | |
| title | String | Title | M | |
| description | String | Description | M | |
| encoding | Encoding | Encoding method | M | |
| language | Language | Language | M | |
| profile | VcProfile | Detailed profile information | M | |

<br>

## 4.5.2 VcProfile

### Class Name
`VcProfile`

### Class Description
`Class containing detailed VC profile information.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| id | String | Identifier | M | |
| credentialSchema | CredentialSchema | Credential schema information | M | |
| issuer | Issuer | Issuer information | M | |

<br>

## 4.5.3 VcMetadata

### Class Name
`VcMetadata`

### Class Description
`Class containing VC metadata. (Extends OmniOne VcMeta)`

### Fields
n/a (Inherits VcMeta)

<br>

## 4.5.4 CredentialSchema

### Class Name
`CredentialSchema`

### Class Description
`Class containing VC schema information.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| id | String | Schema ID | M | |
| type | CredentialSchemaType | Schema type | M | |
| value | String | Schema value/content | M | |

<br>

## 4.5.5 Issuer

### Class Name
`Issuer`

### Class Description
`Class containing VC issuer information.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| id | String | Issuer ID (DID) | M | |
| name | String | Issuer name | M | |
| url | String | Issuer URL | M | |
| certVcRef | String | Certificate VC reference URL | M | |

<br>

## 4.6 Did Data

## 4.6.1 DidDoc

### Class Name
`DidDoc`

### Class Description
`Wrapper class for DID Document.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| didDoc | DidDocument | DID Document object | M | |

<br>

## 4.6.2 AttestedDidDoc

### Class Name
`AttestedDidDoc`

### Class Description
`Class containing attested DID Document information.`

### Fields
| Name | Type | Description | M/O | Note |
|---|---|---|---|---|
| ownerDidDoc | DidDocument | Owner (Target) DID Document | M | |
| invokedDidDoc | InvokedDidDoc | Invoked DID Document | M | |
| proof | Proof | Proof object | M | |

<br>

## 4.1.2 SymmetricCipherType

### Enum Name
`SymmetricCipherType`

### Enum Description
`Enum for symmetric key encryption algorithms and modes.`

### Enum Values
| Value | Description |
|---|---|
| AES-128-CBC | AES 128 bit, CBC mode |
| AES-128-ECB | AES 128 bit, ECB mode |
| AES-256-CBC | AES 256 bit, CBC mode |
| AES-256-ECB | AES 256 bit, ECB mode |

<br>

## 4.1.3 ProofPurpose

### Enum Name
`ProofPurpose`

### Enum Description
`Enum representing the purpose of the Proof (signature).`

### Enum Values
| Value | Description |
|---|---|
| assertionMethod | Assertion Method (VC signing, etc.) |
| authentication | Authentication (Login, etc.) |
| keyAgreement | Key Agreement |
| capabilityInvocation | Capability Invocation |
| capabilityDelegation | Capability Delegation |

<br>

## 4.1.4 ProofType

### Enum Name
`ProofType`

### Enum Description
`Enum for algorithm types used in Proof generation.`

### Enum Values
| Value | Description |
|---|---|
| RsaSignature2018 | RSA Signature (2018) |
| Secp256k1Signature2018 | Secp256k1 ECDSA Signature (2018) |
| Secp256r1Signature2018 | Secp256r1 ECDSA Signature (2018) |

<br>

## 1.13 BaseMultibaseUtil

## 1.13.1 encode (Default)

### Class Name
`BaseMultibaseUtil`

### Function Name
`encode`

### Function Introduction
`Encodes data into a Multibase(Base58BTC) string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | byte[] | Data to encode | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Multibase encoded string | M | |

### Function Declaration
```java
static String encode(byte[] data) throws CommonSdkException
```

### Function Usage
```java
String encoded = BaseMultibaseUtil.encode(dataBytes);
```
<br>

## 1.13.2 encode (Typed)

### Class Name
`BaseMultibaseUtil`

### Function Name
`encode`

### Function Introduction
`Encodes data into the specified Multibase type string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | byte[] | Data to encode | M | |
| type | MultiBaseType | Multibase type | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Multibase encoded string | M | |

### Function Declaration
```java
static String encode(byte[] data, MultiBaseType type) throws CommonSdkException
```

### Function Usage
```java
String encoded = BaseMultibaseUtil.encode(dataBytes, MultiBaseType.base58btc);
```
<br>

## 1.13.3 decode

### Class Name
`BaseMultibaseUtil`

### Function Name
`decode`

### Function Introduction
`Decodes a Multibase string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| encoded | String | Multibase encoded string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Decoded data | M | |

### Function Declaration
```java
static byte[] decode(String encoded) throws CommonSdkException
```

### Function Usage
```java
byte[] data = BaseMultibaseUtil.decode(encodedString);
```
<br>

## 1.13.4 encodeBase64

### Class Name
`BaseMultibaseUtil`

### Function Name
`encodeBase64`

### Function Introduction
`Encodes data into a Base64 string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | byte[] | Data to encode | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| String | Base64 encoded string | M | |

### Function Declaration
```java
static String encodeBase64(byte[] data) throws CommonSdkException
```

### Function Usage
```java
String base64 = BaseMultibaseUtil.encodeBase64(dataBytes);
```
<br>

## 1.13.5 decodeBase64

### Class Name
`BaseMultibaseUtil`

### Function Name
`decodeBase64`

### Function Introduction
`Decodes a Base64 string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| encoded | String | Base64 encoded string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Decoded data | M | |

### Function Declaration
```java
static byte[] decodeBase64(String encoded) throws CommonSdkException
```

### Function Usage
```java
byte[] data = BaseMultibaseUtil.decodeBase64(base64String);
```
<br>

## 1.14 BaseDigestUtil

## 1.14.1 generateHash

### Class Name
`BaseDigestUtil`

### Function Name
`generateHash`

### Function Introduction
`Generates a SHA-256 hash for the data.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| message | String | Message string to hash | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated SHA-256 hash | M | |

### Function Declaration
```java
static byte[] generateHash(String message) throws CommonSdkException
```

### Function Usage
```java
byte[] hash = BaseDigestUtil.generateHash("message");
```
<br>

## 1.14.2 generateHash (Bytes)

### Class Name
`BaseDigestUtil`

### Function Name
`generateHash`

### Function Introduction
`Generates a SHA-256 hash for the data.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| data | byte[] | Data to hash | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated SHA-256 hash | M | |

### Function Declaration
```java
static byte[] generateHash(byte[] data) throws CommonSdkException
```

### Function Usage
```java
byte[] hash = BaseDigestUtil.generateHash(dataBytes);
```
<br>

## 1.14.3 generateHash512

### Class Name
`BaseDigestUtil`

### Function Name
`generateHash512`

### Function Introduction
`Generates a SHA-512 hash for the data.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| message | String | Message string to hash | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated SHA-512 hash | M | |

### Function Declaration
```java
static byte[] generateHash512(String message) throws CommonSdkException
```

### Function Usage
```java
byte[] hash = BaseDigestUtil.generateHash512("message");
```
<br>

## 1.14.4 generateHmac

### Class Name
`BaseDigestUtil`

### Function Name
`generateHmac`

### Function Introduction
`Generates an HMAC (HmacSHA256) value.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| key | byte[] | HMAC key | M | |
| data | byte[] | Data to sign | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| byte[] | Generated HMAC value | M | |

### Function Declaration
```java
static byte[] generateHmac(byte[] key, byte[] data) throws CommonSdkException
```

### Function Usage
```java
byte[] hmac = BaseDigestUtil.generateHmac(keyBytes, dataBytes);
```
<br>

## 1.15 BaseCoreVcUtil

## 1.15.1 parseVcSchema

### Class Name
`BaseCoreVcUtil`

### Function Name
`parseVcSchema`

### Function Introduction
`Parses a VC Schema JSON string into a VcSchema object.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| vcSchemaJson | String | VC Schema JSON string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VcSchema | Parsed VcSchema object | M | |

### Function Declaration
```java
static VcSchema parseVcSchema(String vcSchemaJson) throws CommonSdkException
```

### Function Usage
```java
VcSchema schema = BaseCoreVcUtil.parseVcSchema(jsonString);
```
<br>

## 1.15.2 setIssuer

### Class Name
`BaseCoreVcUtil`

### Function Name
`setIssuer`

### Function Introduction
`Sets issuer information in the VC issuance parameters.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC issuance parameters | M | |
| issuerDid | String | Issuer DID | M | |
| issuerName | String | Issuer name | M | |
| certVcRef | String | Certificate VC reference URL | M | |

### Output Parameters
n/a

### Function Declaration
```java
static void setIssuer(IssueVcParam issueVcParam, String issuerDid, String issuerName, String certVcRef)
```

### Function Usage
```java
BaseCoreVcUtil.setIssuer(param, "did:example:issuer", "Issuer Name", "http://example.com/vc");
```
<br>

## 1.15.3 setVcSchema

### Class Name
`BaseCoreVcUtil`

### Function Name
`setVcSchema`

### Function Introduction
`Sets the VC Schema in the VC issuance parameters.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC issuance parameters | M | |
| vcSchemaJson | String | VC Schema JSON string | M | |

### Output Parameters
n/a

### Function Declaration
```java
static void setVcSchema(IssueVcParam issueVcParam, String vcSchemaJson) throws CommonSdkException
```

### Function Usage
```java
BaseCoreVcUtil.setVcSchema(param, schemaJson);
```
<br>

## 1.15.4 setClaimInfo

### Class Name
`BaseCoreVcUtil`

### Function Name
`setClaimInfo`

### Function Introduction
`Sets claim information in the VC issuance parameters.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC issuance parameters | M | |
| claimInfoMap | HashMap&lt;String, ClaimInfo&gt; | Claim information map | M | |

### Output Parameters
n/a

### Function Declaration
```java
static void setClaimInfo(IssueVcParam issueVcParam, HashMap<String, ClaimInfo> claimInfoMap)
```

### Function Usage
```java
BaseCoreVcUtil.setClaimInfo(param, claims);
```
<br>

## 1.15.5 setVcTypes

### Class Name
`BaseCoreVcUtil`

### Function Name
`setVcTypes`

### Function Introduction
`Sets VC types in the VC issuance parameters.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC issuance parameters | M | |
| vcTypeList | List&lt;VcType&gt; | VC type list | M | |

### Output Parameters
n/a

### Function Declaration
```java
static void setVcTypes(IssueVcParam issueVcParam, List<VcType> vcTypeList)
```

### Function Usage
```java
BaseCoreVcUtil.setVcTypes(param, types);
```
<br>

## 1.15.6 setEvidence

### Class Name
`BaseCoreVcUtil`

### Function Name
`setEvidence`

### Function Introduction
`Sets Evidence information in the VC issuance parameters.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC issuance parameters | M | |
| evidence | DocumentVerificationEvidence | Document verification evidence object | M | |

### Output Parameters
n/a

### Function Declaration
```java
static void setEvidence(IssueVcParam issueVcParam, DocumentVerificationEvidence evidence)
```

### Function Usage
```java
BaseCoreVcUtil.setEvidence(param, evidence);
```
<br>

## 1.15.7 generateVc

### Class Name
`BaseCoreVcUtil`

### Function Name
`generateVc`

### Function Introduction
`Generates a Verifiable Credential (VC) with the input parameters.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC issuance parameters | M | |
| did | String | Holder DID | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VerifiableCredential | Generated VC object | M | |

### Function Declaration
```java
static VerifiableCredential generateVc(IssueVcParam issueVcParam, String did) throws CommonSdkException
```

### Function Usage
```java
VerifiableCredential vc = BaseCoreVcUtil.generateVc(param, "did:example:holder");
```
<br>

## 1.15.8 generateVcMeta

### Class Name
`BaseCoreVcUtil`

### Function Name
`generateVcMeta`

### Function Introduction
`Generates VC metadata.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| vc | VerifiableCredential | Target VC for metadata generation | M | |
| certVcRef | String | Certificate VC reference URL | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VcMeta | Generated VC metadata | M | |

### Function Declaration
```java
static VcMeta generateVcMeta(VerifiableCredential vc, String certVcRef)
```

### Function Usage
```java
VcMeta meta = BaseCoreVcUtil.generateVcMeta(vc, "http://example.com/vc");
```
<br>

## 1.15.9 extractVcSignatureMessage

### Class Name
`BaseCoreVcUtil`

### Function Name
`extractVcSignatureMessage`

### Function Introduction
`Extracts the original message for VC signing.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| tasDidDoc | DidDocument | TAS DID Document | M | |
| verifiableCredential | VerifiableCredential | VC to sign | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| List&lt;SignatureVcParams&gt; | Signature parameters list | M | |

### Function Declaration
```java
static List<SignatureVcParams> extractVcSignatureMessage(DidDocument tasDidDoc, VerifiableCredential verifiableCredential) throws CommonSdkException
```

### Function Usage
```java
List<SignatureVcParams> params = BaseCoreVcUtil.extractVcSignatureMessage(tasDoc, vc);
```
<br>

## 1.15.10 setVcProof

### Class Name
`BaseCoreVcUtil`

### Function Name
`setVcProof`

### Function Introduction
`Sets the generated Proof (signature) in the VC.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| verifiableCredential | VerifiableCredential | VC to set Proof on | M | |
| signatureParamsList | List&lt;SignatureVcParams&gt; | Signature parameters list (including signature value) | M | |

### Output Parameters
n/a

### Function Declaration
```java
static void setVcProof(VerifiableCredential verifiableCredential, List<SignatureVcParams> signatureParamsList) throws CommonSdkException
```

### Function Usage
```java
BaseCoreVcUtil.setVcProof(vc, signedParams);
```
<br>

## 1.15.11 parseVcMeta

### Class Name
`BaseCoreVcUtil`

### Function Name
`parseVcMeta`

### Function Introduction
`Parses a VC metadata JSON string.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| vcMetaJson | String | VC metadata JSON string | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VcMeta | Parsed VcMeta object | M | |

### Function Declaration
```java
static VcMeta parseVcMeta(String vcMetaJson) throws CommonSdkException
```

### Function Usage
```java
VcMeta meta = BaseCoreVcUtil.parseVcMeta(jsonString);
```
<br>

## 1.15.12 validateVerifiableCredential

### Class Name
`BaseCoreVcUtil`

### Function Name
`validateVerifiableCredential`

### Function Introduction
`Validates the structural validity of the VC.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| verifiableCredential | VerifiableCredential | VC to validate | M | |

### Output Parameters
n/a (Throws exception if invalid)

### Function Declaration
```java
static void validateVerifiableCredential(VerifiableCredential verifiableCredential) throws CommonSdkException
```

### Function Usage
```java
BaseCoreVcUtil.validateVerifiableCredential(vc);
```
<br>

## 1.15.13 verifyVc

### Class Name
`BaseCoreVcUtil`

### Function Name
`verifyVc`

### Function Introduction
`Verifies the signature and validity of the VC.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| verifiableCredential | VerifiableCredential | VC to verify | M | |
| didDocument | DidDocument | Issuer's DID Document | M | |
| isRevocationCheck | boolean | Whether to check revocation | M | |

### Output Parameters
n/a (Throws exception if verification fails)

### Function Declaration
```java
static void verifyVc(VerifiableCredential verifiableCredential, DidDocument didDocument, boolean isRevocationCheck) throws CommonSdkException
```

### Function Usage
```java
BaseCoreVcUtil.verifyVc(vc, issuerDoc, true);
```
<br>

## 1.15.14 signVerifiableCredential

### Class Name
`BaseCoreVcUtil`

### Function Name
`signVerifiableCredential`

### Function Introduction
`Signs the VC and returns it.`

### Input Parameters
| Parameter | Type | Description | M/O | Note |
|---|---|---|---|---|
| vcManager | VcManager | VC manager | M | |
| walletManager | WalletManagerInterface | Wallet manager (provides signing function) | M | |
| issuerDidDoc | DidDocument | Issuer DID Document | M | |
| keyId | String | Key ID to sign with | M | |
| vc | VerifiableCredential | VC to sign | M | |

### Output Parameters
| Type | Description | M/O | Note |
|---|---|---|---|
| VerifiableCredential | Signed VC | M | |

### Function Declaration
```java
static VerifiableCredential signVerifiableCredential(VcManager vcManager, WalletManagerInterface walletManager, DidDocument issuerDidDoc, String keyId, VerifiableCredential vc) throws CommonSdkException
```

### Function Usage
```java
VerifiableCredential signedVc = BaseCoreVcUtil.signVerifiableCredential(vcMgr, walletMgr, issuerDoc, "key-1", vc);
```
<br>

# 6. APIs - Exceptions

## 6.1 CommonSdkException

### Class Name
`CommonSdkException`

### Class Description
`Custom exception class for dealing with OpenDID-related errors. It encapsulates an ErrorCode to provide detailed error information.`

<br>

## 5.2 HttpClientException

### Class Name
`HttpClientException`

### Class Description
`Exception class occurring during HTTP communication.`

<br>

## 5.3 ErrorCode

### Enum Name
`ErrorCode`

### Enum Description
`Enumeration of error codes used in the DID Verifier system.`

### Enum Values
`Refer to the source code or separate documentation for detailed error codes.`

<br>

## 5.4 ErrorResponse

### Class Name
`ErrorResponse`

### Class Description
`Error Response Object.`

<br>

# 6. Reference Classes


