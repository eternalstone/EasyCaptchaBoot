package io.github.eternalstone.captcha.gp.base;

import io.github.eternalstone.captcha.gp.captcha.*;

/**
 * to do something
 *
 * @author Justzone on 2023/9/1 11:28
 */
public enum CaptchaEnum {

    SPEC(SpecCaptcha.class),
    GIF(GifCaptcha.class),
    CHINESE(ChineseCaptcha.class),
    CHINESE_GIF(ChineseGifCaptcha.class),
    ARITHMETIC(ArithmeticCaptcha.class),
    ;

    private Class<? extends Captcha> clazz;

    CaptchaEnum(Class<? extends Captcha> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends Captcha> getClazz() {
        return clazz;
    }
}
