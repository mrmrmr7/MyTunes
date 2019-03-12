package com.mrmrmr7.mytunes;

import com.fasterxml.jackson.databind.ser.Serializers;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.mindrot.jbcrypt.BCrypt;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class ClassForTests {

    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();

        System.out.println("Public format: " + publicKey.getFormat());
        System.out.println("Private format: " + privateKey.getFormat());

        for (int i = 0; i < 10; i++) {
            String privateEncoded = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            byte[] privateDecoded = Base64.getDecoder().decode(privateEncoded);
            PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateDecoded);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = keyFactory.generatePrivate(privateKeySpec);

            String publicEncoded = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            byte[] publicDecoded = Base64.getDecoder().decode(publicEncoded);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicDecoded);
            PublicKey publKey = keyFactory.generatePublic(publicKeySpec);

            Map<String, Object> claims = new HashMap<>();
            claims.put("id", 3);
            String token = Jwts
                    .builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.RS256, privKey)
                    .compact();

            System.out.println(token);
            System.out.println("token: " + token);
            System.out.println("PrivateKey: " + publicEncoded);
            System.out.println(Jwts.parser().setSigningKey(publicKey).isSigned("EsyJhbGciOiJSUzI1NiJ9.eyJsZCI6M30.Kq_GSBV1mXJQIU-rmvLu88F-qNRxz0S834VUSLFhULctb3YKO8ZcObqV35DvpiGY08ep90NtoIGcc__HnnSZLEE4IINWNe3sM8CdZlXrbB_YaTluT9-xKr4S-PwJKd45SKhipM5y_QGfJBHbzjuCB-u0X4EYFtyOMhf4oLHNyvwWFjXLMwePtt-Z9sS0OP57erz2OHxeQ_D-_Ss4gBOhtnREHEQtagadCKhddpPzsGp_Ov41lqNh2op3Gi85KB88ZAjGjURO3rD3MjEttoR2eBrIk9ovFV13TlTiwqhkMw13CzKC3U32MW9anKvhLpMvMOFN9ngzng7RK4A"));

            System.out.println(Jwts.parser().setSigningKey(publKey).parseClaimsJws(token).getBody());
        }


    }

}
