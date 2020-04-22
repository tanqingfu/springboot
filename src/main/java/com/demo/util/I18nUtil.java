package com.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

@Slf4j
public class I18nUtil {


    /**
     * 通过code 返回对应的提示信息
     * @param code
     * @return
     */
    public static String getMessage(String code) {
        return getMessage(code, null);
    }

    /**
     * 返回带参数的提示信息
     * @param code
     * @param args
     * @return
     */
    public static String getMessage(String code, Object[] args) {
        return getMessage(code, args, "");
    }


    /**
     * 根据语种查询信息
     * @param code code
     * @param args  参数
     * @param defaultMessage  默认的提示消息
     * @return
     */
    public static String getMessage(String code, Object[] args, String defaultMessage) {
        //这里使用比较方便的方法，不依赖request.
        Locale locale = LocaleContextHolder.getLocale();
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        String content;
        try{
            content = messageSource.getMessage(code, args, locale);
        }catch (Exception e){
            //log.info("获取提示消息失败： ->",e);
            content = defaultMessage;
        }
        return content;

    }


}

