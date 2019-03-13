package com.mrmrmr7.mytunes.util;

import com.mrmrmr7.mytunes.service.ServiceException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.http.Cookie;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class ProtectionUtil {


    public static PublicKey stringToPublicKey(String data) {
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

    public static Claims getClaimsFromCookies(Cookie[] cookieArray) {
        Optional<Cookie> cookieToken;
        Optional<Cookie> cookiePublicKey;

        cookieToken = Arrays.stream(cookieArray).filter(s -> s.getName().equals("token")).findFirst();

        if (!cookieToken.isPresent()) {
            return null;
        }

        cookiePublicKey = Arrays.stream(cookieArray).filter(s -> s.getName().equals("publicKey")).findFirst();
        if (!cookiePublicKey.isPresent()) {
            return null;
        }

        PublicKey publicKey = ProtectionUtil.stringToPublicKey(cookiePublicKey.get().getValue());

        Claims claims = null;

        try {
            claims = Jwts
                    .parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(cookieToken
                            .get()
                            .getValue())
                    .getBody();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return claims;
    }
}
