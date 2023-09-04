package io.github.eternalstone.captcha.handler;

import io.github.eternalstone.captcha.gp.base.Captcha;
import io.github.eternalstone.captcha.gp.base.TextEntry;
import io.github.eternalstone.captcha.gp.factory.CaptchaFactory;
import io.github.eternalstone.captcha.listener.EasyCaptchaListener;
import io.github.eternalstone.captcha.properties.EasyCaptchaProperties;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @author Justzone on 2023/8/31 15:45
 */
@Configuration
public class CaptchaHandler implements InitializingBean {

    @Resource
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Resource
    private EasyCaptchaProperties easyCaptchaProperties;

    @Resource
    private EasyCaptchaListener easyCaptchaListener;

    private RequestMappingInfo.BuilderConfiguration config = new RequestMappingInfo.BuilderConfiguration();

    @Override
    public void afterPropertiesSet() throws Exception {
        this.config.setTrailingSlashMatch(requestMappingHandlerMapping.useTrailingSlashMatch());
        this.config.setContentNegotiationManager(requestMappingHandlerMapping.getContentNegotiationManager());
        this.config.setSuffixPatternMatch(requestMappingHandlerMapping.useSuffixPatternMatch());
        this.config.setRegisteredSuffixPatternMatch(requestMappingHandlerMapping.useRegisteredSuffixPatternMatch());
        this.config.setPathMatcher(requestMappingHandlerMapping.getPathMatcher());
        RequestMappingInfo mappingInfo = RequestMappingInfo
                .paths(easyCaptchaProperties.getEndpoint().getPath())
                .methods(RequestMethod.GET)
                .options(this.config)
                .build();
        Method targetMethod = CaptchaHandler.class.getDeclaredMethod("captcha", HttpServletRequest.class, HttpServletResponse.class);
        requestMappingHandlerMapping.registerMapping(mappingInfo, this, targetMethod);
    }

    @RequestMapping
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        easyCaptchaListener.beforeCreate(request);
        Captcha captcha = CaptchaFactory.getCaptcha(easyCaptchaProperties.getCaptcha());
        TextEntry entry = captcha.createText();
        easyCaptchaListener.created(request, entry);
        easyCaptchaListener.output(response, captcha, entry);
    }

}
