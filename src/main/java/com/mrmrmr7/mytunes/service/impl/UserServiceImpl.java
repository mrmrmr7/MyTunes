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
import com.mrmrmr7.mytunes.service.ServiceUser;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.util.KeyPairUtil;
import com.mrmrmr7.mytunes.validator.ValidatorSignIn;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Optional;

public class UserServiceImpl implements ServiceUser {
    private static final String COOKIE_TOKEN = "token";
    private static final String COOKIE_PUBLIC_KEY = "publicKey";
    private static final int COOKIE_COUNT = 2;

    @Override
    public boolean login(String login, String password, HttpServletResponse response) throws ServiceException {

        ValidatorSignIn validatorSignIn = new ValidatorSignIn();

        if (!validatorSignIn.isValid(login, password)) {
            return false;
        }

        try {
            Optional<User> user;

            user = ((UserDaoExtended) JdbcDaoFactory.getInstance().getDao(User.class)).getByLogin(login);

            if (!user.isPresent()) {
                return false;
            }

            boolean isRightPassword = BCrypt.checkpw(password, user.get().getPassword());

            if (!isRightPassword) {
                return false;
            }

//            KeyPairUtil.setKeysToUser(user.get());

            KeyPair kp = KeyPairUtil.getKeyPair();

//            byte[] byteArray = Base64.decodeBase64(user.get().getPrivateKey());
//            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteArray);
//            PrivateKey privateKey = kp.getPrivate();

            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kp.getPrivate();
            RSAPublicKey rsaPublicKey = (RSAPublicKey) kp.getPublic();

            String publicKey = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());

//            try {
//                privateKey = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
//            } catch (InvalidKeySpecException e) {
//                e.printStackTrace();
//            }

//            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);

            user.get().setPrivateKey(Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));
            JdbcDaoFactory.getInstance().getDao(User.class).update(user.get());

            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

            String token = JWT
                    .create()
                    .withClaim("userId", user.get().getId())
                    .withClaim("userLogin", user.get().getLogin())
                    .withClaim("userRole", (int)user.get().getRoleId())
                    .sign(algorithm);
//
//            Map<String, Object> claimMap = new HashMap<>();
//
//            claimMap.put("userId", user.get().getId());
//            claimMap.put("userLogin", user.get().getLogin());
//            claimMap.put("userRole", user.get().getRoleId());
//
//            String tokentoken = Jwts
//                    .builder()
//                    .setClaims(claimMap)
//                    .signWith(SignatureAlgorithm.RS256, privateKey)
//                    .compact();

            Cookie cookieToken = new Cookie(COOKIE_TOKEN, token);
            response.addCookie(cookieToken);
            Cookie cookiePublicKey = new Cookie(COOKIE_PUBLIC_KEY, publicKey);
            response.addCookie(cookiePublicKey);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return true;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        Optional<Cookie> cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();
        Optional<Cookie> cookieLocale = Arrays.stream(cookies).filter(s -> s.getName().equals("locale")).findFirst();

        cookieToken.ifPresent(c -> {
            c.setMaxAge(0);
            httpServletResponse.addCookie(c);
        });

        cookiePublicKey.ifPresent(c -> {
            c.setMaxAge(0);
            httpServletResponse.addCookie(c);
        });

        cookieLocale.ifPresent(c -> {
            c.setMaxAge(0);
            httpServletResponse.addCookie(c);
        });
    }

    @Override
    public boolean register(User user) {
        return false;
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
                        .withClaim("userRole", (int)user.getRoleId())
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

}
