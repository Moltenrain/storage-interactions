package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarbitID;

import java.awt.*;

public class HuntsmansKitWidetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.HUNTSMANS_KIT;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.HuntsmansKitSide.ITEMS;
    }

    @Override
    public Rectangle getDepositInterfaceOffset() {
        return new Rectangle( -35, -35, 40, 60);
    }

    @Override
    public String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader, String selectedQuantity) {
        return "";
    }

    @Override
    public String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader, String selectedQuantity) {
        return "";
    }

    @Override
    public int[] getScriptIDsThatForceUpdate() {
        return new int[0];
    }

    @Override
    public String getSelectedQuantity(Client client) {
        int bankQuantityType = client.getVarbitValue(VarbitID.II_ELNOCK_STORAGE_SELECTEDQUANTITY);

        return formatBankQuantityType(client, bankQuantityType);
    }

    @Override
    public String getSelectedXValue(Client client) {
        return "X";
    }

    private String formatBankQuantityType(Client client, int bankQuantityType){
        switch (bankQuantityType){
            case 0:
                return "1";
            case 1:
                return "5";
            case 3:
                return "X"; // Client doesn't save an X value for this interface
            case 2:
                return "All";
            default:
                return null;
        }
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
}
