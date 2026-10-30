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

- 주제: Server Common SDK API
- 작성: OmniOne
- 일자: 2024-09-02
- 버전: v1.0.0

| 버전   | 일자       | 변경 내용                 |
| ------ | ---------- | -------------------------|
| v1.0.0 | 2024-09-02 | 초기 작성                 |


<div style="page-break-after: always;"></div>

# 목차
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
`현재 UTC 시간을 ISO 8601 형식("yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'")의 문자열로 반환합니다.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | ISO 8601 형식의 현재 UTC 시간 문자열 | M | |

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
`현재 UTC 시간을 ZonedDateTime 객체로 반환합니다.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| ZonedDateTime | 현재 UTC 시간 객체 | M | |

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
`현재 시간에 지정된 시간을 더한 결과를 ISO 8601 형식의 문자열로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| hours | int | 더할 시간(hour) | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 계산된 시간 문자열 | M | |

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
`현재 시간에 지정된 년수를 더한 결과를 ZonedDateTime 객체로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| years | int | 더할 년수(year) | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| ZonedDateTime | 계산된 시간 객체 | M | |

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
`현재 시간에 지정된 단위의 시간을 더한 결과를 ISO 8601 형식의 문자열로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| amountToAdd | long | 더할 시간 양 | M | |
| unit | ChronoUnit | 시간 단위 (예: ChronoUnit.HOURS) | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 계산된 시간 문자열 | M | |

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
`입력된 시간이 현재 시간보다 이전인지(만료되었는지) 확인합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| expiredAt | Instant | 만료 시간 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| boolean | 만료 여부 (true: 만료됨) | M | |

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
`UTC 날짜 시간 문자열을 파싱하여 Instant 객체로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| timeString | String | UTC 날짜 시간 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| Instant | 파싱된 Instant 객체 | M | |

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
`UTC 날짜 시간 문자열을 "yyyy-MM-dd HH:mm:ss" 형식의 문자열로 변환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| utcDateTime | String | UTC 날짜 시간 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 포맷팅된 문자열 | M | |

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
`UTC 날짜 시간 문자열을 지정된 형식의 문자열로 변환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| utcDateTime | String | UTC 날짜 시간 문자열 | M | |
| format | String | 변환할 포맷 형식 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 포맷팅된 문자열 | M | |

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
`두 번째 날짜 시간이 첫 번째 날짜 시간보다 이후인지 확인합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| firstUtcDateTime | String | 첫 번째 UTC 날짜 시간 문자열 | M | |
| secondUtcDateTime | String | 두 번째 UTC 날짜 시간 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| boolean | 두 번째 시간이 더 늦으면 true | M | |

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
`최대 UTC 시간(9999-12-31 23:59:59)을 Instant 객체로 반환합니다.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| Instant | 최대 UTC 시간 Instant 객체 | M | |

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
`현재 시간에 지정된 분을 더한 결과를 ISO 8601 형식의 문자열로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| minutes | int | 더할 분(minute) | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 계산된 시간 문자열 | M | |

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
`현재 시간에 지정된 초를 더한 결과를 ISO 8601 형식의 문자열로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| seconds | int | 더할 초(second) | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 계산된 시간 문자열 | M | |

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
`DID Key URL에서 쿼리 파라미터나 프래그먼트를 제거한 순수 DID를 추출합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didKeyUrl | String | 전체 DID Key URL | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 추출된 DID | M | |

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
`DID Key URL에서 Key ID(프래그먼트 이후 부분)를 추출합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didKeyUrl | String | 전체 DID Key URL | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 추출된 Key ID | M | |

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
`주어진 DID 문자열의 형식이 유효한지 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| did | String | 검증할 DID 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| boolean | 유효하면 true | M | |

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
`주어진 DID Key URL 문자열의 형식이 유효한지 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didKeyUrl | String | 검증할 DID Key URL 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| boolean | 유효하면 true | M | |

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
`AttributeDef.ATTR_TYPE에 대한 커스텀 직렬화/역직렬화가 등록된 Gson 객체를 생성합니다.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| Gson | 커스텀 설정된 Gson 객체 | M | |

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
`JSON 문자열을 파싱하여 CredentialSchema 객체로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| json | String | CredentialSchema JSON 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| CredentialSchema | 파싱된 CredentialSchema 객체 | M | |

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
`바이트 배열을 16진수 문자열로 변환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| bytes | byte[] | 변환할 바이트 배열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 16진수 문자열 | M | |

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
`주어진 URL로 HTTP GET 요청을 보내고 응답을 문자열로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| url | String | 요청할 URL | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 응답 바디 문자열 | M | |

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
`주어진 URL로 HTTP GET 요청을 보내고 응답을 지정된 클래스 타입으로 파싱하여 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| url | String | 요청할 URL | M | |
| responseType | Class&lt;T&gt; | 응답을 매핑할 클래스 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| T | 파싱된 응답 객체 | M | |

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
`주어진 URL로 HTTP POST 요청을 보내고 응답을 문자열로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| url | String | 요청할 URL | M | |
| requestBody | String | 요청 바디 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 응답 바디 문자열 | M | |

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
`주어진 URL로 HTTP POST 요청을 보내고 응답을 지정된 클래스 타입으로 파싱하여 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| url | String | 요청할 URL | M | |
| requestBody | String | 요청 바디 문자열 | M | |
| responseType | Class&lt;T&gt; | 응답을 매핑할 클래스 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| T | 파싱된 응답 객체 | M | |

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
`랜덤한 Transaction ID(UUID)를 생성합니다.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 생성된 Transaction ID | M | |

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
`랜덤한 Offer ID(UUID)를 생성합니다.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 생성된 Offer ID | M | |

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
`객체를 JSON 문자열로 직렬화하고 키를 정렬한 후 공백을 제거하여 반환합니다.(서명 원문 생성용)`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| obj | Object | 직렬화할 객체 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 정렬되고 공백이 제거된 JSON 문자열 | M | |

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
`객체를 JSON 문자열로 직렬화합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| obj | Object | 직렬화할 객체 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | JSON 문자열 | M | |

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
`JSON 문자열을 지정된 클래스 타입의 객체로 역직렬화합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| jsonString | String | JSON 문자열 | M | |
| clazz | Class&lt;T&gt; | 역직렬화할 클래스 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| T | 역직렬화된 객체 | M | |

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
`JSON 문자열을 TypeReference를 사용하여 복잡한 제네릭 타입의 객체로 역직렬화합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| jsonString | String | JSON 문자열 | M | |
| typeReference | TypeReference&lt;T&gt; | 역직렬화할 타입 레퍼런스 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| T | 역직렬화된 객체 | M | |

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
`16바이트 길이의 랜덤한 Nonce를 생성합니다.`

### Input Parameters
n/a

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 16바이트 Nonce | M | |

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
`객체를 JSON으로 직렬화한 후 QR 코드 이미지를 생성하여 QrImageData 객체로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | Object | QR 코드로 변환할 데이터 객체 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| QrImageData | 생성된 QR 이미지 데이터 | M | |

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
`문자열 내용을 QR 코드 이미지(byte array)로 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| contents | String | QR 코드로 변환할 문자열 내용 | M | |
| format | String | 이미지 포맷 (예: "PNG") | M | |
| width | int | 이미지 너비 | M | |
| height | int | 이미지 높이 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 QR 이미지 바이트 배열 | M | |

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
`문자열 내용을 QR 코드 BitMatrix로 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| contents | String | QR 코드로 변환할 문자열 내용 | M | |
| width | int | 이미지 너비 | M | |
| height | int | 이미지 높이 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| BitMatrix | 생성된 QR BitMatrix | M | |

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
`데이터를 QR 코드로 변환하여 Base64 인코딩된 문자열(Data URI Scheme)로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | String | QR 코드로 변환할 데이터 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | Base64 인코딩된 QR 이미지 문자열 | M | |

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
`객체의 유효성을 검증합니다. (Jakarta Bean Validation)`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| obj | T | 검증할 객체 | M | |

### Output Parameters
n/a (유효하지 않으면 예외 발생)

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
`블록체인 네트워크에 연결합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| config | BlockChainConfig | 블록체인 설정 정보 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| BlockChainManagerInterface | 블록체인 매니저 인터페이스 | M | |

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
`블록체인에 DID Document를 등록합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | 블록체인 매니저 | M | |
| did | String | 등록할 DID | M | |
| didDocument | DidDocument | 등록할 DID Document 객체 | M | |

### Output Parameters
n/a (실패 시 예외 발생)

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
`블록체인에서 DID Document를 조회합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | 블록체인 매니저 | M | |
| did | String | 조회할 DID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| DidDocument | 조회된 DID Document | M | |

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
`블록체인에 VC 메타데이터를 등록합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | 블록체인 매니저 | M | |
| vcId | String | VC 식별자 | M | |
| vcMetadata | VcMetadata | VC 메타데이터 | M | |

### Output Parameters
n/a (실패 시 예외 발생)

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
`블록체인에서 VC 메타데이터를 조회합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | 블록체인 매니저 | M | |
| vcId | String | 조회할 VC 식별자 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VcMetadata | 조회된 VC 메타데이터 | M | |

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
`DID Document의 상태를 업데이트합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | 블록체인 매니저 | M | |
| did | String | 대상 DID | M | |
| status | DidDocStatus | 변경할 상태 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| DidDocument | 업데이트된 DID Document | M | |

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
`VC의 상태를 업데이트합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| blockChain | BlockChainManagerInterface | 블록체인 매니저 | M | |
| vcId | String | 대상 VC 식별자 | M | |
| status | VcStatus | 변경할 상태 | M | |

