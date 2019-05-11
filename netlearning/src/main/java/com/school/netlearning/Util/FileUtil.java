package com.school.netlearning.Util;

//import com.mhqc.mhqcrecordapi.exception.MyException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.multipart.MultipartFile;
//
//import javax.servlet.http.HttpServletResponse;
//import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.OutputStream;
//
//@Component
//public class FileUtil {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);
//
//    @Value("${file.localPath}")
//    private String localPath;
//
//    @Value("${file.fileSize}")
//    private Long localFileSize;
//
//    public static String TEST_PATH = "testPath";
//
//    /**
//     * 获取文件路径
//     *
//     * @param filePath
//     * @return
//     */
//    private String getFilePath(String filePath) {
//        switch (filePath) {
//            case "testPath":
//                filePath = localPath;
//                break;
//            default:
//                throw new RuntimeException();
//        }
//        return filePath;
//    }
//
//    /**
//     * 上传文件到本地
//     *
//     * @param multipartFile
//     * @return
//     * @throws Exception
//     */
//    public String uploadFileToLocal(String path, MultipartFile multipartFile) throws Exception {
//        String fileName = multipartFile.getOriginalFilename();
//        String fileSuffix = System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
//
//        LOGGER.info("上传文件名称：" + fileName);
//        LOGGER.info("上传文件MIME类型：" + multipartFile.getContentType());
//        LOGGER.info("表单中文件组件的名字：" + multipartFile.getName());
//        LOGGER.info("上传文件字节大小：" + multipartFile.getSize());
//        if (localFileSize < multipartFile.getSize()) {
//            LOGGER.error("上传文件过大！");
//            throw new MyException(MyException.PARAM_ERR, "上传文件过大！");
//        }
//
//        File filePath = new File(localPath + path, fileSuffix);
//        if (!filePath.getParentFile().exists()) {
//            LOGGER.info("文件夹不存在，创建文件夹：" + filePath.getParentFile());
//            boolean mkdir = filePath.getParentFile().mkdirs();
//
//        }
//        LOGGER.info("开始保存文件...");
//        fileName = localPath + path + File.separator + fileSuffix;
//        multipartFile.transferTo(new File(fileName));
//        LOGGER.info("文件上传成功！");
//
//        return path + File.separator + fileSuffix;
//    }
//
//    /**
//     * 下载本地文件
//     *
//     * @param path 文件名
//     * @return
//     * @throws Exception
//     */
//    public File downloadOfLocal(String path) throws Exception {
//        File file = new File(localPath + path);
//
//        return file;
//    }
//
//
//    /**
//     * 下载文件
//     *
//     * @param response
//     * @param fileName 文件名
//     */
//    public void downloadFile(HttpServletResponse response, String fileName) {
//        fileName = localPath + fileName;
//        File file = new File(fileName);
//        byte[] buffer = new byte[1024];
//        FileInputStream fis = null;
//        BufferedInputStream bis = null;
//        try {
//            fis = new FileInputStream(file);
//            bis = new BufferedInputStream(fis);
//            OutputStream os = response.getOutputStream();
//            int i = bis.read(buffer);
//            while (i != -1) {
//                os.write(buffer, 0, i);
//                i = bis.read(buffer);
//            }
//            LOGGER.info("获取文件成功：" + fileName);
//        } catch (Exception e) {
//            LOGGER.error("读取文件失败：" + fileName, e);
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bis != null) {
//                    bis.close();
//                }
//                if (fis != null) {
//                    fis.close();
//                }
//            } catch (Exception e) {
//                LOGGER.error("关闭文件流失败！" + fileName + e);
//                e.printStackTrace();
//            }
//
//        }
//    }
//
//    /**
//     * 删除单个文件
//     *
//     * @param fileName
//     * @return
//     */
//    public Boolean deleteFileToLocal(String fileName) {
//        File file = new File(localPath + fileName);
//        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
//        if (file.exists() && file.isFile()) {
//            if (file.delete()) {
//                LOGGER.info("删除单个文件" + fileName + "成功！");
//                return true;
//            } else {
//                LOGGER.info("删除单个文件" + fileName + "失败！");
//                return false;
//            }
//        } else {
//            LOGGER.info("删除单个文件失败" + fileName + "不存在！");
//            return false;
//        }
//    }
//}
