package com.physio.authservice.service;

import com.physio.authservice.model.User;

public interface UserService {
    User registerUser(String username, String password);

    User getByUsername(String username);
}