### Output Parameters
n/a (실패 시 예외 발생)

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
`지정된 ECC 곡선 타입을 사용하여 키 페어를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| curveType | EccCurveType | ECC 곡선 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| KeyPairInterface | 생성된 키 페어 인터페이스 | M | |

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
`공개키와 개인키를 사용하여 공유 비밀키(Shared Secret)를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| publicKey | byte[] | 상대방의 공개키 | M | |
| privateKey | byte[] | 자신의 개인키 | M | |
| curveType | EccCurveType | ECC 곡선 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 공유 비밀키 | M | |

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
`지정된 길이의 난수(Nonce)를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| length | int | 생성할 Nonce 길이 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 Nonce | M | |

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
`공개키를 사용하여 디지털 서명을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| encodedPublicKey | String | 인코딩된 공개키 | M | |
| signature | String | 검증할 서명 | M | |
| message | byte[] | 원본 메시지 | M | |
| curveType | EccCurveType | ECC 곡선 타입 | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`공개키를 압축합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| publicKey | byte[] |압축되지 않은 공개키 | M | |
| curveType | EccCurveType | ECC 곡선 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 압축된 공개키 | M | |

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
`압축된 공개키를 압축 해제합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| compressedKey | byte[] | 압축된 공개키 | M | |
| curveType | EccCurveType | ECC 곡선 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 압축 해제된 공개키 | M | |

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
`공유 비밀키와 Nonce를 병합하여 세션 키를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| sharedSecret | byte[] | 공유 비밀키 | M | |
| nonce | byte[] | Nonce | M | |
| cipherType | SymmetricCipherType | 대칭키 암호화 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 세션 키 | M | |

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
`데이터를 대칭키로 암호화합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| plainText | byte[] | 암호화할 평문 데이터 | M | |
| key | byte[] | 암호화 키 | M | |
| iv | byte[] | 초기화 벡터(IV) | M | |
| cipherType | SymmetricCipherType | 대칭키 암호화 타입 | M | |
| paddingType | SymmetricPaddingType | 패딩 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 암호화된 데이터 | M | |

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
`암호화된 데이터를 대칭키로 복호화합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| cipherText | byte[] | 복호화할 암호문 데이터 | M | |
| key | byte[] | 복호화 키 | M | |
| iv | byte[] | 초기화 벡터(IV) | M | |
| cipherType | SymmetricCipherType | 대칭키 암호화 타입 | M | |
| paddingType | SymmetricPaddingType | 패딩 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 복호화된 데이터 | M | |

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
`DID Document JSON 문자열을 파싱하여 DidManager를 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDocJson | String | DID Document JSON 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| DidManager | DidManager 인스턴스 | M | |

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
`DID Document 객체를 파싱합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDocument | DidDocument | DID Document 객체 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| DidManager | DidManager 인스턴스 | M | |

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
`DidManager에서 Key ID로 Verification Method를 조회합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didManager | DidManager | DidManager 인스턴스 | M | |
| keyId | String | 조회할 Key ID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VerificationMethod | 조회된 Verification Method | M | |

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
`DidDocument에서 Key ID로 Verification Method를 조회합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDocument | DidDocument | DidDocument 객체 | M | |
| keyId | String | 조회할 Key ID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VerificationMethod | 조회된 Verification Method | M | |

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
`DID Document에서 특정 Key ID에 해당하는 공개키를 조회합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDocument | DidDocument | DidDocument 객체 | M | |
| keyId | String | 조회할 Key ID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 공개키 (Multibase 등 저장된 포맷) | M | |

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
`DID Document 내부의 Key Proof들을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDocument | DidDocument | 검증할 DID Document | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`새로운 DID Document를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didManager | DidManager | DidManager 인스턴스 | M | |
| did | String | 생성할 DID | M | |
| controller | String | Controller DID | M | |
| didKeyInfos | List&lt;DidKeyInfo&gt; | 키 정보 목록 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| DidDocument | 생성된 DID Document | M | |

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
`DID Document에 서명을 수행하고 Proof를 추가합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didManager | DidManager | 문서를 포함한 DidManager | M | |
| walletManager | WalletManagerInterface | 서명에 사용할 월렛 매니저 | M | |
| signingKeyIds | List&lt;String&gt; | 서명할 Key ID 목록 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| DidDocument | 서명된 DID Document | M | |

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
`JWT 토큰을 Header, Payload, Signature 부분으로 분리합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| jwtToken | String | 분리할 JWT 토큰 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| JwtComponents | 분리된 JWT 구성 요소 | M | |

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
`JWT 토큰의 서명을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| jwtComponents | JwtComponents | JWT 구성 요소 | M | |
| publicKey | String | 검증할 공개키 (Multibase) | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`토큰을 검증하고 페이로드에서 PII(개인식별정보)를 추출합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| accessToken | String | 검증할 액세스 토큰 | M | |
| didDocument | DidDocument | 서명 검증용 DID Document | M | |
| keyId | String | 서명 검증 키 ID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 추출된 PII (JSON 문자열) | M | |

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
`액세스 토큰을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| payload | String | 토큰 페이로드 (JSON 문자열) | M | |
| walletManager | WalletManagerInterface | 월렛 매니저 | M | |
| keyId | String | 서명할 키 ID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 생성된 액세스 토큰 (JWT) | M | |

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
`액세스 토큰의 유효성을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| accessToken | String | 검증할 액세스 토큰 | M | |
| didDocument | DidDocument | 검증용 DID Document | M | |
| keyId | String | 검증 키 ID | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`새로운 월렛을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| walletPath | String | 월렛 저장 경로 | M | |
| password | char[] | 월렛 비밀번호 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| WalletManagerInterface | 생성된 월렛 매니저 | M | |

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
`기존 월렛을 로드합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| walletPath | String | 월렛 파일 경로 | M | |
| password | char[] | 월렛 비밀번호 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| WalletManagerInterface | 로드된 월렛 매니저 | M | |

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
`월렛 내에 새로운 키를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| walletManager | WalletManagerInterface | 월렛 매니저 | M | |
| keyId | String | 생성할 키 ID | M | |
| algType | String | 알고리즘 타입 (예: "Secp256r1") | M | |

### Output Parameters
n/a (실패 시 예외 발생)

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
`데이터에 서명하여 Compact Signature를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| walletManager | WalletManagerInterface | 월렛 매니저 | M | |
| keyId | String | 서명할 키 ID | M | |
| data | byte[] | 서명할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 서명 데이터 | M | |

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
`DID Document의 서명을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDocument | DidDocument | 검증할 DID Document | M | |
| verificationMethodId | String | 검증에 사용할 Verification Method ID | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`InvokedDidDoc 객체를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| invokerDidDoc | DidDocument | 호출자(Invoker) DID Document | M | |
| ownerDidDoc | DidDocument | 소유자(Target) DID Document | M | |
| proofType | ProofType | Proof 타입 | M | |
| certificateUrl | String | 인증서 URL (Optional) | O | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| InvokedDidDoc | 생성된 InvokedDidDoc 객체 | M | |

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
`InvokedDidDoc에 서명합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| unsignedInvokedDidDoc | InvokedDidDoc | 서명되지 않은 InvokedDidDoc | M | |
| walletManager | WalletManagerInterface | 월렛 매니저 | M | |
| keyId | String | 서명할 Key ID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| InvokedDidDoc | 서명된 InvokedDidDoc | M | |

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
`ECDH를 통해 세션 키를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| clientPublicKey | byte[] | 클라이언트 공개키 | M | |
| serverPrivateKey | byte[] | 서버 개인키 | M | |
| mergedNonce | byte[] | 병합된 Nonce | M | |
| cipherType | SymmetricCipherType | 대칭키 암호화 타입 | M | |
| curveType | EccCurveType | 타원 곡선 타입 | M | |
| applyNonceHashing | boolean | Nonce 해싱 여부 | O | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 세션 키 | M | |

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
`생성된 세션 키가 예상된 키와 일치하는지 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| sessionKey | byte[] | 생성된 세션 키 | M | |
| expectedSessionKey | byte[] | 예상되는 세션 키 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| boolean | 일치하면 true | M | |

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
`후보 목록에서 서버가 지원하는 암호화 타입을 선택합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| candidate | Candidate | 암호화 후보 목록 | M | |
| serverCipherType | SymmetricCipherType | 서버의 선호 암호화 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| SymmetricCipherType | 선택된 암호화 타입 | M | |

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
`디지털 서명을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| encodedPublicKey | String | 인코딩된 공개키 | M | |
| signature | String | 검증할 서명 | M | |
| message | byte[] | 원본 메시지 | M | |
| proofType | ProofType | Proof 타입 | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`서명 메시지(Canonical JSON의 해시)를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | Object | 서명할 데이터 객체 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 서명 메시지 해시 | M | |

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
`데이터에서 Proof 값을 제거하여 서명 전 상태로 복원합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | T | Proof를 제거할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| T | Proof가 제거된 데이터 (Deep Copy) | M | |

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
`데이터에서 Proof를 제거하고 서명 메시지를 추출합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | T | 서명 메시지를 추출할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 서명 메시지 해시 | M | |

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
`ECDH 요청 데이터를 처리하여 서버의 키와 Nonce를 포함한 응답(AccEcdh)을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| ecdhReqData | EcdhReqData | ECDH 요청 데이터 | M | |
| serverCipherType | SymmetricCipherType | 서버 암호화 타입 | M | |
| serverPaddingType | SymmetricPaddingType | 서버 패딩 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| AccEcdh | ECDH 수락 응답 데이터 | M | |

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
`ECDH 요청 데이터의 유효성과 클라이언트 DID를 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| ecdhReqData | EcdhReqData | ECDH 요청 데이터 | M | |
| expectedClientDid | String | 검증할 클라이언트 DID | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`ECDH 키 교환을 통해 세션 키를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| ecdhReqData | EcdhReqData | ECDH 요청 데이터 | M | |
| serverPrivateKey | byte[] | 서버 개인키 | M | |
| mergedNonce | byte[] | 병합된 Nonce (Client Nonce + Server Nonce) | M | |
| serverCipherType | SymmetricCipherType | 서버 암호화 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 세션 키 | M | |

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
`데이터에 대한 Proof(서명)를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | Object | 서명할 데이터 | M | |
| proofPurpose | ProofPurpose | Proof 목적 | M | |
| proofType | ProofType | Proof 타입 | M | |
| verificationMethod | String | 검증 메소드(Key ID) | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| Proof | 생성된 Proof 객체 | M | |

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
`데이터에 포함된 Proof를 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | Object | 검증할 데이터 | M | |
| proof | Proof | 검증할 Proof 객체 | M | |
| didDocument | DidDocument | 서명자 DID Document | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`DID Document 자체의 Proof를 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDocument | DidDocument | 검증할 DID Document | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`Access Token을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| tokenData | TokenData | 토큰 데이터 | M | |
| expirationSeconds | long | 만료 시간(초) | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 생성된 Access Token 문자열 | M | |

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
`Access Token을 검증하고 토큰 데이터를 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| token | String | 검증할 Access Token | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| TokenData | 검증된 토큰 데이터 | M | |

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
`Refresh Token을 사용하여 토큰을 갱신합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| refreshToken | String | Refresh Token | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 갱신된 Access Token | M | |

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
`Verifiable Credential(VC)을 발급합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| issueProfile | IssueProfile | 발급 프로파일 | M | |
| credentialSubject | CredentialSubject | VC에 포함될 주제(Subject) 정보 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VerifiableCredential | 발급된 VC 객체 | M | |

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
`VC의 서명과 유효성을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| vc | VerifiableCredential | 검증할 VC | M | |
| issuerDidDoc | DidDocument | 발급자 DID Document | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`VC를 폐기합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| vcId | String | 폐기할 VC ID | M | |
| reason | String | 폐기 사유 | M | |

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
`새로운 월렛을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| walletFilePath | String | 월렛 파일 경로 | M | |
| password | String | 월렛 비밀번호 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| WalletManagerInterface | 생성된 월렛 매니저 인터페이스 | M | |

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
`기존 월렛을 로드합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| walletFilePath | String | 월렛 파일 경로 | M | |
| password | String | 월렛 비밀번호 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| WalletManagerInterface | 로드된 월렛 매니저 인터페이스 | M | |

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
`월렛 내에 새로운 키를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| keyId | String | 생성할 키 ID | M | |
| curveType | EccCurveType | 타원 곡선 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | 생성된 키 ID | M | |

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
`월렛의 키를 사용하여 서명을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| keyId | String | 서명에 사용할 키 ID | M | |
| data | byte[] | 서명할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 서명 데이터 | M | |

