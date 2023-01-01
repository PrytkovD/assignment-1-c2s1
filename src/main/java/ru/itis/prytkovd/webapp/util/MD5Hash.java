package ru.itis.prytkovd.webapp.util;

import jakarta.xml.bind.DatatypeConverter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {
    public static String hash(String string) {
        MessageDigest messageDigest = null;

        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        messageDigest.update(string.getBytes());
        byte[] digest = messageDigest.digest();

        return DatatypeConverter
            .printHexBinary(digest)
            .toUpperCase();
    }
}
