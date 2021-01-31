package com.lx.auth.impl;

import com.lx.auth.api.AuthService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthServiceImplTests {

    @Test
    public void TestGetSHA256Hashed() {
        String source = "123456";
        System.out.println(source);
        String hashed = new AuthServiceImpl().getSHA256Hashed(source);
        System.out.println(hashed);
    }
}
