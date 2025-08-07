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
    public String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader, String selectedQuantity) {
        return menuSwapperConfigLoader.getShopSellAmount();
    }

    @Override
    public String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader, String selectedQuantity) {
        return menuSwapperConfigLoader.getShopBuyAmount();
    }

    @Override
    public int[] getScriptIDsThatForceUpdate() {
        return new int[0];
    }

    @Override
    public String getSelectedQuantity(Client client) {
        return null;
    }

    @Override
    public String getSelectedXValue(Client client) {
        return null;
    }

    @Override
    public boolean forceReturnNoTooltip(Client client) {
        return false;
    }

    @Override
    public boolean isNotedModeActive(Client client) {
        return false;
    }

    @Override
    public boolean showPlaceholderDisableOverlay(Client client) {
        return false;
    }

    @Override
    protected String getFormatMenuRegex(){
        return "Buy |Sell |BUY_|SELL_";
    }
}
