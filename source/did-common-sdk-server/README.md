
# Server Common SDK Installation Guide

This document serves as a guide for using the Server Common SDK. The Server Common SDK provides various utility classes that can be shared across all service servers of Open DID.

## Software Specifications
| Category      | Description                   |
|---------------|-------------------------------|
| OS            | Linux, macOS, Windows         |
| Language      | Java 21                       |
| Build System  | Gradle 8.2                    |

<br>

## Build Instructions
Generate a JAR file using the `build.gradle` file of this SDK project.

1. Open the Gradle window and execute the `Tasks > build > jar` task in the project.
2. Once the execution is complete, the file `did-common-sdk-server-3.0.0.jar` will be generated in the `build/libs` folder.

<br>

## How to Apply SDK
1. Copy the `did-common-sdk-server-3.0.0.jar` file to the `libs` folder of the app project.
2. Add the following dependency to the `build.gradle` file of the app project.

```groovy
    // Common SDK
    implementation files('libs/did-common-sdk-server-3.0.0.jar')

    // OmniOne SDKs (Local dependencies in libs folder)
    implementation fileTree(dir: "libs", include: ["*.jar"], exclude: ["did-sdk-common-*.jar"])

    // Crypto
    implementation 'org.bouncycastle:bcprov-jdk18on:1.78.1'

    // JSON
    implementation 'com.fasterxml.jackson.core:jackson-databind:2.15.1'
    implementation 'com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.15.1'
    implementation 'com.google.code.gson:gson:2.10.1'

    // Validation
    implementation 'jakarta.validation:jakarta.validation-api:3.0.2'
    implementation 'org.hibernate.validator:hibernate-validator:8.0.1.Final'
    implementation 'org.glassfish:jakarta.el:4.0.2'

    // QR Image
    implementation 'com.google.zxing:javase:3.5.1'
```
3. Ensure that dependencies are properly added by synchronizing `Gradle`.

<br>

## API Specification
For detailed API specifications and key features, please refer to the [Server Common SDK API](../../docs/api/COMMON_SDK-SERVER_API.md) documentation.
