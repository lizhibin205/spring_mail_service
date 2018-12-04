package com.bytrees.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.mail.ChannelConfig;
import com.bytrees.utils.ResponseJson;

@RestController
@RequestMapping(value = "/mail")
public class Mail {
	@Autowired
	private ChannelConfig channelConfig;

	@RequestMapping(value = "/{channelName}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String smtp(@PathVariable(value="channelName") String channelName) {
		//load configuration
		try {
		    
		    return (new ResponseJson<String>(200, "success.", null)).toString();
		} catch (Exception ex) {
			return (new ResponseJson<String>(500, ex.getMessage(), null)).toString();
		}
    }
}
