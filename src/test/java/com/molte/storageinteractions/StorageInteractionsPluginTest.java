package com.molte.storageinteractions;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class StorageInteractionsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(StorageInteractionsPlugin.class);
		RuneLite.main(args);
	}
}