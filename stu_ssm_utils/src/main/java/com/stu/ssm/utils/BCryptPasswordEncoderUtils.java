package com.stu.ssm.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptPasswordEncoderUtils {

    private static BCryptPasswordEncoder  BCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        String encode = BCryptPasswordEncoder.encode("123");
        System.out.println(encode);
    }
}
