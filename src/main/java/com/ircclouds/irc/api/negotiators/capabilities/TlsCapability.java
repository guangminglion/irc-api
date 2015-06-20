package com.ircclouds.irc.api.negotiators.capabilities;

import com.ircclouds.irc.api.negotiators.CompositeNegotiator.Capability;
import com.ircclouds.irc.api.negotiators.api.Relay;

/**
 * TLS capability.
 *
 * @author Danny van Heumen
 */
public class TlsCapability implements Capability
{

	@Override
	public String getId() {
		return "tls";
	}

	@Override
	public boolean enable() {
		return true;
	}

	@Override
	public boolean converse(Relay relay, String msg) {
		if (msg == null)
		{
			relay.send("STARTTLS");
			return true;
		}
		return false;
	}
}
