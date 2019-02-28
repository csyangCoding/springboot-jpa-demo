package com.csy.springboot.util;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
public class StaticFieldInjectionConfiguration {

    @Autowired
    MessageSource resources;

    @PostConstruct
    private void init() {
    	System.out.println("\n\n-----StaticFieldInjectionConfiguration----");
		CheckUtil.setResources(resources);
    }
}