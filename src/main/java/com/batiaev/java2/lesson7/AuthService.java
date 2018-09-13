package com.batiaev.java2.lesson7;

public interface AuthService {
    String authByLoginAndPassword(String login, String password);

    String regNewUser(String login, String password, String nick);
}
