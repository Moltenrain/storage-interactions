package com.molte.bankinteractions;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class BankInteractionsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(BankInteractionsPlugin.class);
		RuneLite.main(args);
	}
}