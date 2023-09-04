package io.github.eternalstone.captcha.gp.base;

import io.github.eternalstone.captcha.gp.exception.EasyCaptchaException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 算术验证码抽象类
 * Created by 王帆 on 2019-08-23 上午 10:08.
 */
public abstract class ArithmeticCaptchaAbstract extends Captcha {

    private ScriptEngineManager manager = new ScriptEngineManager();
    private ScriptEngine engine = manager.getEngineByName("javascript");

    public ArithmeticCaptchaAbstract() {
        setLen(2);
    }

    @Override
    public void setLen(int len) {
        //限定算子数量
        if (len < 2) {
            len = 2;
        }
        if (len > 5) {
            len = 5;
        }
        super.setLen(len);
    }

    /**
     * 生成随机验证码
     *
     * @return 验证码字符数组
     */
    @Override
    protected char[] alphas() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(num(10));
            if (i < len - 1) {
                int type = num(1, 4);
                if (type == 1) {
                    sb.append("+");
                } else if (type == 2) {
                    sb.append("-");
                } else if (type == 3) {
                    sb.append("x");
                }
            }
        }
        return sb.toString().toCharArray();
    }

    @Override
    public TextEntry createText() {
        try {
            char[] alphas = alphas();
            StringBuilder arithmetic = new StringBuilder().append(alphas);
            String key = String.valueOf(engine.eval(arithmetic.toString().replaceAll("x", "*")));
            char[] arithmeticChars = arithmetic.append("=?").toString().toCharArray();
            return new TextEntry(arithmeticChars, key);
        } catch (ScriptException e) {
            throw new EasyCaptchaException("EasyCaptcha Error", e);
        }
    }


}
