package com.lx.auth.api;

import com.lx.auth.entity.Token;
import com.lx.auth.entity.User;

public interface AuthService {
    Token loginWithUsernameAndPassword(String username, String password);

    User getUserById(Long userId);

    void logout(String token);

    User getUserByToken(String token);

    User createUser(String openId, String username, String password);

    Token loginWithOpenId(String openId);

    Boolean checkUserPassword(Long id, String password);

    void setUserPassword(Long id, String password);
}
