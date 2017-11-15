package Helper;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateString {
    public static String generate(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        return RandomStringUtils.random( length, characters );
    }

    public static String generate() {
        return String.valueOf(java.util.UUID.randomUUID());
    }
}
