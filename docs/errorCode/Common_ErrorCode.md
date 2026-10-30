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

Common Util SDK Error
==

- Date: 2024-09-02
- Version: v1.0.0

| Version | Date       | History                 |
| ------- | ---------- | ------------------------|
| v1.0.0  | 2024-09-02 | Initial                 |

<div style="page-break-after: always;"></div>

# Table of Contents

- [Model](#model)
  - [Error Response](#error-response)
- [Error Code](#error-code)
  - [1. Common Util SDK](#1-common-util-sdk)
    - [1-1. Data Processing (00100 ~ 00199)](#1-1-data-processing-00100--00199)
    - [1-2. QR CODE (00200 ~ 00299)](#1-2-qr-code-00200--00299)
    - [1-3. HTTP Client (00300 ~ 00399)](#1-3-http-client-00300--00399)
    - [1-4. Signature (00400 ~ 00409)](#1-4-signature-00400--00409)
    - [1-5. ECDH (00410 ~ 00419)](#1-5-ecdh-00410--00419)
    - [1-6. Crypto (00420 ~ 00429)](#1-6-crypto-00420--00429)
    - [1-7. DID Document (00500 ~ 00599)](#1-7-did-document-00500--00599)
    - [1-8. Wallet (00600 ~ 00699)](#1-8-wallet-00600--00699)
    - [1-9. Token (00700 ~ 00799)](#1-9-token-00700--00799)
    - [1-10. VC (00800 ~ 00899)](#1-10-vc-00800--00899)
    - [1-11. Blockchain (00900 ~ 00999)](#1-11-blockchain-00900--00999)
    - [1-12. General (01000 ~ 01099)](#1-12-general-01000--01099)

# Model

## Error Response

### Description

```
Error struct for Common Util SDK. It has code and message pair.
Code starts with SSDKUTL.
```

### Declaration

```java
public class ErrorResponse {
    private final String code;
    private final String message;
}
```

### Property

| Name    | Type   | Description                        | **M/O** | **Note** |
|---------|--------|------------------------------------|---------| -------- |
| code    | String | Error code. It starts with SSDKUTL | M       |          |
| message | String | Error description                  | M       |          |

<br>

# Error Code

## 1. Common Util SDK

### 1-1. Data Processing (00100 ~ 00199)

| Error Code   | Error Message                      | Description | Action Required                                                                   |
|--------------|-------------------------------------|-------------|-----------------------------------------------------------------------------------|
| SSDKUTL00100 | Failed to Json serialize.           | -           | Check input data structure and ensure all fields are serializable.                |
| SSDKUTL00101 | Failed to Json serialize and sort.  | -           | Verify the sorting criteria and ensure all fields have comparable values.         |
| SSDKUTL00102 | Failed to Json deserialize.         | -           | Validate the JSON string format and ensure it matches the target object structure.|
| SSDKUTL00103 | Invalid date time format.           | -           | Check the date-time string and ensure it follows the expected format (e.g., ISO 8601).|

### 1-2. QR CODE (00200 ~ 00299)

| Error Code   | Error Message                                    | Description | Action Required                                                                    |
|--------------|--------------------------------------------------|-------------|------------------------------------------------------------------------------------|
| SSDKUTL00200 | Failed to generate QR code image.                | -           | Verify the input data for QR code generation and check available memory.           |
| SSDKUTL00201 | Failed to write QR code image to output stream.  | -           | Check write permissions and available disk space. Ensure output stream is open.    |
| SSDKUTL00202 | Failed to encode QR code content.                | -           | Validate the content size and character set. Ensure it's within QR code capacity.  |

### 1-3. HTTP Client (00300 ~ 00399)

| Error Code   | Error Message                | Description | Action Required                                                                         |
|--------------|------------------------------|-------------|-----------------------------------------------------------------------------------------|
| SSDKUTL00300 | Failed to send HTTP request. | -           | Check network connectivity, verify URL, and ensure proper request formation (headers, body, etc.).|

### 1-4. Signature (00400 ~ 00409)

| Error Code   | Error Message                            | Description | Action Required |
|--------------|------------------------------------------|-------------|-----------------|
| SSDKUTL00401 | Failed to verify signature               | -           | Check the signature format and key used for verification. |
| SSDKUTL00402 | Invalid signature                        | -           | Ensure the signature matches the input data and key. |
| SSDKUTL00403 | Failed to generate proof                 | -           | Verify the input parameters for proof generation. |
| SSDKUTL00404 | Failed to generate signature message     | -           | Check the data to be signed. |

### 1-5. ECDH (00410 ~ 00419)

| Error Code   | Error Message                  | Description | Action Required |
|--------------|--------------------------------|-------------|-----------------|
| SSDKUTL00410 | Failed to generate ECDH key    | -           | Check ECC key generation parameters. |
| SSDKUTL00411 | Invalid client nonce           | -           | Ensure the client nonce is valid and has correct length. |
| SSDKUTL00412 | Failed to generate session key | -           | Check input keys and params for session key derivation. |

### 1-6. Crypto (00420 ~ 00429)

| Error Code   | Error Message                       | Description | Action Required |
|--------------|-------------------------------------|-------------|-----------------|
| SSDKUTL00420 | Unsupported cryptographic algorithm | -           | specific algorithm is not supported. Use a supported algorithm. |
| SSDKUTL00421 | Invalid ECC curve type              | -           | Check if the ECC curve is one of the supported types (e.g., secp256r1). |
| SSDKUTL00422 | Invalid symmetric cipher type       | -           | Use a valid symmetric cipher type (e.g., AES). |
| SSDKUTL00423 | Invalid symmetric padding type      | -           | Use a valid padding type. |

### 1-7. DID Document (00500 ~ 00599)

| Error Code   | Error Message                                     | Description | Action Required |
|--------------|---------------------------------------------------|-------------|-----------------|
| SSDKUTL00501 | Invalid DID document                              | -           | Validate the structure and content of the DID document. |
| SSDKUTL00502 | Failed to verify DID document                     | -           | Check if the DID document signature or proof is valid. |
| SSDKUTL00503 | Failed to generate invoked DID document           | -           | Check inputs for generating invoked DID document. |
| SSDKUTL00504 | Verification method not found in DID Document     | -           | Ensure the verification method ID exists in the DID Document. |
| SSDKUTL00505 | Failed to generate DID Document                   | -           | Check parameters required for DID Document generation. |
| SSDKUTL00506 | Required key missing in DID document              | -           | Ensure all mandatory keys are present in the DID Document. |

### 1-8. Wallet (00600 ~ 00699)

| Error Code   | Error Message                  | Description | Action Required |
|--------------|--------------------------------|-------------|-----------------|
| SSDKUTL00601 | Failed to create wallet        | -           | Check file permissions and storage availability. |
| SSDKUTL00602 | Failed to load wallet          | -           | Ensure the wallet file exists and is accessible. |
| SSDKUTL00603 | Failed to generate key         | -           | Check key generation parameters and provider status. |
| SSDKUTL00604 | Failed to generate signature   | -           | Verify key validity and data to be signed. |
| SSDKUTL00605 | Failed to load key element     | -           | Check if the key element exists in the wallet. |

### 1-9. Token (00700 ~ 00799)

| Error Code   | Error Message                       | Description | Action Required |
|--------------|-------------------------------------|-------------|-----------------|
| SSDKUTL00701 | Invalid access token                | -           | The token is invalid. Retrieve a new token. |
| SSDKUTL00702 | Failed to generate token            | -           | Check parameters for token generation. |
| SSDKUTL00703 | Token signature verification failed | -           | The token signature is invalid. |
| SSDKUTL00704 | Failed to parse token               | -           | Ensure the token format is correct. |
| SSDKUTL00705 | Token has expired                   | -           | The token has expired. Request a new token. |

### 1-10. VC (00800 ~ 00899)

| Error Code   | Error Message                              | Description | Action Required |
|--------------|--------------------------------------------|-------------|-----------------|
| SSDKUTL00801 | Failed to create Verifiable Credential     | -           | Check VC creation inputs. |
| SSDKUTL00802 | Failed to parse Verifiable Credential      | -           | Validate the VC JSON format. |
| SSDKUTL00803 | Failed to parse VC schema                  | -           | Ensure the VC schema is valid. |
| SSDKUTL00804 | Failed to parse VC metadata                | -           | Ensure the VC metadata is valid. |
| SSDKUTL00805 | Failed to generate Verifiable Credential   | -           | Check parameters for VC generation. |
| SSDKUTL00806 | Failed to extract VC signature message     | -           | Verify VC structure for signature extraction. |
| SSDKUTL00807 | Failed to set VC proof                     | -           | Check the proof object and VC. |
| SSDKUTL00808 | Failed to verify Verifiable Credential     | -           | Check if the VC signature/proof is valid. |
| SSDKUTL00809 | Failed to sign Verifiable Credential       | -           | Verify the signing key and VC content. |

### 1-11. Blockchain (00900 ~ 00999)

| Error Code   | Error Message                       | Description | Action Required |
|--------------|-------------------------------------|-------------|-----------------|
| SSDKUTL00901 | Failed to connect to blockchain     | -           | Check blockchain network connection. |
| SSDKUTL00902 | Failed to register DID document     | -           | Check DID document validity and blockchain status. |
| SSDKUTL00903 | Failed to get DID document          | -           | Ensure the DID is registered on the blockchain. |
| SSDKUTL00904 | Failed to register VC metadata      | -           | Check VC metadata validity and blockchain status. |
| SSDKUTL00905 | Failed to get VC metadata           | -           | Ensure the VC metadata is registered. |
| SSDKUTL00906 | Failed to serialize blockchain data | -           | Check data structure for serialization. |
| SSDKUTL00907 | Failed to update DID document       | -           | Verify update permissions and DID existence. |
| SSDKUTL00908 | Failed to update VC status          | -           | Verify update permissions and VC existence. |
| SSDKUTL00909 | Failed to remove index              | -           | Check parameters for index removal. |

### 1-12. General (01000 ~ 01099)

| Error Code   | Error Message                | Description | Action Required |
|--------------|------------------------------|-------------|-----------------|
| SSDKUTL01001 | JSON processing error        | -           | Check JSON format and structure. |
| SSDKUTL01002 | Encoding failed              | -           | Check data to be encoded. |
| SSDKUTL01003 | Invalid parameter            | -           | One or more parameters are invalid. |
| SSDKUTL01004 | Not implemented              | -           | Feature not implemented yet. |
| SSDKUTL01005 | Operation not supported      | -           | This operation is not supported. |
| SSDKUTL01006 | Failed to convert QR image   | -           | Check QR image data. |
| SSDKUTL01099 | Unknown error                | -           | Unexpected error occurred. Check logs for details. |