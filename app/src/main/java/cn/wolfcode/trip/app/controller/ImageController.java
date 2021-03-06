package cn.wolfcode.trip.app.controller;

import cn.wolfcode.trip.base.util.UploadUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/images")
public class ImageController {
    @PostMapping
    public Map<String, Object> upload(MultipartFile file) {
        Map<String, Object> map = new HashMap<>();
        try {
            String uploadUrl = UploadUtil.upload(file, UploadUtil.PATH + "/upload");
            map.put("status", 1);
            map.put("url", uploadUrl);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 0);
            map.put("msg", "亲!上传图片失败!");
        }
        return map;
    }
}
