package io.github.eternalstone.captcha.listener;

import io.github.eternalstone.captcha.gp.base.Captcha;
import io.github.eternalstone.captcha.gp.base.TextEntry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * to do something
 *
 * @author Justzone on 2023/8/31 17:16
 */
public interface EasyCaptchaListener {

    /**
     * 在验证码实例创建随机码之前调用
     *
     * @param request
     */
    default void beforeCreate(HttpServletRequest request) {

    }

    /**
     * 在验证码实例创建随机码之后调用
     *
     * @param request
     */
    default void created(HttpServletRequest request, TextEntry entry) {

    }

    /**
     * 实现接口输出，这里有两种情况
     * 1.如果验证码是单机存储在session会话中，直接通过流输出图片
     * 2.如果是分布式环境验证码存储在redis等中间件中，需要重写此方法，将构造的返回实体通过response写出成json
     */
    default void output(HttpServletResponse response, Captcha captcha, TextEntry entry) throws IOException {
        response.setContentType("image/"+ captcha.getFormat());
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        captcha.out(response.getOutputStream(), entry);
    }

    /**
     * 校验验证码
     * @param request
     * @param code
     * @return
     */
    boolean verify(HttpServletRequest request, String code);

}
