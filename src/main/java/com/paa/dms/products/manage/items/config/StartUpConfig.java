package com.paa.dms.products.manage.items.config;

import com.paa.dms.products.manage.items.constants.APIConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StartUpConfig implements ApplicationListener<ApplicationStartedEvent> {
    @Autowired
    private APIConstants apiConstants;
    private static final Logger logger = LoggerFactory.getLogger(StartUpConfig.class);

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info("MS started : " + apiConstants.getSERVICE_NAME());
    }
}
