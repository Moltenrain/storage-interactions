package com.molte.storageinteractions;

import com.google.inject.Provides;
import com.molte.storageinteractions.widget.BankHandler;
import com.molte.storageinteractions.widget.WidgetHandler;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.KeyCode;
import net.runelite.api.events.*;
import net.runelite.client.callback.ClientThread;
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
	private ClientThread _clientThread;

	@Inject
	private StorageInteractionsConfig _config;

	@Inject
	private StorageInteractionsOverlay _overlay;

	@Inject
	private OverlayManager _overlayManager;

	@Inject
	private MenuEntrySwapperConfigLoader _menuSwapperConfig;

	private final ArrayList<WidgetHandler> _widgetHandlers = new ArrayList<>(Arrays.asList(
		new BankHandler()
	));

	private WidgetHandler _activeWidgetHandler;
	private boolean _shiftHeld = false;

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
		_shiftHeld = false;
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

	@Subscribe
	public void onMenuOptionClicked(MenuOptionClicked event)
	{
		// We run this later to ensure menu actions have propagated through code
		_clientThread.invokeLater(this::updateOverlay);
	}

	@Subscribe
	public void onScriptPostFired(ScriptPostFired event)
	{
		if (_activeWidgetHandler == null){
			return;
		}

		int eventScriptID = event.getScriptId();

		if (Arrays.stream(_activeWidgetHandler.getScriptIDsThatForceUpdate()).anyMatch((scriptID) -> eventScriptID == scriptID)){
			// We run this later to ensure menu actions have propagated through code
			_clientThread.invokeLater(this::updateOverlay);
		}
	}

	@Subscribe
	public void onClientTick(ClientTick event) {
		boolean shiftHeldThisTick = _client.isKeyPressed(KeyCode.KC_SHIFT);
		if (shiftHeldThisTick != _shiftHeld){
			_shiftHeld = shiftHeldThisTick;
			_clientThread.invokeLater(this::updateOverlay);
		}
	}

	private void updateOverlay(){
		if (_activeWidgetHandler == null){
			_overlay.setRenderText(null);
			return;
		}

		_overlay.setRenderText(_activeWidgetHandler.getTooltipText(_client, _menuSwapperConfig, null, _shiftHeld));
	}

	@Provides
	private StorageInteractionsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(StorageInteractionsConfig.class);
	}
}
