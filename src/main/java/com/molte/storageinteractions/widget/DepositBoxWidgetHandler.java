package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.ScriptID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarPlayerID;
import net.runelite.api.gameval.VarbitID;

import java.awt.*;

public class DepositBoxWidgetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.BANK_DEPOSITBOX;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.InventoryNoops.ITEMS;
    }

    @Override
    public Rectangle getDepositInterfaceOffset() {
        return null;
    }

    @Override
    public String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return menuSwapperConfigLoader.getBankShiftWithdrawAmount();
    }

    @Override
    public String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return menuSwapperConfigLoader.getBankShiftWithdrawAmount();
    }

    @Override
    public int[] getScriptIDsThatForceUpdate() {
        return new int[] {ScriptID.BANKMAIN_BUILD};
    }

    @Override
    public String GetSelectedQuantity(Client client) {
        int bankQuantityType = client.getVarbitValue(VarbitID.DEPOSITBOX_MODE);

        return formatBankQuantityType(client, bankQuantityType);
    }

    @Override
    public String GetSelectedXValue(Client client) {
        return String.valueOf(client.getVarpValue(VarPlayerID.DEPOSITBOX_REQUESTEDQUANTITY));
    }

    @Override
    public boolean ForceReturnNoTooltip(Client client) {
        return false;
    }

    private String formatBankQuantityType(Client client, int bankQuantityType){
        switch (bankQuantityType){
            case 0:
                return "1";
            case 1:
                return "5";
            case 4:
                return "10";
            case 3:
                return GetSelectedXValue(client);
            case 2:
                return "All";
            default:
                return null;
        }
    }
}
