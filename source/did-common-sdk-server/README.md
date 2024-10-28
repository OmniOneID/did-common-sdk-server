
# Server Common SDK Installation Guide

This document serves as a guide for using the Server Common SDK. The Server Common SDK provides various utility classes that can be shared across all service servers of Open DID.

## Software Specifications
| Category      | Description                   |
|---------------|-------------------------------|
| OS            | Linux, macOS, Windows         |
| Language      | Java 17                       |
| Build System  | Gradle 8.2                    |

<br>

## Build Instructions
Generate a JAR file using the `build.gradle` file of this SDK project.

1. Open the project's `build.gradle` file and add the following JAR task configuration:
```groovy
jar {
  archiveBaseName.set('did-sdk-common')
  archiveVersion.set(version)
  archiveClassifier.set('')
}
```
2. Open the Gradle window and execute the `Tasks > other > jar` task in the project.
3. Once the execution is complete, the file `did-sdk-common-1.0.0.jar` will be generated in the `build/libs` folder.

<br>

## How to Apply SDK
1. Copy the `did-sdk-common-1.0.0.jar` file to the `libs` folder of the app project.
2. Add the following dependency to the `build.gradle` file of the app project.

```groovy
    implementation files('libs/did-sdk-common-1.0.0.jar')
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.1'
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.1'
    implementation 'com.google.zxing:javase:3.5.1'
```
3. Ensure that dependencies are properly added by synchronizing `Gradle`.

<br>

## API Specification and Key Features
| Class          | Key Features                                                       | API Documentation Link                      |
| -------------- | ------------------------------------------------------------------ | ------------------------------------------- |
| DateTimeUtil   | Gets current UTC time, add time, parse time strings                | [Server Common SDK - DateTimeUtil API](../../docs/api/Server%20Common%20SDK_DateTimeUtil.md)   |
| DidValidator   | DID Verification , DID Key Verification URL                        | [Server Common SDK - DidValidator API](../../docs/api/Server%20Common%20SDK_DidValidator.md)   |
| HexUtil        | Convert byte array to hexadecimal string                           | [Server Common SDK - HexUtil API](../../docs/api/Server%20Common%20SDK_HexUtil.md)        |
| HttpClientUtil | HTTP GET Reguest, HTTP POST  Reguest                               | [Server Common SDK - HttpClientUtil API](../../docs/api/Server%20Common%20SDK_HttpClientUtil.md) |
| JsonUtil       | Serialize object to JSON string, deserialize JSON string to object | [Server Common SDK - JsonUtil API](../../docs/api/Server%20Common%20SDK_JsonUtil.md)       |
| QrMaker        | Convert data to QR code image and return as byte array             | [Server Common SDK - QrMaker API](../../docs/api/Server%20Common%20SDK_QrMaker.md)        |

### DateTimeUtil
The DateTimeUtil class provides utility methods for date and time manipulation. Key features include:

* <b>Get current UTC time</b>: Returns the current UTC time in ISO 8601 format.
* <b>Add time</b>: Adds specified time to the current time.
* <b>Parse time string</b>: Parses a string in ISO 8601 format into an Instant object.

### DidValidator
The DidValidator class provides utility methods for validating DID and DID Key URL formats. Key features include:

* <b>Validate DID</b>: Validates the format of the given DID string.
* <b>Validate DID Key URL</b>: Validates the format of the given DID Key URL string.

### HexUtil
The HexUtil class provides utility methods for converting byte arrays to hexadecimal strings. Key features include:

* <b>Convert byte array to hexadecimal string</b>: Converts the given byte array to a hexadecimal string.

### HttpClientUtil
The HttpClientUtil class provides utility methods for sending HTTP GET and POST requests. Key features include:

* <b>HTTP GET request</b>: Sends an HTTP GET request to the specified URL and returns the response body.
* <b>HTTP POST request</b>: Sends an HTTP POST request to the specified URL and returns the response body.

### JsonUtil
The JsonUtil class provides utility methods for JSON serialization and deserialization. Key features include:

* <b>Serialize object to JSON string</b>: Converts an object to a JSON string.
* <b>Deserialize JSON string to object</b>: Converts a JSON string to an object of the specified class type.

### QrMaker
The QrMaker class provides utility methods for generating QR codes. Key features include:

* <b>Generate QR code</b>: Converts the given data into a QR code image and returns it as a byte array.
