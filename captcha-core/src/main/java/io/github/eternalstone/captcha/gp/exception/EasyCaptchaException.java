package io.github.eternalstone.captcha.gp.exception;

/**
 * to do something
 *
 * @author Justzone on 2022/8/30 17:14
 */
public class EasyCaptchaException extends RuntimeException{

    public EasyCaptchaException(String message) {
        super(message);
    }

    public EasyCaptchaException(String message, Throwable cause) {
        super(message, cause);
    }
}
