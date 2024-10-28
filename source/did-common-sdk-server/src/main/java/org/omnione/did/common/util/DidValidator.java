package org.omnione.did.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * The DidValidator class provides utility methods for validating and extracting
 * information from DID and DID KEy URL. It includes methods for:
 */
public class DidValidator {
    private static final String DID_REGEX = "^did:[a-z0-9]+:(?:[a-zA-Z0-9._%-]*:)*[a-zA-Z0-9._%-]+$";
    private static final String DID_KEY_URL_REGEX = "^did:[a-z0-9]+:[a-zA-Z0-9._%-]+\\?versionId=\\d+#.+$";

    private static final Pattern DID_PATTERN = Pattern.compile(DID_REGEX);
    private static final Pattern DID_KEY_URL_PATTERN = Pattern.compile(DID_KEY_URL_REGEX);

    /**
     * Validate the format of a given DID string.
     * @param did The DID string to validate
     * @return true if the DID string is valid, false otherwise
     */
    public static boolean isValidDid(String did) {
        if (did == null) {
            return false;
        }

        Matcher matcher = DID_PATTERN.matcher(did);
        return matcher.matches();
    }

    /**
     * Validate the format of a given DID Key URL string.
     * @param didKeyUrl The DID Key URL string to validate
     * @return true if the DID Key URL string is valid, false otherwise
     */
    public static boolean isValidDidKeyUrl(String didKeyUrl) {
        if (didKeyUrl == null) {
            return false;
        }

        Matcher matcher = DID_KEY_URL_PATTERN.matcher(didKeyUrl);
        return matcher.matches();
    }
}