### Function Declaration
```java
byte[] sign(String keyId, byte[] data) throws CommonSdkException
```

### Function Usage
```java
byte[] signature = walletService.sign("key-1", dataBytes);
```
<br>


# 4. APIs - Data Models

## 4.1 Enums

## 4.1.1 EccCurveType

### Enum Name
`EccCurveType`

### Enum Description
`타원 곡선 암호화(ECC)에 사용되는 곡선 타입 열거형입니다.`

### Enum Values
| Value | Description |
|---|---|
| Secp256k1 | Secp256k1 곡선 |
| Secp256r1 | Secp256r1(P-256) 곡선 |

<br>

## 4.2 ECDH Data

## 4.2.1 AccEcdh

### Class Name
`AccEcdh`

### Class Description
`ECDH 수락(Accept) 응답 데이터를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| server | String | 서버 DID | M | |
| serverNonce | String | 서버 Nonce | M | |
| publicKey | String | 서버 공개키 | M | |
| cipher | SymmetricCipherType | 대칭키 암호화 타입 | M | |
| padding | SymmetricPaddingType | 대칭키 패딩 타입 | M | |
| proof | Proof | Proof 객체 | M | |

<br>

## 4.2.2 EcdhReqData

### Class Name
`EcdhReqData`

### Class Description
`ECDH 요청 데이터를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| client | String | 클라이언트 DID | M | |
| clientNonce | String | 클라이언트 Nonce | M | |
| curve | EccCurveType | 타원 곡선 타입 | M | |
| publicKey | String | 클라이언트 공개키 | M | |
| candidate | Candidate | 암호화 알고리즘 후보군 | M | |
| proof | Proof | Proof 객체 | M | |

