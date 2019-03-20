package com.imooc.security.browser;

import com.imooc.security.browser.support.SimpleResponse;
import com.imooc.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Description
 * @auther 断弯刀
 * @create 2019-03-19 19:28
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    private RequestCache requestCache = new HttpSessionRequestCache();

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要身份验证时跳转到这里
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)   //返回401状态码
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)throws Exception {

        //保存的之前的请求(引发跳转的请求)拿出来
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            //引发跳转的url
            String targetUrl=savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是:"+targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, "html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
                log.info("BrowserSecurityController==>securityProperties.getBrowser().getLoginPage()=="+securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证,请引导用户到登录页");
    }
}
