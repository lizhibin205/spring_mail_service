package com.bytrees.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.mail.ChannelConfig;
import com.bytrees.mail.MailBody;
import com.bytrees.utils.ResponseJson;

@RestController
public class Mail {
	@Autowired
	private ChannelConfig channelConfig;
	private static final Logger logger = LoggerFactory.getLogger(Mail.class);

	@RequestMapping(value = "/mail", method = RequestMethod.POST)
    public ResponseEntity<ResponseJson<MailBody>> send(HttpServletRequest request) {
		try {
			//valid mail body
			MailBody mailBody = MailBody.createFromServletRequest(request);
			com.bytrees.mail.Mail.send(mailBody, channelConfig);
		    return new ResponseEntity<>(new ResponseJson<MailBody>(200, "success.", mailBody), HttpStatus.OK);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(new ResponseJson<MailBody>(500, ex.getMessage(), null), HttpStatus.OK);
		}
    }

	/**
	 * 查询MailChannel列表
	 * 
	 */
	@RequestMapping(value = "/viewChannelList", method = RequestMethod.GET)
	public void viewChannelList(HttpServletRequest request) {
		
	}

	/**
	 * 预览邮件内容
	 * 
	 */
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public ResponseEntity<ResponseJson<MailBody>> viewBody(HttpServletRequest request) {
		try {
			MailBody mailBody = MailBody.createFromServletRequest(request);
			return new ResponseEntity<>(new ResponseJson<MailBody>(200, "success.", mailBody), HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(new ResponseJson<MailBody>(500, ex.getMessage(), null), HttpStatus.OK);
		}
	}
}
