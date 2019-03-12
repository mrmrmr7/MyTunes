package com.mrmrmr7.mytunes.service.impl;

import com.mrmrmr7.mytunes.dao.UserDaoExtended;
import com.mrmrmr7.mytunes.dao.exception.DaoException;
import com.mrmrmr7.mytunes.dao.impl.JdbcDaoFactory;
import com.mrmrmr7.mytunes.entity.User;
import com.mrmrmr7.mytunes.service.ServiceException;
import com.mrmrmr7.mytunes.service.SignUpService;
import com.mrmrmr7.mytunes.util.KeyPairUtil;
import org.mindrot.jbcrypt.BCrypt;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import java.util.*;

public class SignUpServiceImpl implements SignUpService {
    public final static String USERNAME = "mytunesmaler@gmail.com";
    public final static String PASSWORD = "CStheBEST7";

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

    private User buildUser(HttpServletRequest request) {
        String login = request.getParameter("login");
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String password = request.getParameter("password");

        if (login == null || email == null || firstName == null || secondName == null || password == null) {
            return null;
        }

        String passwordBCrypted = BCrypt.hashpw(password, BCrypt.gensalt());

        User user = new User(login, password, firstName, secondName, email);

        return user;
    }

    @Override
    public boolean sendSignUpMessage(HttpServletRequest request) throws ServiceException {
        Properties mailProperties = getProperties();

        User user = buildUser(request);

        if (user == null) {
            return false;
        }

        KeyPairUtil.setKeysToUser(user);

        try {
            JdbcDaoFactory
                    .getInstance()
                    .getDao(User.class)
                    .insert(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        Session session = Session.getInstance(mailProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(USERNAME, PASSWORD);
                    }
                });

        String mailTo = "aliex.s@yandex.by";
        Message message = new MimeMessage(session);
        String confirmPath = user.getPublicKey().substring(228, 300);
        confirmPath = BCrypt.hashpw(confirmPath,BCrypt.gensalt());
        try {
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(mailTo));
            message.setSubject("Testing Subject");
            message.setText("Dear " + user.getFirstName() + " " + user.getSecondName() + " ," +
                    "\n\n" +
                    "If you didn't register on site MyTunes, ignore this message! " +
                    "\n\n" +
                    "Please, confirm your registration by this link: " +
                    "\n\n" +
                    "http://localhost:8080/crud?command=finishRegistration&login=" + user.getLogin() + "&confirm=" + confirmPath.substring(7));
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("Done");
        return true;
    }

    @Override
    public boolean finishSignUp(HttpServletRequest request) throws ServiceException {
        String userLogin = request.getParameter("login");
        String confirmString = "$2a$10$" + request.getParameter("confirm");

        Optional<User> userOptional;

        try {
            userOptional = ((UserDaoExtended)JdbcDaoFactory.getInstance().getDao(User.class)).getByLogin(userLogin);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        User user = userOptional.get();
        String userConfirmString = userOptional.get().getPublicKey().substring(228,300);
        if (BCrypt.checkpw(userConfirmString, confirmString)) {
            user.setStatusId((byte)1);
        } else {
            return false;
        }

        try {
            JdbcDaoFactory.getInstance().getDao(User.class).update(user);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage());
        }

        return true;
    }
}
