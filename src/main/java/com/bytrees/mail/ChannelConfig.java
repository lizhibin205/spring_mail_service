package com.bytrees.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {
	@Value("${test}")
    private String host;
	
	public String getHost() {
		return host;
	}
}
