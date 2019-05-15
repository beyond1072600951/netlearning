package com.school.netlearning.service;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.HashMap;

public interface FileService {

    /**
     * 上传文件到阿里OSS
     *
     * @param multipartFile 文件
     * @return
     * @throws Exception
     */
    public Object uploadFile(MultipartFile multipartFile) throws Exception;

    /**
     * 根据文件名生成签名url
     *
     * @param fileName 文件名
     * @return
     * @throws Exception
     */
    public Object getUrl(String fileName) throws Exception;

    /**
     * 前端直传文件后回调接口
     *
     * @return
     * @throws Exception
     */
    public Object callback(String fileName) throws Exception;
}
