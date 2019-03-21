package com.imooc.security.core.validate.code.image;

import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 默认的图片验证码生成器
 * @Description
 * @auther 断弯刀
 * @create 2019-03-20 21:21
 */
@Data
public class ImageCodeGenerator implements ValidateCodeGenerator {

    /**
     * 系统配置
     */
    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(ServletWebRequest request) {
        //生成随机验证码
        int width = ServletRequestUtils.getIntParameter(request.getRequest(), "width", securityProperties.getCode().getImage().getWidth());//宽和高
        int height = ServletRequestUtils.getIntParameter(request.getRequest(), "height", securityProperties.getCode().getImage().getHeight());
        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_BGR);

        Graphics g = image.getGraphics();

        Random random = new Random();

        g.setColor(getRandColor(200,250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman",Font.ITALIC,20));
        g.setColor(getRandColor(160,200));
        for(int i=0;i<155;i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl =random.nextInt(12);
            int yl =random.nextInt(12);
            g.drawLine(x, y, x+xl, y+yl);
        }

        String sRand = "";
        for (int i = 0; i < securityProperties.getCode().getImage().getLength(); i++) {   //数字验证码长度
            String rand = String.valueOf(random.nextInt(10));
            sRand +=rand;
            g.setColor(new Color(20 + random.nextInt(110),
                    20 + random.nextInt(110),20 + random.nextInt(110)));
            g.drawString(rand, 13*i+6, 16);
        }

        g.dispose();

        return new ImageCode(image,sRand,60);//有效期60秒
    }

    /**
     * 生成随机背景条纹
     */
    private Color getRandColor(int fc,int bc) {
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r,g,b);
    }
}
