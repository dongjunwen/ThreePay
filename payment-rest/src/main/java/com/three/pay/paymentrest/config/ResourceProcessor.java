package com.three.pay.paymentrest.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @Author:luiz
 * @Date: 2018/1/30 13:51
 * @Descripton:
 * @Modify :
 **/
@Component
public class ResourceProcessor implements EnvironmentPostProcessor {
    private static final Logger logger= LoggerFactory.getLogger(ResourceProcessor.class);
    private static final String LOCATION = "/home/soft/payment/config/rest/";
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment configurableEnvironment, SpringApplication springApplication) {
        File filePath = new File(LOCATION);
        if (filePath.exists()) {
            File[] files=filePath.listFiles();
            MutablePropertySources propertySources = configurableEnvironment.getPropertySources();
            int i=0;
            for(File file:files){
                i++;
                Properties properties = loadProperties(file);
                System.out.println("[Config]资源加载:"+properties.toString());
                propertySources.addLast(new PropertiesPropertySource("res"+i, properties));
            }
            System.out.println("[Config]资源size:"+propertySources.size());
        }
    }
    private Properties loadProperties(File f) {
        FileSystemResource resource = new FileSystemResource(f);
        try {

            return PropertiesLoaderUtils.loadProperties(resource);
        }
        catch (IOException ex) {
            logger.error("[Config]资源加载发生异常:{}",ex);
            throw new IllegalStateException("Failed to load local settings from " + f.getAbsolutePath(), ex);
        }
    }
}
