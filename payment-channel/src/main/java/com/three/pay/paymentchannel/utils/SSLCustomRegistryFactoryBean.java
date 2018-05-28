package com.three.pay.paymentchannel.utils;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Author:luiz
 * @Date: 2018/5/28 18:08
 * @Descripton:
 * @Modify :
 **/
@Component
public class SSLCustomRegistryFactoryBean implements FactoryBean<Registry<ConnectionSocketFactory>> {

    private static final Logger logger = LoggerFactory.getLogger(SSLCustomRegistryFactoryBean.class);

    @Override
    public Registry<ConnectionSocketFactory> getObject() throws Exception {
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        SSLContext ctx = null;
        try {
            SSLContext.getDefault();
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[] { tm }, null);
        } catch (Exception e) {
            logger.error("SSLCustomRegistryFactoryBean create error:",e);
        }
        HttpClientBuilder builder = HttpClientBuilder.create();
        SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(ctx,
                NoopHostnameVerifier.INSTANCE);
        builder.setSSLSocketFactory(sslConnectionFactory);

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sslConnectionFactory).build();
        return registry;
    }

    @Override
    public Class<Registry<ConnectionSocketFactory>> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
