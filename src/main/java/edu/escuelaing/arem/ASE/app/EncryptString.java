package edu.escuelaing.arem.ASE.app;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptString {

    public static String shaPassword(String password) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        byte[] hash = md.digest(password.getBytes());

        StringBuffer sb = new StringBuffer();
        for(byte b : hash) {
            sb.append(String.format("%02x", b));
        }

        return  sb.toString();
    }
}
