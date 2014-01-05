package com.ircclouds.irc.api.domain.messages;

import com.ircclouds.irc.api.domain.*;
import com.ircclouds.irc.api.domain.messages.interfaces.*;

public class ChannelNotice extends UserNotice implements IChannelMessage
{
	private String channelName;

	public ChannelNotice(WritableIRCUser aFromUser, String aText, String aChannelName)
	{
		super(aFromUser, aText);
		
		channelName = aChannelName;
	}
	
	public String getChannelName()
	{
		return channelName;
	}
}
