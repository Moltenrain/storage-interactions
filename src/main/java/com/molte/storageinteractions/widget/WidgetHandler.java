package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;

public abstract class WidgetHandler
{
    public abstract int getInterfaceID();
    public abstract int[] getScriptIDsThatForceUpdate();
    public abstract String getTooltipText(Client client, IMenuSwapperConfigLoader menuSwapperConfigLoader, String hoverMenuItemText, boolean shiftHeld);
}
