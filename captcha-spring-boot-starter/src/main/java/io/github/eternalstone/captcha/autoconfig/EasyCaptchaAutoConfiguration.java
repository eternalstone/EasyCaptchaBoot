package io.github.eternalstone.captcha.autoconfig;

import io.github.eternalstone.captcha.gp.factory.CaptchaFactory;
import io.github.eternalstone.captcha.gp.factory.CaptchaProperty;
import io.github.eternalstone.captcha.gp.utils.ColorUtil;
import io.github.eternalstone.captcha.handler.CaptchaHandler;
import io.github.eternalstone.captcha.listener.DefaultCaptchaListener;
import io.github.eternalstone.captcha.listener.EasyCaptchaListener;
import io.github.eternalstone.captcha.properties.EasyCaptchaProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.awt.*;

/**
 * 配置默认开启http端点，如果配置关闭，建议直接使用core包而不用starter包
 *
 * @author Justzone on 2023/8/31 12:14
 */
@Configuration
@ConditionalOnProperty(value = EasyCaptchaProperties.PREFIX + ".endpoint.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(EasyCaptchaProperties.class)
public class EasyCaptchaAutoConfiguration {

    @Bean
    public CaptchaHandler captchaHandler(EasyCaptchaProperties properties) {
        initFactory(properties);
        return new CaptchaHandler();
    }

    @Bean
    @ConditionalOnMissingBean(EasyCaptchaListener.class)
    public EasyCaptchaListener easyCaptchaListener() {
        return new DefaultCaptchaListener();
    }

    /**
     * 初始化从配置读取的验证码实例到工厂中
     *
     * @param properties
     */
    private void initFactory(EasyCaptchaProperties properties) {
        CaptchaProperty property = new CaptchaProperty();
        property.setCaptcha(properties.getCaptcha());
        property.setWidth(properties.getWidth());
        property.setHeight(properties.getHeight());
        property.setLength(properties.getLength());
        property.setCharType(properties.getCharType());
        property.setFormat(properties.getFormat());
        if (properties.getFont() != null) {
            property.setFont(properties.getFont());
        }
        if (StringUtils.hasText(properties.getBackground())) {
            Color color = ColorUtil.hexToColor(properties.getBackground().trim());
            if (color != null) {
                property.setBackground(color);
            }
        }
        CaptchaFactory.getCaptcha(property);
    }

}
