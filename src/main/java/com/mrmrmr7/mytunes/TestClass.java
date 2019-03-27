package com.mrmrmr7.mytunes;

import com.auth0.jwt.algorithms.Algorithm;

import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

public class TestClass {
    public static void main(String[] args) {
        java.security.KeyPairGenerator keyPairGenerator = null;

        try {
            keyPairGenerator = java.security.KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

        String rsaPrivateKeyStr = Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded());
        String rsaPublicKeyStr = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());

        System.out.println(rsaPrivateKeyStr.length());
    }
}
