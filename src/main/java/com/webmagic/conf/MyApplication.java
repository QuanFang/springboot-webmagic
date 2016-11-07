package com.webmagic.conf;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * Created by iceyohoo on 2016/10/20.
 */
@Configuration
public class MyApplication implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
            container.setPort(8011);
    }
}
