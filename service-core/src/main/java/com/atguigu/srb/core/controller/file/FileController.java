package com.atguigu.srb.core.controller.file;


import com.atguigu.common.result.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Api(tags = "文件接受上传使用")
@Slf4j
@RestController
@RequestMapping("/api/oss/file")
public class FileController {


    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file) {

        String originalFilename = file.getOriginalFilename();
        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String uuid = UUID.randomUUID().toString().substring(0, 22);
        Path dest = Paths.get(uuid, originalFilename);
        log.info(dest.toString());
        //直接原路径返回 ，为了后面测试方便
        return R.ok().data("url", originalFilename);
    }

    @GetMapping("/test")
    public String string(@RequestParam(defaultValue = "Jeremy") String name) {

        return "hello " + name;
    }
}
