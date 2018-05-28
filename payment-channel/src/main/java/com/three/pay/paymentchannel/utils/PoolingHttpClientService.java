package com.three.pay.paymentchannel.utils;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Author:luiz
 * @Date: 2018/5/28 18:08
 * @Descripton:
 * @Modify :
 **/
@Component
public class PoolingHttpClientService {

    private static final Logger logger = LoggerFactory.getLogger(PoolingHttpClientService.class);
    @Value("${httpConfig.connectionRequestTimeout}")
    private int connectionRequestTimeout;
    @Value("${httpConfig.connectTimeout}")
    private int connectTimeout;
    @Value("${httpConfig.connectTimeout}")
    private int socketTimeout;

    private PoolingHttpClientConnectionManager httpClientManager;


    public String postXml(String url,String xml,String charset){
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost post = createHttpPost(url);
        try {
            post.addHeader(new BasicHeader("Content-Type","text/xml;charset=UTF-8"));
            post.setEntity(new StringEntity(xml, charset ));
            CloseableHttpResponse resp = httpClient.execute(post);
            try {
                HttpEntity entity = resp.getEntity();
                String response = EntityUtils.toString(entity, charset);
                return response;
            }finally {
                resp.close();
            }
        } catch (Exception e) {
            logger.error("http post xml error:"+e.getMessage(),e);
            logger.error("http connection pool state:"+ JSON.toJSONString(httpClientManager.getTotalStats()));
            throw new RuntimeException("http post xml error:"+e.getMessage());
        }
    }

    public String doPost(String url,Map<String,String> params,String charset){
        CloseableHttpClient httpClient = getHttpClient();
        return doPost(httpClient,url,params,charset);
    }

    public String doPost(CloseableHttpClient httpClient,String url,Map<String,String> params,String charset){
        try {
            List<NameValuePair> getParams = createNameValueParams(params);

            HttpPost post = createHttpPost(url);
            post.setEntity(new UrlEncodedFormEntity(getParams, charset));
            CloseableHttpResponse resp = httpClient.execute(post);
            try {
                HttpEntity entity = resp.getEntity();
                String response = EntityUtils.toString(entity, charset);
                return response;
            }finally {
                resp.close();
            }
        } catch (Exception e) {
            logger.error("http get error:"+e.getMessage(),e);
            logger.error("http connection pool state:"+ JSON.toJSONString(httpClientManager.getTotalStats()));
            throw new RuntimeException("http get error:"+e.getMessage());
        }
    }
    private List<NameValuePair> createNameValueParams(Map<String,String> params){
        List<NameValuePair> rs = new ArrayList<NameValuePair>();
        Set<String> keys = params.keySet();
        for(String key : keys){
            rs.add(new BasicNameValuePair(key, params.get(key)));
        }
        return rs;
    }
    
    
    public String postData(String url,Map<String,String> headParams,String data,String charset){
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost post = createHttpPost(url);
        try {
            setHeaders(headParams,post);
            post.setEntity(new StringEntity(data, charset));
            CloseableHttpResponse resp = httpClient.execute(post);
            try {
                HttpEntity entity = resp.getEntity();
                return EntityUtils.toString(entity, charset);
            }finally {
                resp.close();
            }
        } catch (Exception e) {
            logger.error("http post data error:"+e.getMessage(),e);
            logger.error("http connection pool state:"+ JSON.toJSONString(httpClientManager.getTotalStats()));
            throw new RuntimeException("http post data error:"+e.getMessage());
        }
    }

    public String postData(String url,Map<String,String> headParams,Map<String,String> data,String charset){
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost post = createHttpPost(url);
        try {
            setHeaders(headParams,post);
            List<NameValuePair> formParams = new LinkedList<>();
            if(data != null && data.size()>0){
                Set<String> keys = data.keySet();
                for(String key : keys){
                    formParams.add(new BasicNameValuePair(key, data.get(key)));
                }
            }
            post.setEntity(new UrlEncodedFormEntity(formParams, charset));
            CloseableHttpResponse resp = httpClient.execute(post);
            try {
                HttpEntity entity = resp.getEntity();
                return EntityUtils.toString(entity, charset);
            }finally {
                resp.close();
            }
        } catch (Exception e) {
            logger.error("http post data error:"+e.getMessage(),e);
            logger.error("http connection pool state:"+ JSON.toJSONString(httpClientManager.getTotalStats()));
            throw new RuntimeException("http post data error:"+e.getMessage());
        }
    }

