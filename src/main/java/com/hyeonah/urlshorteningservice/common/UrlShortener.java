package com.hyeonah.urlshorteningservice.common;

public class UrlShortener {
    private static final int BASE62_NUMBER = 62;
    private static final String BASE62_CHAR = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private UrlShortener() {
    }

    public static String generateShorteningKey(final long sequence) {
        return encodingBase62(sequence);
    }

    public static long getSeqByShorteningKey(final String shorteningKey) {
        return decodingBase62(shorteningKey);
    }

    private static String encodingBase62(long sequence) {
        final StringBuilder sb = new StringBuilder();
        while (sequence > 0) {
            final int num = (int) (sequence % BASE62_NUMBER);
            sb.append(BASE62_CHAR.charAt(num));
            sequence /= BASE62_NUMBER;
        }
        return sb.toString();
    }

    private static long decodingBase62(final String shorteningKey) {
        long sum = 0;
        long power = 1;
        for (int i = 0; i < shorteningKey.length(); i++) {
            sum += BASE62_CHAR.indexOf(shorteningKey.charAt(i)) * power;
            power *= BASE62_NUMBER;
        }
        return sum;
    }
}
