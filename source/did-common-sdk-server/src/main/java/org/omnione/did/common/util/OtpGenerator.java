package org.omnione.did.common.util;

import java.security.SecureRandom;

/**
 * Utility class for generating OTP (One-Time Password) codes.
 */
public class OtpGenerator {

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();
    private static final int DEFAULT_OTP_LENGTH = 6;
    private static final int MIN_OTP_VALUE = 100000;
    private static final int MAX_OTP_VALUE = 999999;

    private OtpGenerator() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Generate a 6-digit OTP code using SecureRandom.
     *
     * @return 6-digit OTP code as a string
     */
    public static String generateOtp() {
        return generateOtp(DEFAULT_OTP_LENGTH);
    }

    /**
     * Generate an OTP code with the specified length using SecureRandom.
     *
     * @param length the length of the OTP code (minimum 4, maximum 8)
     * @return OTP code as a string with the specified length
     * @throws IllegalArgumentException if length is not between 4 and 8
     */
    public static String generateOtp(int length) {
        if (length < 4 || length > 8) {
            throw new IllegalArgumentException("OTP length must be between 4 and 8 digits");
        }

        if (length == DEFAULT_OTP_LENGTH) {
            int otpValue = SECURE_RANDOM.nextInt(MAX_OTP_VALUE - MIN_OTP_VALUE + 1) + MIN_OTP_VALUE;
            return String.valueOf(otpValue);
        }

        int minValue = (int) Math.pow(10, length - 1);
        int maxValue = (int) Math.pow(10, length) - 1;
        int otpValue = SECURE_RANDOM.nextInt(maxValue - minValue + 1) + minValue;
        return String.format("%0" + length + "d", otpValue);
    }

    /**
     * Validate whether the given string is a valid 6-digit OTP.
     *
     * @param otp OTP string to validate
     * @return true if valid (6 digits), false otherwise
     */
    public static boolean isValidOtpFormat(String otp) {
        return isValidOtpFormat(otp, DEFAULT_OTP_LENGTH);
    }

    /**
     * Validate whether the given string is a valid OTP of the specified length.
     *
     * @param otp    OTP string to validate
     * @param length expected digit length
     * @return true if valid format, false otherwise
     */
    public static boolean isValidOtpFormat(String otp, int length) {
        if (otp == null || otp.isEmpty()) {
            return false;
        }
        return otp.matches("\\d{" + length + "}");
    }
}
