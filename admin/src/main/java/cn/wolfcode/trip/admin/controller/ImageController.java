package cn.wolfcode.trip.admin.controller;

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
    public Map<String, Object> upload(MultipartFile upload) {
        Map<String, Object> map = new HashMap<>();
        try {
            String uploadUrl = UploadUtil.upload(upload, UploadUtil.PATH + "/upload");
            map.put("uploaded", 1);
            map.put("url", uploadUrl);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("uploaded", 0);
            Map<String, Object> msg = new HashMap<>();
            msg.put("message", "亲!上传图片失败!");
            map.put("error", msg);
        }
        return map;
    }
}
