package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.api.gameval.VarbitID;

import java.awt.*;

public class GnomeStorageWidgetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.FARMING_TOOLS;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.FarmingToolsSide.UNIVERSE;
    }

    @Override
    public Rectangle getDepositInterfaceOffset() {
        return null;
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
        int bankQuantityType = client.getVarbitValue(VarbitID.FARMING_TOOLS_SELECTEDQUANTITY);

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

    @Override
    protected String getFormatMenuRegex(){
        return "Remove-|Store-";
    }
}
