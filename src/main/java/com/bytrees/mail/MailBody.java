package com.bytrees.mail;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class MailBody {
	private String title;
	private List<String> sendToList = new ArrayList<String>();

    public MailBody(HttpServletRequest request) {
    	title = request.getParameter("title");
    	for (String sendTo : request.getParameterValues("sendTo")) {
    		sendToList.add(sendTo);
    	}
    	
    	
    }
    public String getTitle() {
    	return title;
    }
    public List<String> getSendTo() {
    	return sendToList;
    }
}
