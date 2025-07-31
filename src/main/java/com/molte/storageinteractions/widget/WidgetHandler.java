package com.molte.storageinteractions.widget;

import net.runelite.api.Client;

public abstract class WidgetHandler {

    public abstract int getInterfaceID();

    public abstract String getTooltipText(Client client);
}
