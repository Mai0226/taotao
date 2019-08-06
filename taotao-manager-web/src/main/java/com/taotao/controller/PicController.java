package com.taotao.controller;

import com.taotao.common.pojo.PicResult;
import com.taotao.service.PicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/pic")
public class PicController {
    @Autowired
    private PicService picService;
    @RequestMapping("/upload")
    @ResponseBody()
    public PicResult uploda(MultipartFile uploadFile){
        try {
            byte[] bytes = uploadFile.getBytes();
            String name = uploadFile.getOriginalFilename();
            PicResult pictureResult = picService.upload(bytes,name);
            return pictureResult;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
