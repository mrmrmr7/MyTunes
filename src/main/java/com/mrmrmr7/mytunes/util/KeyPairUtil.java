package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.entity.User;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

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

        String publicEncoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        user.setPublicKey(publicEncoded);
        String privateEncoded = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        user.setPrivateKey(privateEncoded);
    }
}
