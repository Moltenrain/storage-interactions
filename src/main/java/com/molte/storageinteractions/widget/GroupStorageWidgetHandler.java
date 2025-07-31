package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.ScriptID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarbitID;

public class GroupStorageWidgetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.SHARED_BANK;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.SHARED_BANK_SIDE;
    }

    @Override
    public String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return menuSwapperConfigLoader.getBankShiftDepositAmount();
    }

    @Override
    public String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader) {
        return menuSwapperConfigLoader.getBankShiftWithdrawAmount();
    }

    @Override
    public int[] getScriptIDsThatForceUpdate() {
        return new int[] {ScriptID.BANKMAIN_FINISHBUILDING, ScriptID.GROUP_IRONMAN_STORAGE_BUILD};
    }

    @Override
    public String GetSelectedQuantity(Client client) {
        int bankQuantityType = client.getVarbitValue(VarbitID.BANK_QUANTITY_TYPE);

        return formatBankQuantityType(client, bankQuantityType);
    }

    @Override
    public String GetSelectedXValue(Client client) {
        return String.valueOf(client.getVarbitValue(VarbitID.BANK_REQUESTEDQUANTITY));
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
            case 2:
                return "10";
            case 3:
                return GetSelectedXValue(client);
            case 4:
                return "All";
            default:
                return null;
        }
    }
}
