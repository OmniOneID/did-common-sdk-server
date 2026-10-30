package org.omnione.did.common.util.token;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.omnione.did.common.exception.ErrorCode;
import org.omnione.did.common.exception.CommonSdkException;
import org.omnione.did.common.util.did.BaseCoreDidUtil;
import org.omnione.did.crypto.exception.CryptoException;
import org.omnione.did.crypto.util.MultiBaseUtils;
import org.omnione.did.crypto.util.SignatureUtils;
import org.omnione.did.crypto.enums.DigestType;
import org.omnione.did.crypto.util.DigestUtils;
import org.omnione.did.data.model.did.DidDocument;
import org.omnione.did.common.util.DidUtil;
import org.omnione.did.wallet.key.WalletManagerInterface;
import org.omnione.did.common.datamodel.data.token.TokenData;
import java.util.HashMap;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Map;

/**
 * Utility class for JWT/Access Token validation and processing.
 * Provides static methods for various token-related operations that can be
 * shared across different servers.
 * 
 * This implementation follows the TA server approach for maximum compatibility
 * and security:
 * - Uses OmniOne SDK's SignatureUtils for signature verification
 * - Supports DID-based authentication
 * - Provides granular validation methods for flexibility
 * - Implements 7-step token validation process
 */
public class BaseAccessTokenUtil {

    private static final Gson gson = new Gson();

    /**
     * JWT token structure containing header, payload, and signature components.
     */
    public static class JwtComponents {
        private final String header;
        private final String payload;
        private final String signature;
        private final String signatureOrigin;

        public JwtComponents(String header, String payload, String signature, String signatureOrigin) {
            this.header = header;
            this.payload = payload;
            this.signature = signature;
            this.signatureOrigin = signatureOrigin;
        }

        public String getHeader() {
            return header;
        }

        public String getPayload() {
            return payload;
        }

        public String getSignature() {
            return signature;
        }

        public String getSignatureOrigin() {
            return signatureOrigin;
        }
    }

    /**
     * JWT time claims data container.
     */
    public static class JwtTimeClaims {
        private final long iat;
        private final long exp;
        private final long currentTime;

        public JwtTimeClaims(long iat, long exp, long currentTime) {
            this.iat = iat;
            this.exp = exp;
            this.currentTime = currentTime;
        }

        public long getIat() {
            return iat;
        }

        public long getExp() {
            return exp;
        }

        public long getCurrentTime() {
            return currentTime;
        }
    }

