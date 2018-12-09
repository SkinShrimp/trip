package cn.wolfcode.trip.base.util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 文件上传工具
 */
public class UploadUtil {
    public static final String PATH = "c://file";

    /**
     * 处理文件上传
     *
     * @param file
     * @param basePath
     * @return 123.png
     */
    public static String upload(MultipartFile file, String basePath) {
        String uuid = UUID.randomUUID().toString();

        String orgFileName = file.getOriginalFilename();
        String ext = "." + FilenameUtils.getExtension(orgFileName);
        String fileName = uuid + ext;
        try {
            File targetFile = new File(basePath, fileName);
            FileUtils.writeByteArrayToFile(targetFile, file.getBytes());

            return "/upload/" + fileName;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }


}
























