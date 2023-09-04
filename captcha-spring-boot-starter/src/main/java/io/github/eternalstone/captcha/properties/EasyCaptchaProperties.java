package io.github.eternalstone.captcha.properties;

import io.github.eternalstone.captcha.gp.base.CaptchaEnum;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * to do something
 *
 * @author Justzone on 2023/8/31 12:36
 */
@ConfigurationProperties(prefix = EasyCaptchaProperties.PREFIX)
public class EasyCaptchaProperties {

    public static final String PREFIX = "easy-captcha";

    /**
     * 设置字体
     */
    private Integer font;

    /**
     * 设置图形长度
     */
    private Integer width = 130;

    /**
     * 设置图形宽度
     */
    private Integer height = 48;

    /**
     * 设置验证码字符长度
     */
    private Integer length;

    /**
     * 验证码字符类型
     */
    private Integer charType;

    /**
     * 验证码文件格式
     */
    private String format;

    /**
     * 验证码背景颜色
     */
    private String background;

    /**
     * 验证码类型
     */
    private CaptchaEnum captcha = CaptchaEnum.SPEC;

    /**
     * http端点配置
     */
    private EndpointElement endpoint = new EndpointElement();


    public Integer getFont() {
        return font;
    }

    public void setFont(Integer font) {
        this.font = font;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getCharType() {
        return charType;
    }

    public void setCharType(Integer charType) {
        this.charType = charType;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public CaptchaEnum getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaEnum captcha) {
        this.captcha = captcha;
    }

    public EndpointElement getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(EndpointElement endpoint) {
        this.endpoint = endpoint;
    }
}
