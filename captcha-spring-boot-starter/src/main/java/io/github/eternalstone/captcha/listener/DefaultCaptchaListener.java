package io.github.eternalstone.captcha.listener;

import io.github.eternalstone.captcha.gp.base.TextEntry;

import javax.servlet.http.HttpServletRequest;

/**
 * 默认通过request.session存储生成的验证码
 *
 * @author Justzone on 2023/8/31 17:23
 */
public class DefaultCaptchaListener implements EasyCaptchaListener {
    private static final String SESSION_KEY = "captcha";

    @Override
    public void created(HttpServletRequest request, TextEntry entry) {
        request.getSession().setAttribute(SESSION_KEY, entry.getKey());
    }

    @Override
    public boolean verify(HttpServletRequest request, String code) {
        if (code != null) {
            String captcha = (String) request.getSession().getAttribute(SESSION_KEY);
            return code.trim().toLowerCase().equals(captcha);
        }
        return false;
    }
}
