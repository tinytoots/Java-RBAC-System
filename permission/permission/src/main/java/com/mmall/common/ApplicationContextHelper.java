package com.mmall.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// 获取Spring上下文
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    // 启动时会把系统的applicationContext注入到类里面
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        // 让全局的applicationContext等于当前的context, 这样就拿到了一个static的applicationContext
        // 当需要的时候，直接使用ApplicationContextHelper里的applicationContext就可以了。
        applicationContext = context;
    }

    // 从applicationContext里取上下文的Spring的bean
    public static <T> T popBean(Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    public static <T> T popBean(String name, Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(name, clazz);
    }
}
