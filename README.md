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
├── README_ko.md
├── RELEASE-PROCESS.md
├── SECURITY.md
├── docs
│   └── api
│       └── COMMON_SDK-SERVER_API_ko.md
│       └── COMMON_SDK-SERVER_API.md
│   └── errorCode
│       └── Common_ErrorCode.md
└── source
    └── did-common-sdk-server
        ├── gradle
        ├── libs
        ├── src
        ├── build.gradle
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
| &nbsp;&nbsp;&nbsp;┖ gradle                | Gradle build configurations and scripts         |
| &nbsp;&nbsp;&nbsp;┖ src                   | Main source code directory                      |
| &nbsp;&nbsp;&nbsp;┖ build.gradle          | Gradle build configuration file                 |
| &nbsp;&nbsp;&nbsp;┖ README.md             | Overview and instructions for the source code   |

<br/>


## Libraries

Libraries used in this project are organized into two main categories:

1. **Third-Party Libraries**: These libraries are open-source dependencies managed via the [build.gradle](source/did-common-sdk-server/build.gradle) file. For a detailed list of third-party libraries and their licenses, please refer to the [dependencies-license.md](dependencies-license.md) file.

2. **OmniOne SDK**: Libraries included in the `source/did-common-sdk-server/libs` folder, serving as local dependencies for the OmniOne DID project.

## API Reference

For more details on the API, please refer to the [Server Common SDK API](./docs/api/COMMON_SDK-SERVER_API.md) documentation.

## Change Log

The Change Log provides a detailed record of version-specific changes and updates. You can find it here:
- [Change Log](./CHANGELOG.md)  

## OpenDID Demonstration Videos <br>
To watch our demonstration videos of the OpenDID system in action, please visit our [Demo Repository](https://github.com/OmniOneID/did-demo-server). <br>

These videos showcase key features including user registration, VC issuance, and VP submission processes.

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) and [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md) for details on our code of conduct, and the process for submitting pull requests to us.

## License
[Apache 2.0](LICENSE)
