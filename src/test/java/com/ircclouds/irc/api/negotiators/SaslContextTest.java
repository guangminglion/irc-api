package com.ircclouds.irc.api.negotiators;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import com.ircclouds.irc.api.negotiators.api.Relay;
import org.junit.Test;

/**
 *
 * @author Danny van Heumen
 */
public class SaslContextTest {
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	public void testConstructionNullRelay() {
		new SaslContext(null);
	}

	@Test
	@SuppressWarnings("ResultOfObjectAllocationIgnored")
	public void testConstruction() {
		Relay relay = mock(Relay.class);
		new SaslContext(relay);
	}

	@Test(expected = IllegalStateException.class)
	public void testNoInitiateConfirm() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.confirm("+", "user", "pass", "role");
	}

	@Test(expected = IllegalStateException.class)
	public void testNoInitiateLoggedIn() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.loggedIn();
	}

	@Test(expected = IllegalStateException.class)
	public void testNoInitiateSuccess() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.success();
	}

	@Test
	public void testInit() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		verify(relay).send("AUTHENTICATE PLAIN");
	}

	@Test(expected = IllegalStateException.class)
	public void testNoConfirmLoggedIn() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.loggedIn();
	}

	@Test(expected = IllegalStateException.class)
	public void testNoConfirmSuccess() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.success();
	}

	@Test
	public void testConfirm() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jilles", "jilles", "sesame");
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzAGppbGxlcwBzZXNhbWU=");
	}

	@Test
	public void testConfirmWithMessageCutoff() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjillesjilles", "jillesjillesjillesjillesjillesjillesjillesjillesjillesjilles", "sesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesamesesame");
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzamlsbGVzAGppbGxlc2ppbGxlc2ppbGxlc2ppbGxlc2ppbGxlc2ppbGxlc2ppbGxlc2ppbGxlc2ppbGxlc2ppbGxlcwBzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWVzZXNhbWV");
	}

	@Test
	public void testConfirmLoggedIn() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jilles", "jilles", "sesame");
		context.loggedIn();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzAGppbGxlcwBzZXNhbWU=");
	}

	@Test
	public void testConfirmSuccess() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jilles", "jilles", "sesame");
		context.success();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzAGppbGxlcwBzZXNhbWU=");
	}

	@Test
	public void testFullyAuthenticated() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jilles", "jilles", "sesame");
		context.loggedIn();
		context.success();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzAGppbGxlcwBzZXNhbWU=");
	}

	@Test(expected = IllegalStateException.class)
	public void testFailBeforeInitiation() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.fail();
	}

	@Test(expected = IllegalStateException.class)
	public void testAbortBeforeInitiation() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.abort();
	}

	@Test
	public void testAbortAfterInitiation() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.abort();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE *");
	}

	@Test
	public void testAbortAfterConfirmation() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jilles", "jilles", "sesame");
		context.abort();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzAGppbGxlcwBzZXNhbWU=");
		verify(relay).send("AUTHENTICATE *");
	}

	@Test
	public void testFailAfterSuccessfulAuthentication() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jilles", "jilles", "sesame");
		context.loggedIn();
		context.success();
		context.fail();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzAGppbGxlcwBzZXNhbWU=");
	}

	@Test
	public void testAbortOnFailedAuthenticationMechanism() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.fail();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE *");
	}

	@Test
	public void testAbortOnFailedAuthentication() {
		Relay relay = mock(Relay.class);
		SaslContext context = new SaslContext(relay);
		context.init();
		context.confirm("+", "jilles", "jilles", "sesame");
		context.fail();
		verify(relay).send("AUTHENTICATE PLAIN");
		verify(relay).send("AUTHENTICATE amlsbGVzAGppbGxlcwBzZXNhbWU=");
		verify(relay).send("AUTHENTICATE *");
	}
}