    public Map<String,String> postDataWithResultHeaders(String url,Map<String,String> headParams,Map<String,String> data,Set<String> resultHeaders,String charset){
        CloseableHttpClient httpClient = getHttpClient();
        HttpPost post = createHttpPost(url);
        try {
            setHeaders(headParams,post);
            List<NameValuePair> formParams = new LinkedList<>();
            if(data != null && data.size()>0){
                Set<String> keys = data.keySet();
                for(String key : keys){
                    formParams.add(new BasicNameValuePair(key, data.get(key)));
                }
            }
            post.setEntity(new UrlEncodedFormEntity(formParams, charset));
            CloseableHttpResponse resp = httpClient.execute(post);
            try {
                Map<String,String> result = new HashMap<String,String>();
                if(resultHeaders != null && resultHeaders.size()>0){
                    for(String key : resultHeaders){
                        Header respHeader = resp.getFirstHeader(key);
                        result.put(key,respHeader  != null ? respHeader.getValue() :"");
                    }
                }
                HttpEntity entity = resp.getEntity();
                result.put("resultData",EntityUtils.toString(entity, charset));
                return result;
            }finally {
                resp.close();
            }
        } catch (Exception e) {
            logger.error("http post data error:"+e.getMessage(),e);
            logger.error("http connection pool state:"+ JSON.toJSONString(httpClientManager.getTotalStats()));
            throw new RuntimeException("http post data error:"+e.getMessage());
        }
    }

    public String postData(String url,Map<String,String> data,String charset){
    	CloseableHttpClient httpClient = getHttpClient();
    	HttpPost post = createHttpPost(url);
    	try {
    		List<NameValuePair> formParams = new LinkedList<>();
    		if(data != null && data.size()>0){
                Set<String> keys = data.keySet();
                for(String key : keys){
                    formParams.add(new BasicNameValuePair(key, data.get(key)));
                }
            }
    		post.setEntity(new UrlEncodedFormEntity(formParams, charset));
    		CloseableHttpResponse resp = httpClient.execute(post);
    		try {
    			HttpEntity entity = resp.getEntity();
    			return EntityUtils.toString(entity, charset);
    		}finally {
    			resp.close();
    		}
    	} catch (Exception e) {
    		logger.error("http post data error:"+e.getMessage(),e);
    		logger.error("http connection pool state:"+ JSON.toJSONString(httpClientManager.getTotalStats()));
    		throw new RuntimeException("http post data error:"+e.getMessage());
    	}
    }
    
	public HttpEntity postDataStr(String url, Map<String, String> data, String charset) {
		CloseableHttpClient httpClient = getHttpClient();
		HttpPost post = createHttpPost(url);
		try {
			List<NameValuePair> formParams = new LinkedList<>();
			if (data != null && data.size() > 0) {
				Set<String> keys = data.keySet();
				for (String key : keys) {
					formParams.add(new BasicNameValuePair(key, data.get(key)));
				}
			}
			post.setEntity(new UrlEncodedFormEntity(formParams, charset));
			CloseableHttpResponse resp = httpClient.execute(post);
			System.out.println("The response value of token:" + resp.getFirstHeader("token"));
			HttpEntity entity = resp.getEntity();
			return entity;
		} catch (Exception e) {
			logger.error("http post data error:" + e.getMessage(), e);
			logger.error("http connection pool state:"
					+ JSON.toJSONString(httpClientManager.getTotalStats()));
			throw new RuntimeException("http post data error:" + e.getMessage());
		}finally{
			
		}
	}

    private void setHeaders(Map<String,String> headParams, HttpPost post){
        if(headParams != null && headParams.size()>0){
            Set<String> keys = headParams.keySet();
            for(String key : keys){
                post.addHeader(key,headParams.get(key));
            }
        }
    }

    public HttpPost createHttpPost(String url){
        HttpPost post = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(connectionRequestTimeout)
                .setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
        post.setConfig(requestConfig);
        return post;
    }

    public CloseableHttpClient getHttpClient(){
    	//ConnectionConfig connectionConfig = ConnectionConfig.custom().setBufferSize(4128).build();
        CloseableHttpClient httpClient = HttpClients.custom()//.setDefaultConnectionConfig(connectionConfig)
        		.setConnectionManager(httpClientManager).build();
        return httpClient;
    }




}
