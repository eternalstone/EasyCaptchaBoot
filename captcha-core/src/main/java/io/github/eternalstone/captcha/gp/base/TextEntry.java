package io.github.eternalstone.captcha.gp.base;

import java.io.Serializable;

/**
 * to do something
 *
 * @author Justzone on 2023/9/1 9:57
 */
public class TextEntry implements Serializable {

    /**
     * 绘画字符：验证码显示绘制的内容
     */
    private char[] chars;

    /**
     * 答案字符：用于验证的字符
     */
    private String key;


    public TextEntry() {

    }

    public TextEntry(char[] chars, String key) {
        this.chars = chars;
        this.key = key;
    }

    public char[] getChars() {
        return chars;
    }

    public void setChars(char[] chars) {
        this.chars = chars;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String charsText() {
        return new String(chars);
    }
}
