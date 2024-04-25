package com.lani.util;

import org.springframework.context.ApplicationContext;

import com.lani.config.ApplicationContextProvider;

import lombok.experimental.UtilityClass;

@UtilityClass
public class BeanUtils {
    public static Object getBean(Class<?> classType) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getBean(classType);
    }

    public static String getProperty(String key) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();
        return applicationContext.getEnvironment().getProperty(key);
    }

}
