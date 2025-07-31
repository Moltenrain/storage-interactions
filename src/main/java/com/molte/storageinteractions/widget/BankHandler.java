package com.molte.storageinteractions.widget;

import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;

import java.util.Random;

public class BankHandler extends WidgetHandler {

    private Random ran = new Random();

    @Override
    public int getInterfaceID() {
        return InterfaceID.BANKMAIN;
    }

    @Override
    public String getTooltipText(Client client) {
        return "" + ran.nextDouble();
    }
}
