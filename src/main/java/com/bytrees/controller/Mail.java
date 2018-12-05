package com.bytrees.controller;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytrees.mail.Authenticator;
import com.bytrees.mail.ChannelConfig;
import com.bytrees.mail.MailBody;
import com.bytrees.utils.ResponseJson;

@RestController
public class Mail {
	@Autowired
	private ChannelConfig channelConfig;
	private static final Logger logger = LoggerFactory.getLogger(HealthCheck.class);

	@RequestMapping(value = "/mail/{channelName}", method = RequestMethod.GET, produces={"application/json;charset=UTF-8"})
    public String send(@PathVariable(value="channelName") String channelName) {
		try {
			//load configuration
			Properties mailProperties = new Properties();
			mailProperties.put("mail.smtp.host", channelConfig.getHost());
			mailProperties.put("mail.smtp.port", channelConfig.getPort());
			mailProperties.put("mail.smtp.auth", "true");
			mailProperties.put("mail.transport.protocol", "smtp");
			mailProperties.put("mail.smtp.starttls.enable", "true");
			mailProperties.put("mail.smtp.ssl.checkserveridentity", "false");
			mailProperties.put("mail.smtp.ssl.trust", channelConfig.getHost());
			Session session = Session.getInstance(mailProperties, 
					new Authenticator(channelConfig.getUsername(), channelConfig.getPassword()));
			//邮件信息
			MimeMessage message = new MimeMessage(session);
			//发件人
			message.setFrom(new InternetAddress(channelConfig.getSendForm()));
			//收件人
			InternetAddress[] sendToList = {new InternetAddress("331313869@qq.com")};
			message.setRecipients(MimeMessage.RecipientType.TO, sendToList);
			//标题
			message.setSubject("Mail test.");
			//正文
			message.setText("这是一封测试邮件！");
			message.saveChanges();
			Transport.send(message);
		    return (new ResponseJson<String>(200, "success.", null)).toString();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return (new ResponseJson<String>(500, ex.getMessage(), null)).toString();
		}
    }

	@RequestMapping(value = "/view", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String viewBody(MailBody mailBody) {
		return (new ResponseJson<MailBody>(200, "success.", mailBody)).toString();
	}
}
