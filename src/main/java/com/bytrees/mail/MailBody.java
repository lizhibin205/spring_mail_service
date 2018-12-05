package com.bytrees.mail;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class MailBody {
	private List<String> sendToList = new ArrayList<String>();
	private String subject;
	private String text;

    public MailBody(HttpServletRequest request) {
    	subject = request.getParameter("subject");
    	String[] sendToArr = request.getParameterValues("sendTo");
    	if (sendToArr != null) {
    		for (String sendTo : sendToArr) {
        		sendToList.add(sendTo);
        	}
    	}
    	text = request.getParameter("text");
    }
    public void valid() throws Exception {
    	if (subject == null) {
    		throw new Exception("Mail subject is null.");
    	}
    	if (sendToList.size() == 0) {
    		throw new Exception("Mail send to is empty.");
    	}
    	if (text == null) {
    		throw new Exception("Mail text is null.");
    	}
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
