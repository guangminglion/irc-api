package com.ircclouds.irc.api;

/**
 * A callback that returns T on success and {@link Exception} on failure
 * 
 * @author miguel@lebane.se
 */
public interface Callback<T> extends ICallback<T, Exception>
{
	void onSuccess(T aObject);

    void onFailure(Exception aExc);
}
