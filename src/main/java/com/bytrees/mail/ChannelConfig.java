package com.bytrees.mail;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChannelConfig {
	@Value("${mail.default.host}")
	private String host;
	@Value("${mail.default.port}")
	private String port;
	@Value("${mail.default.username}")
	private String username;
	@Value("${mail.default.password}")
	private String password;
	@Value("${mail.default.sendFrom}")
	private String sendFrom;

	public String getHost() {
		return host;
	}

	public String getPort() {
		return port;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSendForm() {
		return sendFrom;
	}
}
