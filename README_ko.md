Server Common SDK
==

Server Common SDK Repository에 오신 것을 환영합니다. <br>
이 Repository는 Server Common SDK의 소스 코드, 문서, 관련 리소스를 포함하고 있습니다.

## 폴더 구조
프로젝트 디렉터리 내 주요 폴더와 문서에 대한 개요:

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

각 폴더와 파일에 대한 설명은 다음과 같습니다:

| 이름                               | 설명                      |
|----------------------------------|-------------------------|
| CHANGELOG.md                     | 프로젝트의 버전별 변경 사항         |
| CODE_OF_CONDUCT.md               | 기여자 행동 강령               |
| CONTRIBUTING.md                  | 기여 지침과 절차               |
| LICENSE                          | 라이선스                    |
| dependencies-license.md          | 프로젝트 의존 라이브러리의 라이선스 정보  |
| MAINTAINERS.md                   | 프로젝트 유지 관리자 지침          |
| RELEASE-PROCESS.md               | 새 버전 릴리스 절차             |
| SECURITY.md                      | 보안 정책 및 취약성 보고 방법       |
| docs                             | 문서화                     |
| ┖ api                            | API 가이드 문서              |
| ┖ errorCode                      | 오류 코드 및 문제 해결 가이드       |
| source                           | 서버 소스 코드 프로젝트           |
| ┖ did-wallet-server              | Wallet 서버 소스 코드 및 빌드 파일 |
| &nbsp;&nbsp;&nbsp;┖ gradle       | Gradle 빌드 설정 및 스크립트     |
| &nbsp;&nbsp;&nbsp;┖ src          | 주요 소스 코드 디렉터리           |
| &nbsp;&nbsp;&nbsp;┖ build.gradle | Gradle 빌드 설정 파일         |
| &nbsp;&nbsp;&nbsp;┖ README.md    | 소스 코드 개요 및 지침           |

<br/>

## 라이브러리

이 프로젝트에서 사용되는 라이브러리는 두 가지 주요 범주로 구성됩니다.

1. **서드파티 라이브러리**: 이 라이브러리들은 [build.gradle](source/did-common-sdk-server/build.gradle) 파일을 통해 관리되는 오픈 소스 종속성입니다. 서드파티 라이브러리와 그 라이선스에 대한 자세한 목록은 [dependencies-license.md](dependencies-license.md) 파일을 참조하십시오.

## API Reference


| Class          | 주요 기능                                                               | API 문서 링크                                |
| -------------- | ------------------------------------------------------------------ | ------------------------------------------- |
| DateTimeUtil   | 현재 UTC 시간 가져오기, 시간 추가, 시간 문자열 파싱                   | [Server Common SDK - DateTimeUtil API](./docs/api/Server%20Common%20SDK_DateTimeUtil.md)   |
| DidValidator   | DID 검증, DID 키 검증 URL                                           | [Server Common SDK - DidValidator API](./docs/api/Server%20Common%20SDK_DidValidator.md)   |
| HexUtil        | 바이트 배열을 16진수 문자열로 변환                                   | [Server Common SDK - HexUtil API](./docs/api/Server%20Common%20SDK_HexUtil.md)        |
| HttpClientUtil | HTTP GET 요청, HTTP POST 요청                                       | [Server Common SDK - HttpClientUtil API](./docs/api/Server%20Common%20SDK_HttpClientUtil.md) |
| JsonUtil       | 객체를 JSON 문자열로 직렬화, JSON 문자열을 객체로 역직렬화          | [Server Common SDK - JsonUtil API](./docs/api/Server%20Common%20SDK_JsonUtil.md)       |
| QrMaker        | 데이터를 QR 코드 이미지로 변환하여 바이트 배열로 반환                 | [Server Common SDK - QrMaker API](./docs/api/Server%20Common%20SDK_QrMaker.md)        |

## Change Log

Change Log에는 버전별 변경 사항과 업데이트가 자세히 기록되어 있습니다. 다음에서 확인할 수 있습니다:
- [Change Log](CHANGELOG.md)

## OpenDID 시연 영상
OpenDID 시스템의 시연 영상을 보려면 [데모 Repository](https://github.com/OmniOneID/did-demo-server)를 방문하십시오. <br>

이 영상에서는 사용자 등록, VC 발급, VP 제출 프로세스 등 주요 기능을 시연합니다.

## 기여

기여 절차와 행동 강령에 대한 자세한 내용은 [CONTRIBUTING.md](CONTRIBUTING.md)와 [CODE_OF_CONDUCT.md](CODE_OF_CONDUCT.md)를 참조해 주십시오.

## 라이선스
[Apache 2.0](LICENSE)
