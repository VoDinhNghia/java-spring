package spring.springboot.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoPassword {
    private static MessageDigest md;
    private static String alg = "MD5";

    public String endCode(String password) {
        try {
            md = MessageDigest.getInstance(alg);
            byte[] passBytes = password.getBytes();
            md.reset();
            byte[] digested = md.digest(passBytes);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < digested.length; i++) {
                sb.append(Integer.toHexString(0xff & digested[i]));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }
}
