package com.school.netlearning.Util;

import com.aliyun.oss.ClientConfiguration;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.PutObjectResult;
import net.sf.json.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class AliOSSTool {

    @Value("${alioss.endpoint}")
    private String endpoint;

    @Value("${alioss.accessKeyId}")
    private String accessKeyId;

    @Value("${alioss.accessKeySecret}")
    private String accessKeySecret;

    @Value("${alioss.bucketName}")
    private String bucketName;

    @Value("${alioss.expiration}")
    private Integer expiration;

    @Value("${alioss.expireTime}")
    private Integer expireTime;

    @Value("${alioss.callbackurl}")
    private String callbackurl;

    @Value("${alioss.fileSize}")
    private Long fileSize;

    private Integer uploadType = 1;

    /*@Value("${file.address}")
    private String fileAddress;*/

    private OSSClient ossClient = null;

    /**
     * 获取OSS连接
     *
     * @return
     */
    public OSSClient getOSSClient() {
        // 创建OSSClient实例。
        if (null == ossClient) {
            /*// 创建ClientConfiguration实例（ClientConfiguration是OSSClient的配置类，可配置代理、连接超时、最大连接数等参数）。
            ClientConfiguration clientConfiguration = new ClientConfiguration();
            // 设置OSSClient允许打开的最大HTTP连接数，默认1024。
            clientConfiguration.setMaxConnections(200);
            // 设置Socket层传输数据的超时时间（单位：毫秒），默认为50000毫秒。
            clientConfiguration.setSocketTimeout(10000);
            // 设置建立连接的超时时间（单位：毫秒），默认为50000毫秒。
            clientConfiguration.setConnectionTimeout(10000);
            // 设置从连接池中获取连接的超时时间（单位：毫秒）,默认不超时。
            clientConfiguration.setConnectionRequestTimeout(1000);
            // 如果空闲时间超过此参数的设定值，则关闭连接（单位：毫秒），默认为60000毫秒 。
            clientConfiguration.setIdleConnectionTime(10000);
            // 设置失败请求重试次数，默认3次。
            clientConfiguration.setMaxErrorRetry(5);
            // 设置是否支持CNAME作为Endpoint，默认支持。
            clientConfiguration.setSupportCname(true);
            // 设置是否开启二级域名（Second Level Domain）的访问方式，默认不开启。
            clientConfiguration.setSLDEnabled(true);
            // 设置连接OSS所采用的协议（HTTP/HTTPS），默认为HTTP。
            clientConfiguration.setProtocol(Protocol.HTTP);
            // 设置用户代理，指HTTP的User-Agent头，默认为“aliyun-sdk-java”。
            clientConfiguration.setUserAgent("aliyun-sdk-java");
            // 设置代理服务器端口，默认值无。
            clientConfiguration.setProxyHost("<yourProxyHost>");
            // 设置代理服务器验证的用户名，默认值无。
            clientConfiguration.setProxyUsername("<yourProxyUserName>");
            // 设置代理服务器验证的密码，默认值无。
            clientConfiguration.setProxyPassword("<yourProxyPassword>");*/
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret, new ClientConfiguration());
        }
        // 关闭ossClient
        //ossClient.shutdown();
        return ossClient;
    }

    /**
     * 上传文件
     *
     * @param fileName 文件名称
     * @param file     文件
     */
    public void putObject(String fileName, byte[] file) {
        putObject(fileName, file, getOSSClient());
    }

    public void putObject(String fileName, byte[] file, OSSClient ossClient) {
        PutObjectResult result = ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(file));
//        ossClient.shutdown();
    }

    /**
     * 获取签名url
     *
     * @param fileName 文件名
     * @return
     */
    public Object generatePresignedUrl(String fileName) {
        return generatePresignedUrl(fileName, getOSSClient());
    }

    public Object generatePresignedUrl(String fileName, OSSClient ossClient) {
        /*if (1 == uploadType) {
            //本地文件不用签名
            return fileAddress + fileName;
        }*/
        URL url = ossClient.generatePresignedUrl(bucketName, fileName, new Date(System.currentTimeMillis() + expiration), HttpMethod.GET);
        return url;
    }

    /**
     * 删除单个文件
     *
     * @param fileName
     * @return
     */
    public void deleteObject(String fileName) {
        getOSSClient().deleteObject(bucketName, fileName);
    }

    /**
     * 获取STS临时访问权限
     *
     * @param dir 临时访问的路径前缀
     * @return
     */
    /*public Object getSTS(String dir) throws Exception {
        String host = "http://" + bucketName + "." + endpoint;
        OSSClient client = getOSSClient();
//        long expireTime = 3000;
        long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
        Date expiration = new Date(expireEndTime);
        PolicyConditions policyConds = new PolicyConditions();
        //设置上传文件的大小限制
        policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, fileSize);
        policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

        String postPolicy = client.generatePostPolicy(expiration, policyConds);
        byte[] binaryData = postPolicy.getBytes("utf-8");
        String encodedPolicy = BinaryUtil.toBase64String(binaryData);
        String postSignature = client.calculatePostSignature(postPolicy);

        Map<String, String> respMap = new LinkedHashMap<String, String>();
        respMap.put("accessid", accessKeyId);
        respMap.put("policy", encodedPolicy);
        respMap.put("signature", postSignature);
        //respMap.put("expire", formatISO8601Date(expiration));
        respMap.put("dir", dir);
        respMap.put("host", host);
        respMap.put("expire", String.valueOf(expireEndTime / 1000));

        //上传回调
        String callback = "{\"callbackUrl\":\"" + callbackurl + "\",\"callbackBody\":\"filename=${object}&size=${size}&mimeType=${mimeType}&userName=" + "aliName" + "\",\"callbackBodyType\":\"application/x-www-form-urlencoded\"}";
        byte[] bytes = Base64.encodeBase64(callback.getBytes("utf-8"));
        respMap.put("callback", new String(bytes));
        //本字段区别上传文件的方式；0：aliOSS上传，1：上传文件到本地
        respMap.put("uploadType", String.valueOf(uploadType));

        JSONObject ja1 = JSONObject.fromObject(respMap);
        return ja1.toString();
    }*/
}
