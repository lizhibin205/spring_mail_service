package com.bytrees.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {
	@Value("${log4j.appender.logFile.File}")
    private String host;
	
	public String getHost() {
		return host;
	}
}
