package com.bytrees.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.utils.ResponseJson;

@RestController
public class HealthCheck {
	@RequestMapping(value = "/healthCheck", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	public String index() {
	    return (new ResponseJson<Object>(200, "success.", null)).toString();
	}
}
