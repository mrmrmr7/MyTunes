package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.controller.command.CommandDirector;
import com.mrmrmr7.mytunes.dao.UserDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.exception.ServiceException;
import com.mrmrmr7.mytunes.service.ServiceUser;
import com.mrmrmr7.mytunes.util.KeyPairUtil;
import com.mrmrmr7.mytunes.util.ProtectionUtil;
import com.mrmrmr7.mytunes.validator.AuthorizationValidator;
import com.mrmrmr7.mytunes.validator.ValidatorSignIn;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

            KeyPairUtil.setKeysToUser(user.get());
            byte[] byteArray = Base64.decodeBase64(user.get().getPrivateKey());
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteArray);
            PrivateKey privateKey = null;
            try {
                privateKey = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);
            } catch (InvalidKeySpecException e) {
                e.printStackTrace();
            }

            JdbcDaoFactory.getInstance().getDao(User.class).update(user.get());

            Map<String, Object> claimMap = new HashMap<>();

            claimMap.put("userId", user.get().getId());
            claimMap.put("userLogin", user.get().getLogin());
            claimMap.put("userRole", user.get().getRoleId());

            String token = Jwts
                    .builder()
                    .setClaims(claimMap)
                    .signWith(SignatureAlgorithm.RS256, privateKey)
                    .compact();

            Cookie cookieToken = new Cookie(COOKIE_TOKEN, token);
            response.addCookie(cookieToken);
            Cookie cookiePublicKey = new Cookie(COOKIE_PUBLIC_KEY, user.get().getPublicKey());
            response.addCookie(cookiePublicKey);

        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse httpServletResponse) throws ServiceException {
        Cookie[] cookies = request.getCookies();
        Optional<Cookie> cookieToken = Arrays.stream(cookies).filter(s -> s.getName().equals("token")).findFirst();
        Optional<Cookie> cookiePublicKey = Arrays.stream(cookies).filter(s -> s.getName().equals("publicKey")).findFirst();

        cookieToken.ifPresent(s -> s.setMaxAge(0));
        cookiePublicKey.ifPresent(s -> s.setMaxAge(0));

        httpServletResponse.addCookie(cookieToken.get());
        httpServletResponse.addCookie(cookiePublicKey.get());
    }

    @Override
    public boolean register(User user) {
        return false;
    }

    @Override
    public boolean isAuthorized(String command, HttpServletRequest httpServletRequest) throws ServiceException {

        AuthorizationValidator authorizationValidator = new AuthorizationValidator();

        if (!authorizationValidator.isValid(command, httpServletRequest)) {
            return false;
        }

        if (command.equals(CommandDirector.SIGN_IN.getValue()) ||
                command.equals(CommandDirector.SIGN_UP.getValue()) ||
                command.equals(CommandDirector.FINISH_REGISTRATION.getValue())) {
            return true;
        }

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

            PublicKey publicKey = ProtectionUtil.stringToPublicKey(cookiePublicKey.get().getValue());

            Claims claims = ProtectionUtil.getClaimsFromCookies(cookies);


            String testToken = null;

            try {


                Optional<User> userOptional = ((UserDaoExtended)JdbcDaoFactory.getInstance().getDao(User.class)).getByLogin(claims.get("userLogin").toString());

                if (!userOptional.isPresent()) {
                    return false;
                }

                User user = userOptional.get();
                byte[] byteArray = Base64.decodeBase64(user.getPrivateKey());
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(byteArray);
                PrivateKey privateKey = KeyFactory.getInstance("RSA").generatePrivate(pkcs8EncodedKeySpec);

                Map<String, Object> claimMap = new HashMap<>();

                claimMap.put("userId", user.getId());
                claimMap.put("userLogin", user.getLogin());
                claimMap.put("userRole", user.getRoleId());

                httpServletRequest.setAttribute("role", user.getRoleId());
                testToken = Jwts.builder().setClaims(claimMap).signWith(SignatureAlgorithm.RS256, privateKey).compact();
            } catch (DaoException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (InvalidKeySpecException e) {
                return false;
            }

            return cookieToken.get().getValue().equals(testToken);
        } else {
            return false;
        }
    }

}
