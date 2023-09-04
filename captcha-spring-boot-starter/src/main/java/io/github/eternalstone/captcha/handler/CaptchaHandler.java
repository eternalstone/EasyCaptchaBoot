package io.github.eternalstone.captcha.handler;

import io.github.eternalstone.captcha.gp.base.Captcha;
import io.github.eternalstone.captcha.gp.base.TextEntry;
import io.github.eternalstone.captcha.gp.factory.CaptchaFactory;
import io.github.eternalstone.captcha.listener.EasyCaptchaListener;
import io.github.eternalstone.captcha.properties.EasyCaptchaProperties;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * SpringBoot2.x使用注册handlerMapping的方式将验证码接口配置化
 * SpringBoot2.6.0以上版本，需要配置 spring.mvc.pathmatch.matching-strategy=ANT_PATH_MATCHER
 * 否则会报错误：java.lang.IllegalArgumentException: Expected lookupPath in request attribute "org.springframework.web.util.UrlPathHelper.PATH".
 *
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
