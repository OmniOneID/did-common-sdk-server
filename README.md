Server Common SDK 
==

Welcome to the Server Common SDK Repository. <br>
This repository contains the source code, documentation, and related resources for the Server Common SDK.

## Folder Structure
Overview of the major folders and documents in the project directory:

```
did-common-sdk-server
├── CHANGELOG.md
├── CLA.md
├── CODE_OF_CONDUCT.md
├── CONTRIBUTING.md
├── LICENSE
├── dependencies-license.md
├── MAINTAINERS.md
├── README.md
├── RELEASE-PROCESS.md
├── SECURITY.md
├── docs
│   └── api
│       └── Server Common SDK_DateTimeUtil.md
│       └── Server Common SDK_DidUtil.md
│       └── Server Common SDK_DidValidator.md
│       └── Server Common SDK_HexUtil.md
│       └── Server Common SDK_HttpClientUtil.md
│       └── Server Common SDK_JsonUtil.md
│       └── Server Common SDK_QrMaker.md
│   └── errorCode
│       └── Common_ErrorCode.md
└── source
    └── did-common-sdk-server
        ├── gradle
        └── src
        └── build.gradle
        └── README.md
```

<br/>

Below is a description of each folder and file in the directory:

| Name                    | Description                                     |
| ----------------------- | ----------------------------------------------- |
| CHANGELOG.md            | Version-specific changes in the project         |
| CODE_OF_CONDUCT.md      | Code of conduct for contributors                |
| CONTRIBUTING.md         | Contribution guidelines and procedures          |
| LICENSE                 | License                                         |
| dependencies-license.md | Licenses for the project’s dependency libraries |
| MAINTAINERS.md          | Guidelines for project maintainers              |
| RELEASE-PROCESS.md      | Procedures for releasing new versions           |
| SECURITY.md             | Security policies and vulnerability reporting   |
| docs                    | Documentation                                   |
| ┖ api                   | API guide documentation                         |
| ┖ errorCode             | Error codes and troubleshooting guides          |
| source                  | Server source code project                      |
| ┖ did-common-sdk-server | Common SDK source code and build files          |
| ┖ gradle                | Gradle build configurations and scripts         |
| ┖ src                   | Main source code directory                      |
| ┖ build.gradle          | Gradle build configuration file                 |
| ┖ README.md             | Overview and instructions for the source code   |

<br/>


## Libraries

Libraries used in this project are organized into two main categories:

1. **Third-Party Libraries**: These libraries are open-source dependencies managed via the [build.gradle](source/did-common-sdk-server/build.gradle) file. For a detailed list of third-party libraries and their licenses, please refer to the [dependencies-license.md](dependencies-license.md) file.

## API Reference

| Class          | Key Features                                                       | API Documentation Link                      |
| -------------- | ------------------------------------------------------------------ | ------------------------------------------- |
| DateTimeUtil   | Gets current UTC time, add time, parse time strings                | [Server Common SDK - DateTimeUtil API](./docs/api/Server%20Common%20SDK_DateTimeUtil.md)   |
| DidValidator   | DID Verification , DID Key Verification URL                        | [Server Common SDK - DidValidator API](./docs/api/Server%20Common%20SDK_DidValidator.md)   |
| HexUtil        | Convert byte array to hexadecimal string                           | [Server Common SDK - HexUtil API](./docs/api/Server%20Common%20SDK_HexUtil.md)        |
| HttpClientUtil | HTTP GET Reguest, HTTP POST  Reguest                               | [Server Common SDK - HttpClientUtil API](./docs/api/Server%20Common%20SDK_HttpClientUtil.md) |
| JsonUtil       | Serialize object to JSON string, deserialize JSON string to object | [Server Common SDK - JsonUtil API](./docs/api/Server%20Common%20SDK_JsonUtil.md)       |
| QrMaker        | Convert data to QR code image and return as byte array             | [Server Common SDK - QrMaker API](./docs/api/Server%20Common%20SDK_QrMaker.md)        |

## Change Log

The Change Log provides a detailed record of version-specific changes and updates. You can find it here:
- [Change Log](./CHANGELOG.md)  

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for details on our code of conduct, and the process for submitting pull requests to us.

## License
[Apache 2.0](LICENSE)