package com.bytrees.controller;

import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private static final Logger logger = LoggerFactory.getLogger(Mail.class);

	@RequestMapping(value = "/mail", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
    public String send(HttpServletRequest request) {
		try {
			//valid mail body
			MailBody mailBody = new MailBody(request);
			mailBody.valid();
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
			List<String> sendToStrList = mailBody.getSendTo();
			InternetAddress[] sendToList = new InternetAddress[sendToStrList.size()];
			for (int index = 0; index < sendToStrList.size(); index++) {
				sendToList[index] = new InternetAddress(sendToStrList.get(index));
			}
			//InternetAddress[] sendToList = {new InternetAddress("test@qq.com")};
			message.setRecipients(MimeMessage.RecipientType.TO, sendToList);
			//标题
			message.setSubject(mailBody.getSubject());
			//正文
			message.setText(mailBody.getText());
			message.saveChanges();
			Transport.send(message);
		    return (new ResponseJson<String>(200, "success.", null)).toString();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return (new ResponseJson<String>(500, ex.getMessage(), null)).toString();
		}
    }

	@RequestMapping(value = "/view", method = RequestMethod.POST, produces={"application/json;charset=UTF-8"})
	public String viewBody(HttpServletRequest request) {
		MailBody mailBody = new MailBody(request);
		return (new ResponseJson<MailBody>(200, "success.", mailBody)).toString();
	}
}
