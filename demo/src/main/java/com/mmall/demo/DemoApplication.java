package com.mmall.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication // 由于大量项目都会在主要的配置类上添加@AuthConfiguration, @EnableAutoConfiguration和@CompnentScan，所以用这一个替代这三个注解
@RestController // 相当于同时使用了Spring MVC里的@Controller和@ResponseBody注解
// @EnableAutoConfiguration // 这个注解自动加在了SpringBootApplication里，作用是SprinBoot会自动根据jar包的依赖来配置项目
@EnableGlobalMethodSecurity(prePostEnabled = true) // 使下面@PreAuthorize生效
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping("/")
	public String home() {
		return "hello spring boot";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello world";
	}

	// 此方法需要角色认证
	@PreAuthorize("hasRole('ROLE_ADMIN')") // 当方法需要role验证时，注意一定要加ROLE_,这是RoleVoter.class的内置属性，加上才能知道是做角色校验
	@RequestMapping("/roleAuth")
	public String role() {
		return "admin auth";
	}

}
