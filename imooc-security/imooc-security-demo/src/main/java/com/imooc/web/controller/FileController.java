package com.imooc.web.controller;

import com.imooc.dto.FileInfo;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-15 10:26
 */
@RestController
@RequestMapping("/file")
public class FileController {

    private String folder = "D:\\aacode\\java\\imooc\\imooc-security\\imooc-security-demo\\src\\main\\java\\com\\imooc\\web\\controller";

    @PostMapping
    public FileInfo upload(MultipartFile file)throws Exception{

        System.out.println("public FileInfo upload(MultipartFile file)方法");
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());

        File localFile=new File(folder,new Date().getTime()+".txt");
        file.transferTo(localFile);

        return new FileInfo(localFile.getAbsolutePath());
    }

    @GetMapping("/{id}")
    public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response)throws Exception{

        //写在小括号里面，IO流会自己关闭，省去累赘代码
        try (InputStream inputStream=new FileInputStream(new File(folder,id+".txt"));
             OutputStream outputStream=response.getOutputStream()){

            response.setContentType("application/x-download");
            response.addHeader("Content-Disposition","attachment;filename=test.txt");

            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
        }
    }

}
