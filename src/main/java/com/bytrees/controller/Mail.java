package com.bytrees.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.utils.ResponseJson;

@RestController
@RequestMapping(value = "/mail")
public class Mail {
	@RequestMapping(value = "/smtp/{channel}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String smtp(@PathVariable(value="channel") String channel) {
		return (new ResponseJson<Object>(200, "success.", null)).toString();
    }
}
