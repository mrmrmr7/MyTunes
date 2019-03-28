package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.dao.UserDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.SignUpService;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.KeyPairUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.jws.soap.SOAPBinding;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import java.io.Serializable;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.text.MessageFormat;
import java.util.*;

public class SignUpServiceImpl implements SignUpService {
    private final static String USERNAME = "mytunesmaler@gmail.com";
    private final static String PASSWORD = "CStheBEST7";

    @Override
    public Properties getProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return properties;
    }


    @Override
    public boolean  sendSignUpMessage(User user) throws ServiceException {
        Properties mailProperties = getProperties();

        KeyPair keyPair = KeyPairUtil.getKeyPair();

        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();

        String rsaPrivateKeyStr = Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded());
        String rsaPublicKeyStr = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());

        user.setPrivateKey(rsaPrivateKeyStr);

        Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

        String token = JWT
                .create()
                .withClaim("userLogin", user.getLogin())
                .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime()))
                .sign(algorithm);

        try {
            JdbcDaoFactory
                    .getInstance()
                    .getDao(User.class)
                    .insert(user);
        } catch (Exception e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
        }

        Session session = Session.getInstance(mailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        String mailTo = user.getEmail();
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject("Mytunes, registration");
            message.setText("Dear " + user.getLogin() + " ," +
                    "\n\n" +
                    "If you didn't register on site MyTunes, ignore this message! " +
                    "\n\n" +
                    "Please, confirm your registration by this link: " +
                    "\n\n" +
                    "http://localhost:8080/crud?command=finishRegistration&token=" + token + "&publicKey=" + rsaPublicKeyStr);

            Transport.send(message);
        } catch (MessagingException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_SEND_MESSAGE) + e.getMessage());
        }

        System.out.println("Done");
        return true;
    }

    @Override
    public boolean finishSignUp(HttpServletRequest request) throws ServiceException {
        DecodedJWT decodedJWT = JWT.decode(request.getParameter("token"));

        try {
            Optional<User> userOptional = ((UserDaoExtended)JdbcDaoFactory.getInstance().getDao(User.class))
                    .getByLogin(decodedJWT.getClaim("userLogin").asString());
            String publicKey = request.getParameter("publicKey");

            String rightPublic = publicKey.replace(" ", "+");
            byte[] arr = Base64
                    .getDecoder()
                    .decode(rightPublic);
            X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(arr);

            arr = Base64.getDecoder().decode(userOptional.get().getPrivateKey());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec1 = new PKCS8EncodedKeySpec(arr);

            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec1);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);

            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

            JWTVerifier jwtVerifier = JWT.require(algorithm).withClaim("userLogin", userOptional.get().getLogin()).acceptExpiresAt(1800).build();
            jwtVerifier.verify(request.getParameter("token"));

            userOptional.ifPresent(s -> s.setStatusId((byte)1));

            JdbcDaoFactory.getInstance().getDao(User.class).update(userOptional.get());
        } catch (DaoException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.IMPOSSIBLE_GET) + e.getMessage());
        } catch (JWTVerificationException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_TOKEN) + e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_DATA) + e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new ServiceException(MessageFormat.format(ExceptionDirector.EXC_MSG, ExceptionDirector.INVALID_KEY) + e.getMessage());
        }

        return true;
    }
}
