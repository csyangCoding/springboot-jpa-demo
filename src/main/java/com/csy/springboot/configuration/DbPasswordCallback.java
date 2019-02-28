package com.csy.springboot.configuration;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.util.DruidPasswordCallback;

public class DbPasswordCallback extends DruidPasswordCallback {
	private static final Logger logger = LoggerFactory.getLogger(DbPasswordCallback.class); 
	/**
	 * 
	 */
	private static final long serialVersionUID = 5587801020368008660L;

	@Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        String password = (String) properties.get("password");
//        String publickey = (String) properties.get("publickey");
        try {
            String dbpassword = ConfigTools.decrypt(password);
            setPassword(dbpassword.toCharArray());
        } catch (Exception e) {
        	logger.error("Druid ConfigTools.decrypt", e);
        }
    }
}
