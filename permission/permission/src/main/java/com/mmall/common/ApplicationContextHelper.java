package com.mmall.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

// 获取Spring上下文
@Component("applicationContextHelper")
public class ApplicationContextHelper implements ApplicationContextAware {

    // 启动时会把系统的applicationContext注入到类里面
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