<br>

## 4.3 Signature Data

## 4.3.1 Proof

### Class Name
`Proof`

### Class Description
`디지털 서명(Proof) 정보를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| type | ProofType | Proof 타입 | M | |
| created | String | 생성 일시 (UTC ISO 8601) | M | |
| verificationMethod | String | 검증 메소드 ID | M | |
| proofPurpose | ProofPurpose | Proof 목적 | M | |
| proofValue | String | 서명 값 (Multibase 인코딩) | M | |

<br>

## 4.4 Token Data

## 4.4.1 TokenData

### Class Name
`TokenData`

### Class Description
`토큰 서비스에서 사용되는 토큰 데이터 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| expirationTime | long | 만료 시간(타임스탬프) | M | |

<br>

## 4.5 VC Data

## 4.5.1 IssueProfile

### Class Name
`IssueProfile`

### Class Description
`VC 발급 프로파일 정보를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| id | String | 프로파일 ID | M | |
| type | ProfileType | 프로파일 타입 | M | |
| title | String | 제목 | M | |
| description | String | 설명 | M | |
| encoding | Encoding | 인코딩 방식 | M | |
| language | Language | 언어 | M | |
| profile | VcProfile | 세부 프로파일 정보 | M | |

<br>

## 4.5.2 VcProfile

### Class Name
`VcProfile`

### Class Description
`VC 프로파일의 상세 정보를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| id | String | 식별자 | M | |
| credentialSchema | CredentialSchema | Credential 스키마 정보 | M | |
| issuer | Issuer | 발급자 정보 | M | |

