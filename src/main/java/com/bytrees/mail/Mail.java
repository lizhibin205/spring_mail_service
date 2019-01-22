package com.bytrees.mail;

import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public static void send(MailBody mailBody,ChannelConfig channelConfig) throws Exception {
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
    }
}
