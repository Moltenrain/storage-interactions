package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarbitID;

import java.awt.*;

public class TackleBoxWidgetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.TACKLE_BOX_MAIN;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.TackleBoxSide.ITEMS;
    }

    @Override
    public Rectangle getDepositInterfaceOffset() {
        return new Rectangle( -35, -35, 40, 60);
    }

    @Override
    public String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return "";
    }

    @Override
    public String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return "";
    }

    @Override
    public int[] getScriptIDsThatForceUpdate() {
        return new int[0];
    }

    @Override
    public String GetSelectedQuantity(Client client) {
        int bankQuantityType = client.getVarbitValue(VarbitID.II_ELNOCK_STORAGE_SELECTEDQUANTITY);

        return formatBankQuantityType(client, bankQuantityType);
    }

    @Override
    public String GetSelectedXValue(Client client) {
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
    public boolean ForceReturnNoTooltip(Client client) {
        return false;
    }
}