<br>

## 4.5.3 VcMetadata

### Class Name
`VcMetadata`

### Class Description
`VC의 메타데이터를 담는 클래스입니다. (OmniOne VcMeta 확장)`

### Fields
n/a (VcMeta 상속)

<br>

## 4.5.4 CredentialSchema

### Class Name
`CredentialSchema`

### Class Description
`VC의 스키마 정보를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| id | String | 스키마 ID | M | |
| type | CredentialSchemaType | 스키마 타입 | M | |
| value | String | 스키마 값/내용 | M | |

<br>

## 4.5.5 Issuer

### Class Name
`Issuer`

### Class Description
`VC 발급자 정보를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| id | String | 발급자 ID (DID) | M | |
| name | String | 발급자 이름 | M | |
| url | String | 발급자 URL | M | |
| certVcRef | String | 인증서 VC 참조 URL | M | |

<br>

## 4.6 Did Data

## 4.6.1 DidDoc

### Class Name
`DidDoc`

### Class Description
`DID Document를 감싸는 래퍼 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| didDoc | DidDocument | DID Document 객체 | M | |

<br>

## 4.6.2 AttestedDidDoc

### Class Name
`AttestedDidDoc`

### Class Description
`증명된 DID Document 정보를 담는 클래스입니다.`

### Fields
| Name | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| ownerDidDoc | DidDocument | 소유자 DID Document | M | |
| invokedDidDoc | InvokedDidDoc | 호출된 DID Document | M | |
| proof | Proof | Proof 객체 | M | |

<br>

## 4.1.2 SymmetricCipherType

### Enum Name
`SymmetricCipherType`

### Enum Description
`대칭키 암호화 알고리즘 및 모드 열거형입니다.`

### Enum Values
| Value | Description |
|---|---|
| AES-128-CBC | AES 128 bit, CBC 모드 |
| AES-128-ECB | AES 128 bit, ECB 모드 |
| AES-256-CBC | AES 256 bit, CBC 모드 |
| AES-256-ECB | AES 256 bit, ECB 모드 |

<br>

## 4.1.3 ProofPurpose

### Enum Name
`ProofPurpose`

### Enum Description
`Proof(서명)의 목적을 나타내는 열거형입니다.`

### Enum Values
| Value | Description |
|---|---|
| assertionMethod | Assertion Method (VC 서명 등) |
| authentication | Authentication (로그인 등) |
| keyAgreement | Key Agreement (키 교환) |
| capabilityInvocation | Capability Invocation (권한 행사) |
| capabilityDelegation | Capability Delegation (권한 위임) |

<br>

## 4.1.4 ProofType

### Enum Name
`ProofType`

### Enum Description
`Proof(서명) 생성에 사용된 알고리즘 타입 열거형입니다.`

### Enum Values
| Value | Description |
|---|---|
| RsaSignature2018 | RSA 서명 (2018) |
| Secp256k1Signature2018 | Secp256k1 ECDSA 서명 (2018) |
| Secp256r1Signature2018 | Secp256r1 ECDSA 서명 (2018) |

<br>

## 1.13 BaseMultibaseUtil

## 1.13.1 encode (Default)

### Class Name
`BaseMultibaseUtil`

### Function Name
`encode`

### Function Introduction
`데이터를 Multibase(Base58BTC) 문자열로 인코딩합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | byte[] | 인코딩할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | Multibase 인코딩된 문자열 | M | |

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
`데이터를 지정된 Multibase 타입으로 인코딩합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | byte[] | 인코딩할 데이터 | M | |
| type | MultiBaseType | Multibase 타입 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | Multibase 인코딩된 문자열 | M | |

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
`Multibase 문자열을 디코딩합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| encoded | String | Multibase 인코딩된 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 디코딩된 데이터 | M | |

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
`데이터를 Base64 문자열로 인코딩합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | byte[] | 인코딩할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| String | Base64 인코딩된 문자열 | M | |

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
`Base64 문자열을 디코딩합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| encoded | String | Base64 인코딩된 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 디코딩된 데이터 | M | |

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
`데이터에 대(SHA-256) 해시를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| message | String | 해시할 메시지 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 SHA-256 해시 | M | |

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
`데이터에 대(SHA-256) 해시를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| data | byte[] | 해시할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 SHA-256 해시 | M | |

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
`데이터에 대(SHA-512) 해시를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| message | String | 해시할 메시지 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 SHA-512 해시 | M | |

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
`HMAC(HmacSHA256) 값을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| key | byte[] | HMAC 키 | M | |
| data | byte[] | 서명할 데이터 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| byte[] | 생성된 HMAC 값 | M | |

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
`VC 스키마 JSON 문자열을 파싱하여 VcSchema 객체로 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| vcSchemaJson | String | VC 스키마 JSON 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VcSchema | 파싱된 VcSchema 객체 | M | |

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
`VC 발급 파라미터에 발급자 정보를 설정합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC 발급 파라미터 | M | |
| issuerDid | String | 발급자 DID | M | |
| issuerName | String | 발급자 이름 | M | |
| certVcRef | String | 인증서 VC 참조 URL | M | |

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
`VC 발급 파라미터에 VC 스키마를 설정합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC 발급 파라미터 | M | |
| vcSchemaJson | String | VC 스키마 JSON 문자열 | M | |

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
`VC 발급 파라미터에 클레임 정보를 설정합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC 발급 파라미터 | M | |
| claimInfoMap | HashMap&lt;String, ClaimInfo&gt; | 클레임 정보 맵 | M | |

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
`VC 발급 파라미터에 VC 타입을 설정합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC 발급 파라미터 | M | |
| vcTypeList | List&lt;VcType&gt; | VC 타입 리스트 | M | |

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
`VC 발급 파라미터에 증명(Evidence) 정보를 설정합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC 발급 파라미터 | M | |
| evidence | DocumentVerificationEvidence | 문서 검증 증명 객체 | M | |

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
`입력된 파라미터로 Verifiable Credential(VC)을 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| issueVcParam | IssueVcParam | VC 발급 파라미터 | M | |
| did | String | 수신자(Holder) DID | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VerifiableCredential | 생성된 VC 객체 | M | |

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
`VC 메타데이터를 생성합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| vc | VerifiableCredential | 메타데이터 생성 대상 VC | M | |
| certVcRef | String | 인증서 VC 참조 URL | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VcMeta | 생성된 VC 메타데이터 | M | |

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
`VC 서명을 위한 원본 메시지를 추출합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| tasDidDoc | DidDocument | TAS의 DID Document | M | |
| verifiableCredential | VerifiableCredential | 서명할 VC | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| List&lt;SignatureVcParams&gt; | 서명 파라미터 리스트 | M | |

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
`VC에 생성된 서명(Proof)을 설정합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| verifiableCredential | VerifiableCredential | Proof를 설정할 VC | M | |
| signatureParamsList | List&lt;SignatureVcParams&gt; | 서명 파라미터 리스트 (서명 값 포함) | M | |

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
`VC 메타데이터 JSON 문자열을 파싱합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| vcMetaJson | String | VC 메타데이터 JSON 문자열 | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VcMeta | 파싱된 VcMeta 객체 | M | |

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
`VC의 구조적 유효성을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| verifiableCredential | VerifiableCredential | 검증할 VC | M | |

