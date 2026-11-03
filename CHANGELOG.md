# Changelog

## [Unreleased] (v3.0.0)

### 🚀 New Features
- Shared server utilities: `BaseCoreDidUtil`, `BaseCoreVcUtil`, `BaseCryptoUtil`, `BaseDigestUtil`, `BaseMultibaseUtil`, `BaseBlockChainUtil`, `BaseAccessTokenUtil`, `BaseWalletUtil`
- Domain helpers: `DidDocCryptoHelper`, `EcdhCryptoHelper`, `SignatureCryptoHelper`
- Service interfaces and base implementations for ECDH, signature, token, VC, and wallet (`Base*ServiceImpl`)
- `LssClient`, `ObjectUtil`, `OtpGenerator`
- `CommonSdkException` with `SSDKUTL` error codes

### 🔧 Changes
- The artifact is `did-common-sdk-server-3.0.0.jar` (previously `did-sdk-common`)

## v1.0.0 (2024-10-18)

### 🚀 New Features
- Common Server SDK
    - DateTime Util
    - DID Util
    - DID Validator Util
    - Http Client Util
    - ID Generator Util
    - JSON Util
    - Qr Maker Util