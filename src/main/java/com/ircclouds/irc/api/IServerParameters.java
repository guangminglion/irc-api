package com.ircclouds.irc.api;

import java.util.*;

import com.ircclouds.irc.api.domain.*;

/**
 * This interface stores the IRC connection parameters needed on connect.
 * 
 * @author miguel@lebane.se
 *
 */
public interface IServerParameters
{
	/**
	 * Gets the desired to use nickname
	 * @return the desired to use nickname
	 */
	String getNickname();

	/**
	 * Gets desired to use alternative nicknames
	 * @return desired to use alternative nicknames
	 */
	List<String> getAlternativeNicknames();

	/**
	 * Gets the desired to use ident
	 * @return the desired to use ident
	 */
	String getIdent();

	/**
	 * Gets the desired to use real name
	 * @return the desired to use real name
	 */
	String getRealname();

	/**
	 * Gets the desired to use {@link IRCServer}
	 * @return the desired to use {@link IRCServer}
	 */
	IRCServer getServer();
}
