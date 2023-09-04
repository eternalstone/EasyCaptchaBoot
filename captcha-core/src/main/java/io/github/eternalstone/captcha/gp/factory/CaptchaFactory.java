package io.github.eternalstone.captcha.gp.factory;

import io.github.eternalstone.captcha.gp.base.Captcha;
import io.github.eternalstone.captcha.gp.base.CaptchaEnum;
import io.github.eternalstone.captcha.gp.exception.EasyCaptchaException;

import java.util.concurrent.ConcurrentHashMap;

/**
 * to do something
 *
 * @author Justzone on 2023/8/31 15:12
 */
public class CaptchaFactory {

    private static final ConcurrentHashMap<CaptchaEnum, Captcha> CAPTCHA_MAP = new ConcurrentHashMap<>();

    public static Captcha getCaptcha(CaptchaEnum captchaEnum) {
        //从本地缓存中获取
        Captcha captcha = CAPTCHA_MAP.get(captchaEnum);
        if (captcha != null) {
            return captcha;
        }
        try {
            synchronized (captchaEnum) {
                captcha = CAPTCHA_MAP.get(captchaEnum);
                if (captcha != null) {
                    return captcha;
                }
                //根据验证码类型获取class对象
                Class<? extends Captcha> clazz = captchaEnum.getClazz();
                captcha = clazz.newInstance();
                CAPTCHA_MAP.put(captchaEnum, captcha);
                return captcha;
            }
        } catch (Exception e) {
            throw new EasyCaptchaException("Initialize Captcha instance exception");
        }
    }

    public static Captcha getCaptcha(CaptchaProperty property) {
        if (property == null) {
            property = new CaptchaProperty();
        }
        Captcha captcha = getCaptcha(property.getCaptcha());
        if (property.getFont() != null) {
            captcha.setFont(property.getFont());
        }
        if (property.getWidth() != null) {
            captcha.setWidth(property.getWidth());
        }
        if (property.getHeight() != null) {
            captcha.setHeight(property.getHeight());
        }
        if (property.getLength() != null) {
            captcha.setLen(property.getLength());
        }
        if (property.getBackground()!= null) {
            captcha.setBackground(property.getBackground());
        }
        if (property.getFormat() != null) {
            captcha.setFormat(property.getFormat());
        }
        if (property.getCharType() != null) {
            captcha.setCharType(property.getCharType());
        }
        return captcha;
    }

}
