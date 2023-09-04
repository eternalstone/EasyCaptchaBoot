package io.github.eternalstone.captcha.gp.factory;

import io.github.eternalstone.captcha.gp.base.Captcha;
import io.github.eternalstone.captcha.gp.base.CaptchaEnum;
import io.github.eternalstone.captcha.gp.base.Constants;
import io.github.eternalstone.captcha.gp.utils.FontsUtil;

import java.awt.*;
import java.io.Serializable;

/**
 * 验证码属性配置
 *
 * @author Justzone on 2023/9/4 9:45
 */
public class CaptchaProperty implements Serializable {

    /**
     * 设置字体
     */
    private Font font;

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
    private Integer length = 4;

    /**
     * 验证码字符类型
     */
    private Integer charType;

    /**
     * 验证码文件格式
     */
    private String format = Constants.PNG_IMG;

    /**
     * 验证码背景颜色
     */
    private Color background;

    /**
     * 验证码类型
     */
    private CaptchaEnum captcha = CaptchaEnum.SPEC;

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void setFont(int font) {
        setFont(font, 32f);
    }

    public void setFont(int font, float size) {
        setFont(font, Font.BOLD, size);
    }

    public void setFont(int font, int style, float size) {
        this.font = FontsUtil.getFont(Captcha.FONT_NAMES[font], style, size);
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

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }

    public CaptchaEnum getCaptcha() {
        return captcha;
    }

    public void setCaptcha(CaptchaEnum captcha) {
        this.captcha = captcha;
    }
}
