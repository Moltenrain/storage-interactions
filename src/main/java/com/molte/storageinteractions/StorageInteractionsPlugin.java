package com.molte.storageinteractions;

import com.google.inject.Provides;
import com.molte.storageinteractions.widget.BankHandler;
import com.molte.storageinteractions.widget.WidgetHandler;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.WidgetClosed;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
@PluginDescriptor(
	name = "Storage Interactions"
)
public class StorageInteractionsPlugin extends Plugin
{
	@Inject
	private Client _client;

	@Inject
	private StorageInteractionsConfig _config;

	@Inject
	private StorageInteractionsOverlay _overlay;

	@Inject
	private OverlayManager _overlayManager;

	private final ArrayList<WidgetHandler> _widgetHandlers = new ArrayList<>(Arrays.asList(
		new BankHandler()
	));

	private WidgetHandler _activeWidgetHandler;

	@Override
	protected void startUp() throws Exception
	{
		_overlayManager.add(_overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		_overlayManager.remove(_overlay);
		_overlay.setRenderText(null);
		_activeWidgetHandler = null;
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded widgetLoaded)
	{
		int interfaceID = widgetLoaded.getGroupId();

		for (WidgetHandler widgetHandler : _widgetHandlers){
			if (widgetHandler.getInterfaceID() == interfaceID){
				_activeWidgetHandler = widgetHandler;
				break;
			}
		}

		updateOverlay();
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed widgetClosed)
	{
		if (_activeWidgetHandler != null && _activeWidgetHandler.getInterfaceID() == widgetClosed.getGroupId()) {
			_activeWidgetHandler = null;
		}

		updateOverlay();
	}

	private void updateOverlay(){
		if (_activeWidgetHandler == null){
			_overlay.setRenderText(null);
			return;
		}

		_overlay.setRenderText(_activeWidgetHandler.getTooltipText(_client));
	}

	@Provides
	private StorageInteractionsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(StorageInteractionsConfig.class);
	}
}