### Output Parameters
n/a (유효하지 않으면 예외 발생)

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
`VC의 서명 및 유효성을 검증합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| verifiableCredential | VerifiableCredential | 검증할 VC | M | |
| didDocument | DidDocument | 발급자의 DID Document | M | |
| isRevocationCheck | boolean | 폐기 여부 확인 플래그 | M | |

### Output Parameters
n/a (검증 실패 시 예외 발생)

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
`VC에 서명하여 반환합니다.`

### Input Parameters
| Parameter | Type | Description | M/O | 비고 |
|---|---|---|---|---|
| vcManager | VcManager | VC 매니저 | M | |
| walletManager | WalletManagerInterface | 월렛 매니저 (서명 기능 제공) | M | |
| issuerDidDoc | DidDocument | 발급자 DID Document | M | |
| keyId | String | 서명에 사용할 Key ID | M | |
| vc | VerifiableCredential | 서명할 VC | M | |

### Output Parameters
| Type | Description | M/O | 비고 |
|---|---|---|---|
| VerifiableCredential | 서명된 VC | M | |

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
`OpenDID 관련 오류를 처리하기 위한 커스텀 예외 클래스입니다. ErrorCode를 포함하여 상세한 오류 정보를 제공합니다.`

<br>

## 5.2 HttpClientException

### Class Name
`HttpClientException`

### Class Description
`HTTP 통신 중 발생하는 예외 클래스입니다.`

<br>

## 5.3 ErrorCode

### Enum Name
`ErrorCode`

### Enum Description
`DID Verifier 시스템에서 사용되는 에러 코드 열거형입니다.`

### Enum Values
`상세 에러 코드는 소스 코드 또는 별도 문서를 참조하십시오.`

<br>

## 5.4 ErrorResponse

### Class Name
`ErrorResponse`

### Class Description
`에러 응답 객체입니다.`

<br>

# 6. Reference Classes
