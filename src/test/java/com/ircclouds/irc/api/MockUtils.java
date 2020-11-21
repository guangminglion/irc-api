package com.ircclouds.irc.api;

import static org.powermock.api.mockito.PowerMockito.whenNew;

import java.util.concurrent.*;

import com.ircclouds.irc.api.comms.*;
import com.ircclouds.irc.api.state.*;

public class MockUtils
{
	private static Thread readerThread;
	private static ConnectedApi connectedApi;

	public static ConnectedApi newConnectedApi(SocketChannelConnection aConnection, IServerParameters aServerParams, Integer aWaitingDuration) throws Exception
	{
		final CountDownLatch _cdl = new CountDownLatch(1);

		whenNew(SocketChannelConnection.class).withAnyArguments().thenReturn(aConnection);
		final IRCApi api = new IRCApiImpl(false);
		api.connect(aServerParams, new Callback<IIRCState>()
		{
			@Override
			public void onSuccess(final IIRCState aObject)
			{
				setConnectedApi(api, aObject);

				readerThread = Thread.currentThread();

				_cdl.countDown();
			}

			@Override
			public void onFailure(Exception aExc)
			{
				readerThread = Thread.currentThread();

				_cdl.countDown();
			}
		}, null);

		_cdl.await(aWaitingDuration, TimeUnit.SECONDS);
		if (readerThread != null)
		{
			readerThread.join();
		}

		return connectedApi;
	}

	protected interface ConnectedApi
	{
		IRCApi getIRCApi();

		IIRCState getConnectedState();
	}

	private static void setConnectedApi(final IRCApi aApi, final IIRCState aConnectedState)
	{
		connectedApi = new ConnectedApi()
		{

			@Override
			public IRCApi getIRCApi()
			{
				return aApi;
			}

			@Override
			public IIRCState getConnectedState()
			{
				return aConnectedState;
			}
		};
	}
}
