package com.school.netlearning.controller;

import com.school.netlearning.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传文件到阿里OSS
     *
     * @param multipartFile 文件
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/upload")
    public Object uploadFile(@RequestParam("upload") MultipartFile multipartFile) throws Exception {
        return fileService.uploadFile(multipartFile);
    }
}
