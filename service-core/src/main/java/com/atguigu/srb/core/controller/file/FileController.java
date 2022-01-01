package com.atguigu.srb.core.controller.file;


import com.atguigu.common.result.R;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Api(tags = "文件接受上传使用")
@Slf4j
@RestController
@RequestMapping("/api/oss/file")
public class FileController {


    @PostMapping("/upload")
    public R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {

        String originalFilename = file.getOriginalFilename();

        //String servletPath = request.getServletPath();
        //log.info("servletPath : {}",servletPath);  // ->  /api/oss/file/upload
        String scheme = request.getScheme();
        log.info("scheme : {}",scheme);
        String serverName = request.getServerName();
        log.info("serverName : {}",serverName);
        int serverPort = request.getServerPort();
        log.info("serverPort : {}",serverPort);

        int localPort = request.getLocalPort();
        log.info("localPort : {}",localPort);

        int remotePort = request.getRemotePort();
        log.info("remotePort : {}",remotePort);

        String remoteAddr = request.getRemoteAddr();
        log.info("remoteAddr : {}",remoteAddr);

        String requestURI = request.getRequestURI();
        log.info("requestURI : {}",requestURI);

        String fileExtension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String uuid = UUID.randomUUID().toString().substring(0, 22);
        Path dest = Paths.get(uuid, originalFilename);
        String contextPath = request.getSession().getServletContext().getContextPath();
        log.info(contextPath);
        originalFilename = "http://localhost/api/oss/file/image?name=" + originalFilename;
        //直接原路径返回 ，为了后面测试方便
        return R.ok().data("url", originalFilename);
    }


    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] string(@RequestParam(defaultValue = "car.jpg") String name) throws IOException {

        String path = "E:\\picture\\" + name;
        FileInputStream inputStream = new FileInputStream(path);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        return bytes;

    }
}
