package com.droidwars.web;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.testng.annotations.Test;

import java.util.Random;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

@Slf4j
@Test(enabled = false)
public class BCryptFamiliarity {

    private static final String SIMPLE_PASSWORD = "PASSWORD";

    public void simpleTest() {

        String gensalt = BCrypt.gensalt();
        String hashpw = BCrypt.hashpw(SIMPLE_PASSWORD, gensalt);
        log.debug("salt = {}, size = {}", gensalt, gensalt.length());
        log.debug("saltedpwd = {}, size = {}", hashpw, hashpw.length());

        assertTrue(BCrypt.checkpw(SIMPLE_PASSWORD, hashpw));
        assertFalse(BCrypt.checkpw(SIMPLE_PASSWORD.toLowerCase(), hashpw));

    }

    public void longPasswordTest() {

        int maxPasswordLength = 0;

        String gensalt = BCrypt.gensalt();
        log.debug("salt = {}, size = {}", gensalt, gensalt.length());
        StringBuilder password = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            String oldPassword = password.toString();
            password.append((char) (random.nextInt(42) + 48));

            if (password.length() > 40) {
                log.debug("password = {}, size = {}", password, password.length());
                String hashpw = BCrypt.hashpw(password.toString(), gensalt);
                log.debug("saltedpwd = {}, size = {}", hashpw, hashpw.length());
                assertTrue(BCrypt.checkpw(password.toString(), hashpw));
                if (BCrypt.checkpw(oldPassword, hashpw)) {
                    maxPasswordLength = oldPassword.length();
                    log.debug("max password length = {}", maxPasswordLength);
                    break;
                }
            }
        }

        assertThat(maxPasswordLength, Matchers.greaterThan(40));

    }

    public void emptyPasswordTest() {

        String gensalt = BCrypt.gensalt();
        String password = String.valueOf(new char[] {0, 1});
        String hashpw = BCrypt.hashpw(password, gensalt);
        log.debug("salt = {}, size = {}", gensalt, gensalt.length());
        log.debug("saltedpwd = {}, size = {}", hashpw, hashpw.length());

        assertTrue(BCrypt.checkpw(password, hashpw));

        String password2 = String.valueOf(new char[] {0});
        String hashpw2 = BCrypt.hashpw(password2, gensalt);
        log.debug("salt = {}, size = {}", gensalt, gensalt.length());
        log.debug("saltedpwd = {}, size = {}", hashpw2, hashpw2.length());

        assertTrue(BCrypt.checkpw(password2, hashpw2));

        String password3 = "";
        String hashpw3 = BCrypt.hashpw(password3, gensalt);
        log.debug("salt = {}, size = {}", gensalt, gensalt.length());
        log.debug("saltedpwd = {}, size = {}", hashpw3, hashpw3.length());

        assertTrue(BCrypt.checkpw(password3, hashpw2));
        assertTrue(BCrypt.checkpw(password2, hashpw3));

    }

}
