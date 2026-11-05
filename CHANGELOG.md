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
- Added error codes for encryption, decryption, decoding, nonce and hash failures, which previously reported `ENCODING_FAILED` or `JSON_PROCESSING_ERROR`
- `BaseCoreVcUtil.signVerifiableCredential` keeps the error code of the failing step
- Added `CommonSdkException.getErrorResponse()`

### 🐛 Bug Fixes
- `DidUtil.extractVersion` reads the `versionId` query parameter of a DID URL
- An expired JWT reports `EXPIRED_TOKEN`
- A missing verification method reports `DID_DOC_VERIFICATION_METHOD_NOT_FOUND`
- `BaseEcdhServiceImpl` validates the client DID format and the client nonce length
- A null ECC curve, symmetric cipher or padding type reports `INVALID_ECC_CURVE_TYPE`, `INVALID_SYMMETRIC_CIPHER_TYPE` or `INVALID_SYMMETRIC_PADDING_TYPE` instead of a `NullPointerException`

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