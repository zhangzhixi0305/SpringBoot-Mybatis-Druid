package com.zhixi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author zhangzhixi
 * @version 1.0
 * @date 2021-12-15 23:20
 */
@Controller
public class MyController {

    @RequestMapping("/hello")
    @ResponseBody
    public String test() {
        return "nihao";
    }

    /**
     * 文件上传请求
     */
    @PostMapping("/upload")
    public String toUpLoad(@RequestParam("email") String email,
                           @RequestParam("username") String username,
                           // 单文件上传
                           @RequestPart("headImg") MultipartFile headImg,
                           // 多文件上传
                           @RequestPart("photos") MultipartFile[] photos) throws IOException {
        System.out.println("email" + email);
        System.out.println("用户名：" + username);
        System.out.println("单文件的文件大小：" + headImg.getSize());
        System.out.println("多文件的文件个数：" + photos.length);
        // 将文件输出到哪里
        File file = new File("H:\\cache");
        if (!file.exists()) {
            file.mkdir();
        }


        // 保存单文件的上传地址
        if (!headImg.isEmpty()) {
            // 获取文件名
            String filename = headImg.getOriginalFilename();
            /*将文件写入到文件夹中*/
            headImg.transferTo(new File(file.getAbsolutePath() + File.separator + System.currentTimeMillis() + filename));
        }
        // 保存多文件的上传地址
        if (photos.length > 0) {
            for (MultipartFile photo : photos) {
                if (!photo.isEmpty()) {
                    // 获取文件名
                    String filename = photo.getOriginalFilename();
                    // 将收到的文件传输到给定的目标文件。
                    photo.transferTo(new File(file.getAbsolutePath() + File.separator + System.currentTimeMillis() + filename));
                }
            }
        }

        // 表单提交成功跳转到主页面
        return "index";
    }

    @RequestMapping("/upload")
    public String upload() {
        return "upload";
    }

    /*请求异常：交给全局异常处理器GlobalExceptionHandling进行处理*/
    @RequestMapping("/exception")
    public void testException() {
        int i = 10/0;
    }
}
