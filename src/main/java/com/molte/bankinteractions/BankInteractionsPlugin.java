package com.molte.bankinteractions;

import com.google.inject.Provides;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.api.widgets.ComponentID;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@Slf4j
@PluginDescriptor(
	name = "Bank Interactions"
)
public class BankInteractionsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private BankInteractionsConfig config;

	@Inject
	private BankInteractionsOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Bank interactions started");
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Bank interactions stopped");
		overlayManager.remove(overlay);
		overlay.setRenderText(null);
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widgetLoaded) {
		if (widgetLoaded.getGroupId() == InterfaceID.BANK) {
			LoadOverlay();
		}
	}

	@Subscribe
	public void onGameTick(GameTick t)
	{
		Widget bankWidget = client.getWidget(ComponentID.BANK_CONTAINER);
		boolean bankOpen = bankWidget != null && !bankWidget.isHidden();

		if (!bankOpen){
			UnloadOverlay();
			return;
		}

		Widget bankBar = client.getWidget(ComponentID.BANK_ITEM_COUNT_BAR);
		Widget[] wid = bankBar.getChildren();

	}

	private void LoadOverlay(){
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Bank Opened", null);
		overlay.setRenderText("1");
	}

	private void UnloadOverlay(){
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Bank Closed", null);
		overlay.setRenderText(null);
	}

	@Provides
	private BankInteractionsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(BankInteractionsConfig.class);
	}
}
