package com.ircclouds.irc.api.negotiators;

import com.ircclouds.irc.api.IRCApi;
import com.ircclouds.irc.api.commands.CapCmd;
import com.ircclouds.irc.api.domain.messages.interfaces.IMessage;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

/**
 * @author Danny van Heumen
 */
public class NoopNegotiatorTest
{
	@Test
	public void testInitiate()
	{
		IRCApi irc = mock(IRCApi.class);
		NoopNegotiator neg = new NoopNegotiator();
		CapCmd cmd = neg.initiate(irc);
		assertEquals("CAP END", cmd.asString());
	}

	@Test
	public void testOnMessage()
	{
		IMessage msg = mock(IMessage.class);
		NoopNegotiator neg = new NoopNegotiator();
		neg.onMessage(msg);
	}
}
