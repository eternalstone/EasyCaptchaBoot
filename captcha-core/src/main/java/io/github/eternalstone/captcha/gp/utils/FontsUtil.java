package io.github.eternalstone.captcha.gp.utils;

import io.github.eternalstone.captcha.gp.exception.EasyCaptchaException;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

/**
 * 直接使用流的方式获取字体文件
 */
public class FontsUtil {


    /**
     * @param fontName 字体文件名称
     * @return
     */
    public static Font getFont(String fontName, int style, float size) {
        Font font;
        try (InputStream inputStream = FontsUtil.class.getResourceAsStream("/" + fontName)) {
            font = Font.createFont(Font.TRUETYPE_FONT, inputStream).deriveFont(style, size);
        } catch (FontFormatException | IOException e) {
            throw new EasyCaptchaException("EasyCaptcha Error", e);
        }
        return font;
    }

}
