package com.ircclouds.irc.api.domain.messages;

import com.ircclouds.irc.api.domain.*;

/**
 * 
 * @author
 * 
 */
public class ChannelPingMsg extends ChannelCTCPMsg
{
	public ChannelPingMsg(WritableIRCUser aFromUser, String aText, String aChanName)
	{
		super(aFromUser, aText, aChanName);
	}
}
