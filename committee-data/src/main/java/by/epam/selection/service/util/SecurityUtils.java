package by.epam.selection.service.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Util class for security purposes.
 *
 * @author Alex Aksionchik 12/27/2017
 * @version 1.0
 */
public final class SecurityUtils {

    private static final String ALGORITHM = "MD5";

    private SecurityUtils() {}

    public static String md5Hex(String password) {
        String hash;
        try {
            MessageDigest md  = MessageDigest.getInstance(ALGORITHM);
            md.update(password.getBytes());
            byte[] digest = md.digest();
            hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm.");
        }
        return hash;
    }

}
