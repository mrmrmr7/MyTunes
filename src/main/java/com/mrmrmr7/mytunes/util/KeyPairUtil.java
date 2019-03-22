package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.entity.User;
import org.apache.commons.codec.binary.Base64;

import java.security.*;

public class KeyPairUtil {
    public static void setKeysToUser(User user) {
        java.security.KeyPairGenerator keyPairGenerator = null;

        try {
            keyPairGenerator = java.security.KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        String publicEncoded = Base64.encodeBase64String(publicKey.getEncoded());
        String privateEncoded = Base64.encodeBase64String(privateKey.getEncoded());
        user.setPrivateKey(privateEncoded);
    }

    public static KeyPair getKeyPair() {
        java.security.KeyPairGenerator keyPairGenerator = null;

        try {
            keyPairGenerator = java.security.KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        return keyPair;
    }
}
