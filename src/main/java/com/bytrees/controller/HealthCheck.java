package com.bytrees.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.utils.ResponseJson;

@RestController
public class HealthCheck {
	private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);

	@RequestMapping(value = "/healthCheck", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
	public String index() {
		logger.info("It works.");
	    return (new ResponseJson<Object>(200, "success.", null)).toString();
	}
}
