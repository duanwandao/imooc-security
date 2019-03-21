package com.imooc.security.core.validate.code;

import com.imooc.security.core.validate.code.image.ImageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 生成校验码的请求处理器
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 14:13
 */
@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    /**
     * 创建验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response)throws Exception {
        ImageCode imageCode = (ImageCode)imageCodeGenerator.generate(new ServletWebRequest(request));
        //code放到sesison 第二步
        sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        //将图片写到响应的接口中 第三步
        ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
    }

}
