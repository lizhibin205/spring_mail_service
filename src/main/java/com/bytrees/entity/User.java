package com.bytrees.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;
    private Long id;
	private String username;
	private String password;
	private Timestamp createTime;
    private Timestamp updateTime;

    public User() {
    	
    }
    public User(String username, String password) {
    	this.username = username;
    	this.password = password;
    	this.createTime = new Timestamp(new Date().getTime());
    }

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

    public Timestamp getCreateTime() {
    	return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
    	this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
    	return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
    	this.updateTime = updateTime;
    }
}
