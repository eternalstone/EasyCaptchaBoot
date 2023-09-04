package io.github.eternalstone.captcha.properties;

import java.io.Serializable;

/**
 * to do something
 *
 * @author Justzone on 2023/8/31 17:55
 */
public class EndpointElement implements Serializable {

    private String path = "/captcha";

    private boolean enabled = true;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
