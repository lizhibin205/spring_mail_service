package com.bytrees.mail;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class MailBody {
	private List<String> sendToList = new ArrayList<String>();
	private String subject;
	private String text;
	
	public static MailBody createFromServletRequest(HttpServletRequest request) throws Exception {
		MailBody mailBody = new MailBody();
		//subject
		mailBody.subject = request.getParameter("subject");
		if (mailBody.subject == null || mailBody.subject.isEmpty()) {
			throw new Exception("mail subject should not be empty!");
		}
		//sendTo
		String[] sendToArr = request.getParameterValues("sendTo");
		if (sendToArr == null) {
			throw new Exception("mail sendTo should not be null!");
		}
		for (String sendTo : sendToArr) {
			if (sendTo == null || !Pattern.matches("^[A-Za-z\\d\\.-]+@[A-Za-z\\d]+\\.[A-Za-z\\d]{2,4}$", sendTo)) {
				throw new Exception("mail sendTo format error:" + sendTo);
			}
			mailBody.sendToList.add(sendTo);
		}
		if (mailBody.sendToList.size() < 1) {
			throw new Exception("mail sendToList should not be empty!");
		}
		//text
		mailBody.text = request.getParameter("text");
		if (mailBody.text == null || mailBody.text.isEmpty()) {
			throw new Exception("mail text should not be empty!");
		}
		return mailBody;
	}
    public MailBody() {
    	
    }
    public String getSubject() {
    	return subject;
    }
    public List<String> getSendTo() {
    	return sendToList;
    }
    public String getText() {
    	return text;
    }
}
