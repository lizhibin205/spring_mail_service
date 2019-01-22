package com.bytrees.mail;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.stereotype.Component;

@Component
public class ChannelConfig extends PropertyPlaceholderConfigurer {
	private static Map<String, Channel> mapList = new HashMap<String, Channel>();

    public ChannelConfig() {
    }

    public Channel getChannel(String channelName) {
    	return mapList.containsKey(channelName) ? mapList.get(channelName) : null;
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props) throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        for (Object keySet : props.keySet()) {
            String key = keySet.toString();
            String[] keyPartArr = key.split("\\.");
            if (keyPartArr.length != 3) {
            	continue;
            }
            String channelName = keyPartArr[1];
            if (mapList.containsKey(channelName)) {
            	continue;
            }
            mapList.put(channelName, new Channel(
                props.getProperty("mail." + channelName + ".host"),
                props.getProperty("mail." + channelName + ".port"),
                props.getProperty("mail." + channelName + ".username"),
                props.getProperty("mail." + channelName + ".password"),
                props.getProperty("mail." + channelName + ".sendFrom")
            ));
        }
    }
}
