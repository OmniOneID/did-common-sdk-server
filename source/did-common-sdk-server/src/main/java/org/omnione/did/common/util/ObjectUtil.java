package org.omnione.did.common.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Objects;

/**
 * General-purpose object utility methods.
 */
public class ObjectUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private ObjectUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Null-safe equality check using {@link Objects#equals}.
     *
     * @param a first object
     * @param b second object
     * @return true if both are equal (or both null)
     */
    public static boolean equals(Object a, Object b) {
        return Objects.equals(a, b);
    }

    /**
     * Case-insensitive null-safe equality check for strings.
     *
     * @param a first string
     * @param b second string
     * @return true if both strings are equal ignoring case
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        if (Objects.equals(a, b)) return true;
        if (a == null || b == null) return false;
        return a.equalsIgnoreCase(b);
    }

    /**
     * Compare two version strings (e.g. "1.2.3" vs "1.2.4").
     * Segments are compared numerically left-to-right.
     *
     * @param v1 first version string (non-null)
     * @param v2 second version string (non-null)
     * @return negative if v1 < v2, zero if equal, positive if v1 > v2
     * @throws IllegalArgumentException if either argument is null
     */
    public static int compareVersion(String v1, String v2) {
        if (v1 == null || v2 == null) throw new IllegalArgumentException("version must not be null");
        String[] a = v1.trim().split("\\.");
        String[] b = v2.trim().split("\\.");
        int n = Math.max(a.length, b.length);
        for (int i = 0; i < n; i++) {
            int x = i < a.length ? parseIntSafe(a[i]) : 0;
            int y = i < b.length ? parseIntSafe(b[i]) : 0;
            if (x != y) return Integer.compare(x, y);
        }
        return 0;
    }

    private static int parseIntSafe(String s) {
        if (s == null || s.isEmpty()) return 0;
        return Integer.parseInt(s.trim());
    }

    /**
     * Check whether a JSON array string contains the given target value.
     * Falls back to a simple string-contains check if JSON parsing fails.
     *
     * @param jsonArrayString JSON array string (e.g. {@code ["a","b"]})
     * @param targetValue     value to search for
     * @return true if the array contains the target value
     */
    public static boolean containsInJsonArray(String jsonArrayString, String targetValue) {
        if (jsonArrayString == null || jsonArrayString.isBlank() || targetValue == null) {
            return false;
        }

        try {
            List<String> list = objectMapper.readValue(jsonArrayString, new TypeReference<List<String>>() {});
            return list.contains(targetValue);
        } catch (Exception e) {
            return jsonArrayString.contains("\"" + targetValue + "\"");
        }
    }
}
