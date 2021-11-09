package com.mmall.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

	// SpringSecurity中定义了四个支持使用表达式的注解
	// 此方法需要角色认证
	@PreAuthorize("hasRole('ROLE_ADMIN')") // 当方法需要role验证时，注意一定要加ROLE_,这是RoleVoter.class的内置属性，加上才能知道是做角色校验
	@PostAuthorize("hasRole('')") // 在方法调用后进行权限检查
	@PreFilter("") // 对集合类的参数或者返回值进行过滤
	@PostFilter("") //
	@RequestMapping("/roleAuth")
	public String role() {
		return "admin auth";
	}

	// username必须与当前登陆的用户相同
	@PreAuthorize("#id<10 and principal.username.equals(#username) and #user.username.equals('abc')") // 让参数id<10
	@PostAuthorize("returnObject%2==0") // 在方法调用后进行权限检查,比如方法返回必须是一个偶数
	@PreFilter("") // 对集合类的参数进行过滤
	@PostFilter("") // 对集合类的返回值进行过滤
	@RequestMapping("/roleAuth")
	public Integer test(Integer id, String username, User user) {
		return id;
	}

	// filterObject就是list里的对象
	@PreFilter("filterObject%2==0") // 对集合类的参数进行过滤，比如只留偶数
	@PostFilter("filterObject%4==0") // 对集合类的返回值进行过滤, 返回值必须被4整除
	@RequestMapping("/test2")
	public List<Integer> test2(List<Integer> idList) {
		return idList;
	}

}
