//package com.example.ocdemo.utils;
//
//import java.io.File;
//import java.io.IOException;
//import java.security.KeyManagementException;
//import java.security.KeyStoreException;
//import java.security.NoSuchAlgorithmException;
//import java.security.cert.CertificateException;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpStatus;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.config.Registry;
//import org.apache.http.config.RegistryBuilder;
//import org.apache.http.conn.socket.ConnectionSocketFactory;
//import org.apache.http.conn.socket.PlainConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
//import org.apache.http.conn.ssl.SSLContextBuilder;
//import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
//import org.apache.http.entity.ContentType;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.entity.mime.MultipartEntityBuilder;
//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
//import org.apache.http.util.EntityUtils;
//
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
///**
// * @author H__D
// * @date 2016年10月19日 上午11:27:25
// */
//public class HttpClientUtil {
//
//    // utf-8字符编码
//    public static final String CHARSET_UTF_8 = "utf-8";
//
//    // HTTP内容类型。
//    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";
//
//    // HTTP内容类型。相当于form表单的形式，提交数据
//    public static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";
//
//    // HTTP内容类型。相当于form表单的形式，提交数据
//    public static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";
//
//    public static final String AUTH_HEADER_NAME = "Authorization";
//    public static final String AUTH_TOKEN = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJrdWJlcm5ldGVzL3NlcnZpY2VhY2NvdW50Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9uYW1lc3BhY2UiOiJ0ZXN0Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZWNyZXQubmFtZSI6InRlbXBsYXRlLnNhLXRva2VuLTU3N2Y2Iiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQubmFtZSI6InRlbXBsYXRlLnNhIiwia3ViZXJuZXRlcy5pby9zZXJ2aWNlYWNjb3VudC9zZXJ2aWNlLWFjY291bnQudWlkIjoiMzFiMDQ4ZGEtYmEzZC0xMWU4LWIyMjUtZmExNjNlYTAxZTJjIiwic3ViIjoic3lzdGVtOnNlcnZpY2VhY2NvdW50OnRlc3Q6dGVtcGxhdGUuc2EifQ.uav0YZRDeCfwZXeBhwX69pzVL_b3BayVidB0sdHuqWQXdUli7yzYknhZvipF4kzyWp-mAzT-Mv9fpeSuIKAVp9m16JEx5y8C7-xu1tTGQV5_gMs2PsaqdBA48gp2PawzYAs_s5QWvJp00Lad66aFI4FDCo74QYxGZHLpv9pufRKJ9ib1_nGRNZf-5mwfyzpSCJznWcgpNV19MhTYn90RI6Tmk7o_6nD2hte9y_BtIOrZZyzAgYQxd9RvonTSyHoIXj-oV89AnByPppO5zxyc7AqRsbXc-2hWOl0Htp2oukdUjiugybjuPL20NcVNQ3rqMFwuidCG0SL-R_G4iBgUjg";
//
//
//    // 连接管理器
//    private static PoolingHttpClientConnectionManager pool;
//
//    // 请求配置
//    private static RequestConfig requestConfig;
//
//    static {
//
//        try {
//            //System.out.println("初始化HttpClientTest~~~开始");
//            //SSLContextBuilder builder = new SSLContextBuilder();
//            //builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
//            //SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(builder.build());
//            SSLContext sslcontext = createIgnoreVerifySSL();
//            // 配置同时支持 HTTP 和 HTPPS
//            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create().register(
//                    "http", PlainConnectionSocketFactory.getSocketFactory()).register(
//                    "https", new SSLConnectionSocketFactory(sslcontext)).build();
//            // 初始化连接管理器
//            pool = new PoolingHttpClientConnectionManager(
//                    socketFactoryRegistry);
//            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
//            pool.setMaxTotal(200);
//            // 设置最大路由
//            pool.setDefaultMaxPerRoute(2);
//            // 根据默认超时限制初始化requestConfig
//            int socketTimeout = 10000;
//            int connectTimeout = 10000;
//            int connectionRequestTimeout = 10000;
//            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
//                    connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(
//                    connectTimeout)
//                    .build();
//
//            //System.out.println("初始化HttpClientTest~~~结束");
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        }
//
//
//        // 设置请求超时时间
//        requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
//                .setConnectionRequestTimeout(50000).build();
//    }
//
//    public static SSLContext createIgnoreVerifySSL() throws NoSuchAlgorithmException, KeyManagementException {
//        SSLContext sc = SSLContext.getInstance("TLSv1.2");
//
//        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
//        X509TrustManager trustManager = new X509TrustManager() {
//            @Override
//            public void checkClientTrusted(
//                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
//                    String paramString) throws CertificateException {
//            }
//
//            @Override
//            public void checkServerTrusted(
//                    java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
//                    String paramString) throws CertificateException {
//            }
//
//            @Override
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return null;
//            }
//        };
//
//        sc.init(null, new TrustManager[] { trustManager }, null);
//        return sc;
//    }
//
//    public static CloseableHttpClient getHttpClient() {
//
//        CloseableHttpClient httpClient = HttpClients.custom()
//                // 设置连接池管理
//                .setConnectionManager(pool)
//                // 设置请求配置
//                .setDefaultRequestConfig(requestConfig)
//                // 设置重试次数
//                .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
//                .build();
//
//        return httpClient;
//    }
//
//    /**
//     * 发送Post请求
//     *
//     * @param httpPost
//     * @return
//     */
//    private static String sendHttpPost(HttpPost httpPost) {
//
//        CloseableHttpClient httpClient = null;
//        CloseableHttpResponse response = null;
//        // 响应内容
//        String responseContent = null;
//        try {
//            // 创建默认的httpClient实例.
//            httpClient = getHttpClient();
//            // 配置请求信息
//            httpPost.setConfig(requestConfig);
//            // 执行请求
//            response = httpClient.execute(httpPost);
//            // 得到响应实例
//            HttpEntity entity = response.getEntity();
//
//            // 可以获得响应头
//            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
//            // for (Header header : headers) {
//            // System.out.println(header.getName());
//            // }
//
//            // 得到响应类型
//            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());
//
//            // 判断响应状态
//            if (response.getStatusLine().getStatusCode() >= 300) {
//                throw new Exception(
//                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
//            }
//
//            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
//                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
//                EntityUtils.consume(entity);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return responseContent;
//    }
//
//    /**
//     * 发送Get请求
//     *
//     * @param httpGet
//     * @return
//     */
//    private static String sendHttpGet(HttpGet httpGet) {
//
//        CloseableHttpClient httpClient = null;
//        CloseableHttpResponse response = null;
//        // 响应内容
//        String responseContent = null;
//        try {
//            // 创建默认的httpClient实例.
//            httpClient = getHttpClient();
//            // 配置请求信息
//            httpGet.setConfig(requestConfig);
//            httpGet.setHeader(AUTH_HEADER_NAME,AUTH_TOKEN);
//            httpGet.setHeader("Accept",CONTENT_TYPE_JSON_URL);
//            // 执行请求
//            response = httpClient.execute(httpGet);
//            // 得到响应实例
//            HttpEntity entity = response.getEntity();
//
//            // 可以获得响应头
//            // Header[] headers = response.getHeaders(HttpHeaders.CONTENT_TYPE);
//            // for (Header header : headers) {
//            // System.out.println(header.getName());
//            // }
//
//            // 得到响应类型
//            // System.out.println(ContentType.getOrDefault(response.getEntity()).getMimeType());
//
//            // 判断响应状态
//            if (response.getStatusLine().getStatusCode() >= 300) {
//                throw new Exception(
//                        "HTTP Request is not success, Response code is " + response.getStatusLine().getStatusCode());
//            }
//
//            if (HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
//                responseContent = EntityUtils.toString(entity, CHARSET_UTF_8);
//                EntityUtils.consume(entity);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                // 释放资源
//                if (response != null) {
//                    response.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return responseContent;
//    }
//
//
//    /**
//     * 发送 post请求
//     *
//     * @param httpUrl 地址
//     */
//    public static String sendHttpPost(String httpUrl) {
//        // 创建httpPost
//        HttpPost httpPost = new HttpPost(httpUrl);
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 发送 get请求
//     *
//     * @param httpUrl
//     */
//    public static String sendHttpGet(String httpUrl) {
//        // 创建get请求
//        HttpGet httpGet = new HttpGet(httpUrl);
//        return sendHttpGet(httpGet);
//    }
//
//
//    /**
//     * 发送 post请求（带文件）
//     *
//     * @param httpUrl   地址
//     * @param maps      参数
//     * @param fileLists 附件
//     */
//    public static String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
//        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
//        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
//        if (maps != null) {
//            for (String key : maps.keySet()) {
//                meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
//            }
//        }
//        if (fileLists != null) {
//            for (File file : fileLists) {
//                FileBody fileBody = new FileBody(file);
//                meBuilder.addPart("files", fileBody);
//            }
//        }
//        HttpEntity reqEntity = meBuilder.build();
//        httpPost.setEntity(reqEntity);
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 发送 post请求
//     *
//     * @param httpUrl 地址
//     * @param params  参数(格式:key1=value1&key2=value2)
//     */
//    public static String sendHttpPost(String httpUrl, String params) {
//        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
//        try {
//            // 设置参数
//            if (params != null && params.trim().length() > 0) {
//                StringEntity stringEntity = new StringEntity(params, "UTF-8");
//                stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
//                httpPost.setEntity(stringEntity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 发送 post请求
//     *
//     * @param maps 参数
//     */
//    public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
//        String parem = convertStringParamter(maps);
//        return sendHttpPost(httpUrl, parem);
//    }
//
//
//    /**
//     * 发送 post请求 发送json数据
//     *
//     * @param httpUrl    地址
//     * @param paramsJson 参数(格式 json)
//     */
//    public static String sendHttpPostJson(String httpUrl, String paramsJson) {
//        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
//        try {
//            // 设置参数
//            if (paramsJson != null && paramsJson.trim().length() > 0) {
//                StringEntity stringEntity = new StringEntity(paramsJson, "UTF-8");
//                stringEntity.setContentType(CONTENT_TYPE_JSON_URL);
//                httpPost.setEntity(stringEntity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sendHttpPost(httpPost);
//    }
//
//    /**
//     * 发送 post请求 发送xml数据
//     *
//     * @param httpUrl   地址
//     * @param paramsXml 参数(格式 Xml)
//     */
//    public static String sendHttpPostXml(String httpUrl, String paramsXml) {
//        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
//        try {
//            // 设置参数
//            if (paramsXml != null && paramsXml.trim().length() > 0) {
//                StringEntity stringEntity = new StringEntity(paramsXml, "UTF-8");
//                stringEntity.setContentType(CONTENT_TYPE_TEXT_HTML);
//                httpPost.setEntity(stringEntity);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return sendHttpPost(httpPost);
//    }
//
//
//    /**
//     * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
//     *
//     * @param parameterMap 需要转化的键值对集合
//     * @return 字符串
//     */
//    public static String convertStringParamter(Map parameterMap) {
//        StringBuffer parameterBuffer = new StringBuffer();
//        if (parameterMap != null) {
//            Iterator iterator = parameterMap.keySet().iterator();
//            String key = null;
//            String value = null;
//            while (iterator.hasNext()) {
//                key = (String) iterator.next();
//                if (parameterMap.get(key) != null) {
//                    value = (String) parameterMap.get(key);
//                } else {
//                    value = "";
//                }
//                parameterBuffer.append(key).append("=").append(value);
//                if (iterator.hasNext()) {
//                    parameterBuffer.append("&");
//                }
//            }
//        }
//        return parameterBuffer.toString();
//    }
//
//
//}
