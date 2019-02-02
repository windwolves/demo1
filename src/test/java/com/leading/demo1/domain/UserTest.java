package com.leading.demo1.domain;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void testUser() {
        User user = new User();
        user.setId(UUID.fromString("763be857-c859-4e34-a44f-a44110d2e799")).setName("leading");
        assertEquals("leading", user.getName());
        assertEquals("leading", user.toString());
        assertEquals(UUID.fromString("763be857-c859-4e34-a44f-a44110d2e799"), user.getId());
    }
}
