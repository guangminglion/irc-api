package com.ircclouds.irc.api;

import java.io.*;

import com.ircclouds.irc.api.domain.*;
import com.ircclouds.irc.api.filters.*;
import com.ircclouds.irc.api.listeners.*;
import com.ircclouds.irc.api.state.*;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;

public interface IIRCSession
{
	ICommandServer getCommandServer();

	void addListeners(MESSAGE_VISIBILITY aLevel, IMessageListener... aListener);
	
	void removeListener(IMessageListener aListener);
	
	boolean open(IRCServer aServer, Callback<IIRCState> aCallback) throws IOException;
	
	void close() throws IOException;

	IMessageFilter getMessageFilter();

	void dispatchClientError(Exception e);

	/**
	 * Secure current IRC server connection.
	 *
	 * In case the 'tls' capability extension is supported, it is possible
	 * to convert a plain text connection into a TLS connection. This can
	 * only be done if the IRC server expects the TLS handshake. When the
	 * IRC server expects the TLS handshake to occur is specified in the
	 * capability. At the point when the TLS handshake is expected, this
	 * method can be called to convert the connection into an SSL
	 * connection.
	 *
	 * @param aContext An SSL context. An optional parameter, we fall back
	 * to a default SSLContext if none is provided.
	 * @param aHostname IRC server host name.
	 * @param aPort IRC server port number.
	 * @throws SSLException SSLException thrown in case the TLS handshake
	 * initiation fails.
	 */
	void secureConnection(SSLContext aContext, String aHostname, int aPort) throws SSLException;
}
