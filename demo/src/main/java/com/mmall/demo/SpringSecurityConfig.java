package com.mmall.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    // Spring Security提供了一套基于内存的验证
    // 告诉系统内存里有一个用户


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("admin").password(new BCryptPasswordEncoder().encode("123456")).roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("chen").password(new BCryptPasswordEncoder().encode("chen")).roles("ADMIN");
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("demo").password(new BCryptPasswordEncoder().encode("demo")).roles("USER");
    }

    // 定义规则: 决定哪些请求会被拦截以及一些请求如何被处理
    @Override
    protected void configure(HttpSecurity http) throws  Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()  // 项目主路径被放行
                .anyRequest().authenticated() // 其他请求需要经过验证
                .and()
                .logout().permitAll() // 注销可以被随意访问
                .and()
                .formLogin().permitAll(); // 允许表单登陆
        http.csrf().disable();
    }

    // 忽略web静态资源，js,css,images无需进行权限拦截
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**");
    }




}
