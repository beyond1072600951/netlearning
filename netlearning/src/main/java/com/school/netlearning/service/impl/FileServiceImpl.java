package com.school.netlearning.service.impl;

import com.school.netlearning.Util.AliOSSTool;
import com.school.netlearning.service.FileService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Autowired
    private AliOSSTool aliOSSTool;

    @Override
    public Object uploadFile(MultipartFile multipartFile) throws Exception {
        String fileName = "netlearing" + "/" + UUID.randomUUID().toString() + "/" + System.currentTimeMillis() + "."
                + StringUtils.substringAfterLast(multipartFile.getOriginalFilename(), ".");
        aliOSSTool.putObject(fileName, multipartFile.getBytes());
        //获取OSS完整路径
        System.out.println(this.getUrl(fileName));
        return fileName;
    }

    @Override
    public Object getUrl(String fileName) throws Exception {
        return aliOSSTool.generatePresignedUrl(fileName);
    }

    @Override
    public Object callback(String fileName) throws Exception {
        logger.info("客户端直传文件后回调：" + fileName);
        return fileName;
    }
}
