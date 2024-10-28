package org.omnione.did.common.util;

/**
 * The DidUtil class provides utility methods for working with DID.
 * This class includes methods for:
 */
public class DidUtil {

    /**
     * Extract the DID from a given DID URL, removing any query or fragment components.
     * @param didKeyUrl The full DID URL string which may contain query parameters or fragments.
     * @return The DID without any query parameters or fragments.
     */
    public static String extractDid(String didKeyUrl) {
        if (didKeyUrl == null) {
            return null;
        }

        int queryIndex = didKeyUrl.indexOf('?');
        int fragmentIndex = didKeyUrl.indexOf('#');

        int endIndex = didKeyUrl.length();

        if (queryIndex != -1) {
            endIndex = queryIndex;
        }

        if (fragmentIndex != -1 && (fragmentIndex < endIndex)) {
            endIndex = fragmentIndex;
        }

        return didKeyUrl.substring(0, endIndex);
    }

    /**
     * Extract the key ID from a given DID Key URL, removing any query or fragment components.
     *
     * @param didKeyUrl The full DID Key URL string which may contain query parameters or fragments.
     * @return The key ID without any query parameters or fragments.
     */
    public static String extractKeyId(String didKeyUrl) {
        if (didKeyUrl == null) {
            return null;
        }

        int fragmentIndex = didKeyUrl.indexOf('#');
        if (fragmentIndex == -1) {
            throw new IllegalArgumentException("URL does not contain a fragment identifier (#).");
        }

        return didKeyUrl.substring(fragmentIndex + 1);
    }
}
