package com.example.demo.untils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.Resource;

public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public SpringUtils() {
    }

    public  void setApplicationContext(ApplicationContext applicationContext) {
        SpringUtils.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Resource getResource(String location) {
        return applicationContext == null?null:applicationContext.getResource(location);
    }



    public static <T> T getBean(Class<T> clz) throws BeansException {
        return applicationContext == null?null:applicationContext.getBean(clz);
    }

    public static <T> T getBean(String name, Class<T> clz) {
        return applicationContext == null?null:applicationContext.getBean(name, clz);
    }

    public static boolean containsBean(String name) {
        return applicationContext != null && applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext != null && applicationContext.isSingleton(name);
    }

    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext == null?null:applicationContext.getType(name);
    }

    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return applicationContext == null?null:applicationContext.getAliases(name);
    }
}
