package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;

import java.awt.*;

public class ShopWidgetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.SHOPMAIN;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.Shopside.ITEMS;
    }

    @Override
    public Rectangle getDepositInterfaceOffset() {
        return new Rectangle( -35, -35, 40, 60);
    }

    @Override
    public String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return menuSwapperConfigLoader.getShopSellAmount();
    }

    @Override
    public String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return menuSwapperConfigLoader.getShopBuyAmount();
    }

    @Override
    public int[] getScriptIDsThatForceUpdate() {
        return new int[0];
    }

    @Override
    public String GetSelectedQuantity(Client client) {
        return null;
    }

    @Override
    public String GetSelectedXValue(Client client) {
        return null;
    }

    @Override
    public boolean ForceReturnNoTooltip(Client client) {
        return false;
    }

    @Override
    public boolean IsNotedModeActive(Client client) {
        return false;
    }

    @Override
    protected String getFormatMenuRegex(){
        return "Buy |Sell |BUY_|SELL_";
    }
}
