package com.leading.demo1.security;

import com.leading.demo1.token.AccessToken;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AccessTokenTest {
    @Test
    public void testAccessToken() {
        AccessToken token = new AccessToken();

        token.setToken("Token")
            .setAccessTimeStampUTC(1541033789574L);
        assertEquals("Token", token.getToken());
        assertEquals(1541033789574L, token.getAccessTimeStampUTC());
    }

    @Test
    public void testEquals() {
        AccessToken t1 = new AccessToken();
        AccessToken t2 = new AccessToken();
        t1.setToken("token").setAccessTimeStampUTC(System.currentTimeMillis());
        t2.setToken("token").setAccessTimeStampUTC(System.currentTimeMillis());
        assertEquals(t1, t2);
    }
}
