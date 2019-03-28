package com.mrmrmr7.mytunes.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.dao.GenericDao;
import com.mrmrmr7.mytunes.dao.UserDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.UserService;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.ExceptionDirector;
import com.mrmrmr7.mytunes.util.KeyPairUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

public class UserServiceImpl implements UserService {
    private final static String USERNAME = "mytunesmaler@gmail.com";
    private final static String PASSWORD = "CStheBEST7";
    private static final String COOKIE_TOKEN = "token";
    private static final String COOKIE_PUBLIC_KEY = "publicKey";
    private static final int COOKIE_COUNT = 2;

    public boolean validateTokenById(String token, String publicKey) throws ServiceException {
        DecodedJWT decodedJWT = JWT.decode(token);
        int id = decodedJWT.getClaim("userId").asInt();
        try {
            Optional<User> userOptional = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(id);
            if (userOptional.isPresent()) {
                String rsaKeyStr = userOptional.get().getPrivateKey();
                byte[] decoredRSA = Base64.getDecoder().decode(rsaKeyStr);
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(decoredRSA);
                String rightPublic = publicKey.replace(" ", "+");
                decoredRSA = Base64.getDecoder().decode(rightPublic);
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decoredRSA);
                RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
                RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);

                Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

                JWTVerifier jwtVerifier = JWT
                        .require(algorithm)
                        .withClaim("userId", userOptional.get().getId())
                        .withClaim("userEmail", userOptional.get().getEmail())
                        .acceptExpiresAt(18_000)
                        .build();

                jwtVerifier.verify(token);
                return true;
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException(e.getMessage());
        } catch (InvalidKeySpecException e) {
            throw new ServiceException(e.getMessage());
        } catch (JWTVerificationException e) {
        }
        return true;
    }

    public boolean tryRestartPassword(String email) throws ServiceException {

        try {
            Optional<User> userOptional = ((UserDaoExtended) JdbcDaoFactory.getInstance().getDao(User.class)).getByEmail(email);
            if (userOptional.isPresent()) {
                boolean restartStarted = true;
                User user = userOptional.get();

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
                        .withClaim("userId", user.getId())
                        .withClaim("userEmail", user.getEmail())
                        .withExpiresAt(new Date(Calendar.getInstance().getTime().getTime()))
                        .sign(algorithm);


                try {
                    JdbcDaoFactory
                            .getInstance()
                            .getDao(User.class)
                            .update(user);
                } catch (DaoException e) {
                    throw new ServiceException(e.getMessage());
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
                    message.setSubject("Mytunes, restart password");
                    message.setText("Dear " + user.getLogin() + " ," +
                            "\n\n" +
                            "If you didn't start password restart on site MyTunes, ignore this message! " +
                            "\n\n" +
                            "Link to change password (do not show it to anyone): " +
                            "\n\n" +
                            "http://localhost:8080/crud?command=" + CommandDirector.VIEW_RESTART_PASSWORD_PAGE.getValue() + "&token=" + token + "&publicKey=" + rsaPublicKeyStr);

                    Transport.send(message);
                } catch (MessagingException e) {
                    restartStarted = false;
                    e.printStackTrace();
                }

                return restartStarted;
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return false;
    }

    private Properties getProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        return properties;
    }

    @Override
    public boolean isRightUser(String login, String password) throws ServiceException {

        try {
            Optional<User> user;
            user = ((UserDaoExtended) JdbcDaoFactory.getInstance().getDao(User.class)).getByLogin(login);
            return user.filter(user1 -> BCrypt.checkpw(password, user1.getPassword())).isPresent();
        } catch (DaoException e) {
        }

        return false;
    }

    @Override
    public Map<String, Cookie> getCookies(String login) throws ServiceException {
        Map<String, Cookie> cookieMap = new HashMap<>();

        KeyPair kp = KeyPairUtil.getKeyPair();
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
        RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();

        String token = null;
        try {
            Optional<User> userOptional = ((UserDaoExtended) JdbcDaoFactory.getInstance().getDao(User.class)).getByLogin(login);
            JdbcDaoFactory.getInstance().getDao(User.class).update(userOptional.get());
            userOptional.get().setPrivateKey(Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            token = JWT
                    .create()
                    .withClaim("userId", userOptional.get().getId())
                    .withClaim("userLogin", userOptional.get().getLogin())
                    .withClaim("userRole", (int) userOptional.get().getRoleId())
                    .sign(algorithm);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        Cookie cookieToken = new Cookie(COOKIE_TOKEN, token);
        String publicKey = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
        Cookie cookiePublicKey = new Cookie(COOKIE_PUBLIC_KEY, publicKey);
        cookieMap.put(COOKIE_TOKEN, cookieToken);
        cookieMap.put(COOKIE_PUBLIC_KEY, cookiePublicKey);

        return cookieMap;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        Optional<Cookie> cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();

        cookieToken.ifPresent(c -> {
            c.setMaxAge(0);
            httpServletResponse.addCookie(c);
        });

        cookiePublicKey.ifPresent(c -> {
            c.setMaxAge(0);
            httpServletResponse.addCookie(c);
        });
    }

    @Override
    public boolean isAuthorized(String command, HttpServletRequest httpServletRequest) throws ServiceException {
        Cookie[] cookies = httpServletRequest.getCookies();
        Optional<Cookie> cookieToken;
        Optional<Cookie> cookiePublicKey;

        if (cookies != null && cookies.length >= COOKIE_COUNT) {
            cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();

            if (!cookieToken.isPresent()) {
                return false;
            }

            cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();
            if (!cookiePublicKey.isPresent()) {
                return false;
            }


            try {
                DecodedJWT decodedJWT = JWT.decode(cookieToken.get().getValue());
                Claim userIdClaim = decodedJWT.getClaim("userId");
                String publiKey = cookiePublicKey.get().getValue();

                Optional<User> userOptional = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(userIdClaim.asInt());

                if (!userOptional.isPresent()) {
                    return false;
                }

                User user = userOptional.get();
                byte[] byteArray = Base64.getDecoder().decode(user.getPrivateKey());
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteArray);
                RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory
                        .getInstance("RSA")
                        .generatePrivate(pkcs8EncodedKeySpec);

                byteArray = Base64.getDecoder().decode(publiKey);
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(byteArray);
                RSAPublicKey rsaPublicKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(x509EncodedKeySpec);

                Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

                JWTVerifier jwtVerifier = JWT
                        .require(algorithm)
                        .withClaim("userId", user.getId())
                        .withClaim("userLogin", user.getLogin())
                        .withClaim("userRole", (int) user.getRoleId())
                        .build();

                jwtVerifier.verify(cookieToken.get().getValue());

                httpServletRequest.setAttribute("role", user.getRoleId());

            } catch (DaoException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                return false;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (JWTVerificationException e) {
                return false;
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changePassword(int id, String password) throws ServiceException {
        try {
            Optional<User> userOptional = JdbcDaoFactory.getInstance().getDao(User.class).getByPK(id);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                user.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
                try {
                    JdbcDaoFactory.getInstance().getDao(User.class).update(user);
                } catch (DaoException e) {
                    throw new ServiceException(e.getMessage());
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }
    }

}
