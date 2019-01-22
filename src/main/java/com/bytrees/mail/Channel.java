package com.bytrees.mail;

public class Channel {
	private String host;
	private String port;
	private String username;
	private String password;
	private String sendFrom;

	public Channel(String host, String port, String username, String password, String sendFrom) {
		this.host = host;
		this.port = port;
		this.username = username;
		this.password = password;
		this.sendFrom = sendFrom;
	}

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
	public String getSendFrom() {
		return sendFrom;
	}
}
