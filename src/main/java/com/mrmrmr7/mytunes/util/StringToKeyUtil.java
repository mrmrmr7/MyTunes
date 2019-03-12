package com.mrmrmr7.mytunes.util;

import javax.servlet.http.Cookie;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class StringToKeyUtil {

    public static PublicKey toPublicKey(String data) {
        byte[] byteArray = Base64
                .getDecoder()
                .decode(data
                        .getBytes());

        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteArray);
        KeyFactory keyFactory = null;

        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        PublicKey publicKey = null;

        try {
            publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
}