    /**
     * Splits and validates JWT token format.
     *
     * @param jwtToken The JWT token to split
     * @return JwtComponents containing header, payload, signature, and signature
     *         origin
     * @throws CommonSdkException if token format is invalid
     */
    public static JwtComponents splitJwtToken(String jwtToken) {
        try {
            String[] parts = jwtToken.split("\\.");
            if (parts.length != 3) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

            String header = parts[0];
            String payload = parts[1];
            String signature = parts[2];
            String signatureOrigin = header + "." + payload;

            return new JwtComponents(header, payload, signature, signatureOrigin);

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Extracts public key from DID Document for token verification.
     *
     * @param signerDidDocument The DID document of the token signer
     * @return The public key for signature verification
     * @throws CommonSdkException if public key extraction fails
     */
    public static String extractPublicKeyFromDidDocument(DidDocument signerDidDocument) {
        try {
            String publicKey = BaseCoreDidUtil.getPublicKey(signerDidDocument, "assert");

            if (publicKey == null) {
                throw new CommonSdkException(ErrorCode.DIDDOC_REQUIRED_KEY_MISSING);
            }

            return publicKey;
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.DIDDOC_REQUIRED_KEY_MISSING);
        }
    }

    /**
     * Verifies JWT token signature using OmniOne SDK's SignatureUtils.
     * Uses verifyCompactSignWithCompressedKey with SHA256 hashed data.
     *
     * @param jwtComponents The JWT components (header, payload, signature)
     * @param publicKey     The public key for signature verification (multibase
     *                      encoded)
     * @throws CommonSdkException if signature verification fails
     */
    public static void verifyJwtSignature(JwtComponents jwtComponents, String publicKey) {
        try {
            // 1. Hash the signature origin data with SHA256
            byte[] hashedSignOriginData;
            try {
                hashedSignOriginData = DigestUtils.getDigest(
                        jwtComponents.getSignatureOrigin().getBytes(StandardCharsets.UTF_8),
                        DigestType.SHA256);
            } catch (CryptoException e) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

            // 2. Decode Base64 URL signature to bytes
            byte[] signatureBytes;
            try {
                signatureBytes = Base64.getUrlDecoder().decode(jwtComponents.getSignature());
            } catch (IllegalArgumentException e) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

            // 3. Decode multibase public key to bytes
            byte[] compressedPublicKeyBytes;
            try {
                compressedPublicKeyBytes = MultiBaseUtils.decode(publicKey);
            } catch (CryptoException e) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

            // 4. Verify signature using OmniOne SDK
            try {
                SignatureUtils.verifyCompactSignWithCompressedKey(
                        compressedPublicKeyBytes,
                        hashedSignOriginData,
                        signatureBytes,
                        org.omnione.did.crypto.enums.EccCurveType.Secp256r1);
            } catch (CryptoException e) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Parses JWT header and extracts kid (key identifier) information.
     *
     * @param header The Base64 URL encoded JWT header
     * @return The extracted kid information
     * @throws CommonSdkException if header parsing or kid extraction fails
     */
    public static String extractKidFromHeader(String header) {
        try {
            // Decode Base64 URL header
            byte[] headerBytes = Base64.getUrlDecoder().decode(header);
            String headerJson = new String(headerBytes, StandardCharsets.UTF_8);

            // Parse JSON
            Map<String, Object> headerMap = gson.fromJson(headerJson, new TypeToken<Map<String, Object>>() {
            }.getType());

            // Extract kid information
            String kid = (String) headerMap.get("kid");

            if (kid == null) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

            return kid;

        } catch (JsonSyntaxException e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Validates that the signer DID from JWT header matches the DID document ID.
     *
     * @param kid               The key identifier from JWT header
     * @param signerDidDocument The DID document of the token signer
     * @throws CommonSdkException if DID validation fails
     */
    public static void validateSignerDid(String kid, DidDocument signerDidDocument) {
        try {
            // Extract DID from kid
            String signerDidFromHeader = DidUtil.extractDid(kid);

            // Get DID from document
            String signerDidFromDocument = signerDidDocument.getId();

            // Validate they match
            if (!signerDidFromHeader.equals(signerDidFromDocument)) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Extracts PII (Personally Identifiable Information) from JWT payload.
     *
     * @param payload The Base64 URL encoded JWT payload
     * @return The extracted PII information
     * @throws CommonSdkException if payload parsing or PII extraction fails
     */
    public static String extractPiiFromPayload(String payload) {
        try {
            // Decode Base64 URL payload
            byte[] payloadBytes = Base64.getUrlDecoder().decode(payload);
            String payloadJson = new String(payloadBytes, StandardCharsets.UTF_8);

            // Parse JSON
            Map<String, Object> payloadMap = gson.fromJson(payloadJson, new TypeToken<Map<String, Object>>() {
            }.getType());

            // Extract PII information (try different field names)
            String pii = (String) payloadMap.get("pii");
            if (pii == null) {
                pii = (String) payloadMap.get("sub");
            }

            if (pii == null) {
                throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
            }

            return pii;

        } catch (JsonSyntaxException e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Converts object to long value for timestamp processing.
     *
     * @param obj       The object to convert
     * @param fieldName The field name for error logging
     * @return The long value
     * @throws NumberFormatException if conversion fails
     */
    private static long convertToLong(Object obj, String fieldName) throws NumberFormatException {
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        } else {
            return Long.parseLong(obj.toString());
        }
    }

    /**
     * Extracts and parses time claims (iat and exp) from JWT payload.
     *
     * @param payloadMap The parsed JWT payload map
     * @return JwtTimeClaims containing iat, exp, and current time
     * @throws CommonSdkException if time claims are missing or invalid
     */
    private static JwtTimeClaims extractJwtTimeClaims(Map<String, Object> payloadMap) {
        Object iatObj = payloadMap.get("iat");
        Object expObj = payloadMap.get("exp");

        if (iatObj == null) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }

        if (expObj == null) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }

        try {
            long iat = convertToLong(iatObj, "iat");
            long exp = convertToLong(expObj, "exp");
            long currentTime = Instant.now().getEpochSecond();

            return new JwtTimeClaims(iat, exp, currentTime);

        } catch (NumberFormatException e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Validates JWT time claims against current time.
     *
     * @param timeClaims The JWT time claims to validate
     * @throws CommonSdkException if validation fails
     */
    private static void validateTimeLogic(JwtTimeClaims timeClaims) {
        long currentTime = timeClaims.getCurrentTime();
        long iat = timeClaims.getIat();
        long exp = timeClaims.getExp();

        // 1. Check if token has expired
        if (currentTime >= exp) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }

        // 2. Check if token was issued in the future (with clock skew tolerance)
        long clockSkewTolerance = 300; // 5 minutes
        if (currentTime + clockSkewTolerance < iat) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }

        // 3. Check if expiration time is after issued time
        if (exp <= iat) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Validates JWT token time claims (iat and exp).
     *
     * @param payload The Base64 URL encoded JWT payload
     * @throws CommonSdkException if time validation fails
     */
    public static void validateTokenTimeClaimsFromPayload(String payload) {
        try {
            // 1. Decode and parse payload
            byte[] payloadBytes = Base64.getUrlDecoder().decode(payload);
            String payloadJson = new String(payloadBytes, StandardCharsets.UTF_8);

            Map<String, Object> payloadMap = gson.fromJson(payloadJson, new TypeToken<Map<String, Object>>() {
            }.getType());

            // 2. Extract time claims
            JwtTimeClaims timeClaims = extractJwtTimeClaims(payloadMap);

            // 3. Validate time logic
            validateTimeLogic(timeClaims);

        } catch (JsonSyntaxException e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Verifies a JWT token without extracting PII.
     * Validates signature, DID, and time claims.
     *
     * @param jwtToken          The JWT token to validate
     * @param signerDidDocument The DID document of the token signer
     * @throws CommonSdkException if any step of the validation fails
     */
    public static void verifyJwt(String jwtToken, DidDocument signerDidDocument) {
        try {
            JwtComponents jwtComponents = splitJwtToken(jwtToken);
            String kid = extractKidFromHeader(jwtComponents.getHeader());
            validateSignerDid(kid, signerDidDocument);
            String publicKey = extractPublicKeyFromDidDocument(signerDidDocument);
            verifyJwtSignature(jwtComponents, publicKey);
            validateTokenTimeClaimsFromPayload(jwtComponents.getPayload());
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Complete JWT token validation and PII extraction process.
     * This is a high-level method that orchestrates the entire validation flow.
     *
     * @param jwtToken          The JWT token to validate
     * @param signerDidDocument The DID document of the token signer
     * @return The extracted PII information
     * @throws CommonSdkException if any step of the validation fails
     */
    public static String validateTokenAndExtractPii(String jwtToken, DidDocument signerDidDocument) {
        try {
            // 1. Split JWT token into components
            JwtComponents jwtComponents = splitJwtToken(jwtToken);

            // 2. Extract kid from JWT header and validate signer DID
            String kid = extractKidFromHeader(jwtComponents.getHeader());
            validateSignerDid(kid, signerDidDocument);

            // 3. Extract public key from signer DID document
            String publicKey = extractPublicKeyFromDidDocument(signerDidDocument);

            // 4. Verify JWT signature
            verifyJwtSignature(jwtComponents, publicKey);

            // 5. Validate JWT time claims (iat and exp)
            validateTokenTimeClaimsFromPayload(jwtComponents.getPayload());

            // 6. Extract PII from payload
            String pii = extractPiiFromPayload(jwtComponents.getPayload());

            return pii;

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN);
        }
    }

    /**
     * Check whether the token has expired based on the "exp" claim.
     *
     * @param token JWT token string
     * @return true if expired or expiration cannot be determined, false if still valid
     * @throws CommonSdkException if the token cannot be parsed
     */
    public static Boolean isTokenExpired(String token) throws CommonSdkException {
        long exp = getExpirationDateFromToken(token);
        long now = Instant.now().getEpochSecond();
        return now >= exp;
    }

    /**
     * Extract the "exp" (expiration) claim from a JWT token.
     *
     * @param token JWT token string
     * @return expiration time as Unix epoch seconds
     * @throws CommonSdkException if the token cannot be parsed or "exp" is missing
     */
    public static long getExpirationDateFromToken(String token) throws CommonSdkException {
        Map<String, Object> payload = parsePayloadAsMap(token);
        Object expObj = payload.get("exp");
        if (expObj == null) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN, "Missing 'exp' claim in token");
        }
        return ((Number) expObj).longValue();
    }

    /**
     * Extract the "sub" (subject / user ID) claim from a JWT token.
     *
     * @param token JWT token string
     * @return subject string
     * @throws CommonSdkException if the token cannot be parsed or "sub" is missing
     */
    public static String getUserIdFromToken(String token) throws CommonSdkException {
        Map<String, Object> payload = parsePayloadAsMap(token);
        String sub = (String) payload.get("sub");
        if (sub == null) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN, "Missing 'sub' claim in token");
        }
        return sub;
    }

    /**
     * Extract the "aud" (audience) claim from a JWT token.
     *
     * @param token JWT token string
     * @return list of audience strings (may be empty)
     * @throws CommonSdkException if the token cannot be parsed
     */
    @SuppressWarnings("unchecked")
    public static java.util.List<String> getAudienceFromToken(String token) throws CommonSdkException {
        Map<String, Object> payload = parsePayloadAsMap(token);
        Object audObj = payload.get("aud");
        if (audObj == null) {
            return java.util.Collections.emptyList();
        }
        if (audObj instanceof java.util.List) {
            return (java.util.List<String>) audObj;
        }
        return java.util.Collections.singletonList(audObj.toString());
    }

    /**
     * Validate whether the token's "aud" claim contains the expected audience.
     *
     * @param token            JWT token string
     * @param expectedAudience Expected audience value
     * @return true if the audience is present and matches, false otherwise
     * @throws CommonSdkException if the token cannot be parsed
     */
    public static Boolean validateAudience(String token, String expectedAudience) throws CommonSdkException {
        if (expectedAudience == null) {
            return false;
        }
        java.util.List<String> aud = getAudienceFromToken(token);
        return aud.contains(expectedAudience);
    }

    /**
     * Extract the "iss" (issuer) claim from a JWT token.
     *
     * @param token JWT token string
     * @return issuer string
     * @throws CommonSdkException if the token cannot be parsed or "iss" is missing
     */
    public static String getIssuerFromToken(String token) throws CommonSdkException {
        Map<String, Object> payload = parsePayloadAsMap(token);
        String iss = (String) payload.get("iss");
        if (iss == null) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN, "Missing 'iss' claim in token");
        }
        return iss;
    }

    /**
     * Parse the JWT payload into a raw key-value map.
     * Decodes the Base64 URL-encoded payload and deserializes the JSON.
     *
     * @param token JWT token string
     * @return parsed payload map
     * @throws CommonSdkException if the token format or payload JSON is invalid
     */
    private static Map<String, Object> parsePayloadAsMap(String token) throws CommonSdkException {
        try {
            JwtComponents jwtComponents = splitJwtToken(token);
            byte[] payloadBytes = Base64.getUrlDecoder().decode(jwtComponents.getPayload());
            String payloadJson = new String(payloadBytes, StandardCharsets.UTF_8);
            return gson.fromJson(payloadJson, new TypeToken<Map<String, Object>>() {}.getType());
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.INVALID_ACCESS_TOKEN, "Failed to parse token payload: " + e.getMessage());
        }
    }

    /**
     * Parse Token.
     *
     * @param token Parsing token
     * @return TokenData
     * @throws CommonSdkException if parsing fails
     */
    public static TokenData parseToken(String token) throws CommonSdkException {
        try {
            JwtComponents jwtComponents = splitJwtToken(token);
            byte[] payloadBytes = Base64.getUrlDecoder().decode(jwtComponents.getPayload());
            String payloadJson = new String(payloadBytes, StandardCharsets.UTF_8);

            return gson.fromJson(payloadJson, TokenData.class);
        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.TOKEN_PARSE_FAILED, "Failed to parse token: " + e.getMessage());
        }
    }

    /**
     * Generate Access Token.
     *
     * @param walletManager     Wallet Manager
     * @param keyId             Key ID
     * @param tokenData         Token Data
     * @param expirationSeconds Expiration time in seconds
     * @return Access Token
     * @throws CommonSdkException if generation fails
     */
    public static String generateAccessToken(
            WalletManagerInterface walletManager,
            String keyId,
            TokenData tokenData,
            long expirationSeconds) throws CommonSdkException {
        try {
            // 1. Set Expiration Time
            long currentTime = Instant.now().getEpochSecond();
            long expirationTime = currentTime + expirationSeconds;
            tokenData.setExpirationTime(expirationTime);

            // 2. Create Header
            Map<String, Object> headerMap = new HashMap<>();
            headerMap.put("typ", "JWT");
            headerMap.put("alg", "ES256");
            headerMap.put("kid", keyId);

            String headerJson = gson.toJson(headerMap);
            String encodedHeader = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(headerJson.getBytes(StandardCharsets.UTF_8));

            // 3. Create Payload - merge TokenData and standardized claims
            String tokenDataJson = gson.toJson(tokenData);
            Map<String, Object> payloadMap = gson.fromJson(tokenDataJson, new TypeToken<Map<String, Object>>() {
            }.getType());

            // Add standard claims if not present in TokenData
            payloadMap.put("iat", currentTime);
            payloadMap.put("exp", expirationTime);

            String payloadJson = gson.toJson(payloadMap);
            String encodedPayload = Base64.getUrlEncoder().withoutPadding()
                    .encodeToString(payloadJson.getBytes(StandardCharsets.UTF_8));

            // 4. Create Signature Origin
            String signatureOrigin = encodedHeader + "." + encodedPayload;

            // 5. Sign
            byte[] hashedData = DigestUtils.getDigest(signatureOrigin.getBytes(StandardCharsets.UTF_8),
                    DigestType.SHA256);
            byte[] signatureBytes = walletManager.generateCompactSignatureFromHash(keyId, hashedData);
            String encodedSignature = Base64.getUrlEncoder().withoutPadding().encodeToString(signatureBytes);

            return signatureOrigin + "." + encodedSignature;

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.TOKEN_GENERATION_FAILED,
                    "Failed to generate access token: " + e.getMessage());
        }
    }

    /**
     * Verify Access Token.
     *
     * @param token     Access Token
     * @param publicKey Public Key
     * @return TokenData
     * @throws CommonSdkException if verification fails
     */
    public static TokenData verifyAccessToken(String token, String publicKey) throws CommonSdkException {
        try {
            // 1. Verify Signature
            JwtComponents jwtComponents = splitJwtToken(token);
            verifyJwtSignature(jwtComponents, publicKey);

            // 2. Validate Time Claims
            validateTokenTimeClaimsFromPayload(jwtComponents.getPayload());

            // 3. Parse and Return TokenData
            return parseToken(token);

        } catch (CommonSdkException e) {
            throw e;
        } catch (Exception e) {
            throw new CommonSdkException(ErrorCode.TOKEN_SIGNATURE_VERIFICATION_FAILED,
                    "Failed to verify access token: " + e.getMessage());
        }
    }
}
