package com.example.fileuploaddemo.controller;


import org.springframework.http.HttpHeaders;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
public class FileUploadController {

    /**
     * 上传文件。
     *
     * @param file
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFile(@RequestParam(value = "file", required = false)MultipartFile file,@RequestHeader HttpHeaders headers) throws IOException {
        System.out.println("====================================");
        for(String key: headers.keySet()){
            Object o = headers.get(key);
            System.out.println("key=" + key + ",value=" + o.toString());
        }
        byte[] bytes = file.getBytes();
        File fileToSave = new File(file.getOriginalFilename());
        FileCopyUtils.copy(bytes, fileToSave);
        return fileToSave.getAbsolutePath();
    }

    @GetMapping(value = "/hello")
    public String hello(@RequestParam(value = "name",required = false) String name) {
        if (name == null || name.length() == 0){
            return "hello annoyounce....";
        }
        return "hello ...." + name;
    }

//    解决 windows 的 curl 命令执行后返回乱码
//    chcp 65001 就是换成UTF-8代码页
//    chcp 936 可以换回默认的GBK
//    chcp 437 是美国英语
//    在命令行标题栏上点击右键，选择"属性"->"字体",将字体修改为True Type字体"Lucida Console",然后点击确定将属性应用到当前窗口。
}