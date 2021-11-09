package com.mmall.demo;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


// 密码自定义验证
// 通过数据库取用户信息时，数据库里存储的值可能是加密后的值，用户的值很难与其匹配，所以需要密码自定义验证
public class MyPasswordEncoder extends BCryptPasswordEncoder {

//    // 对原始密码进行加密
//    @Override
//    public String encode(CharSequence charSequence) {
//        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
//    }
//
//
//    // 拿原始密码与加密后的密码进行匹配
//    @Override
//    public boolean matches(CharSequence charSequence, String s) {
//        return false;
//    }
    
}
