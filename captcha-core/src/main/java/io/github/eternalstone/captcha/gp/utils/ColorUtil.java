package io.github.eternalstone.captcha.gp.utils;

import java.awt.*;

/**
 * to do something
 *
 * @author Justzone on 2023/9/2 20:13
 */
public class ColorUtil {

    public static String colorToHex(Color color) {
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        return String.format("#%02x%02x%02x", red, green, blue);
    }

    public static Color hexToColor(String hex) {
        try {
            return Color.decode(hex);
        } catch (Exception e) {
            return null;
        }
    }

}
