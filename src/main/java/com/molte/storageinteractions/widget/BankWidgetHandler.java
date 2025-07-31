package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.ScriptID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.api.widgets.Widget;

import java.awt.*;

public class BankWidgetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.BANKMAIN;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.Bankside.UNIVERSE;
    }

    @Override
    public Rectangle getDepositInterfaceOffset() {
        return null;
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
        return new int[] {ScriptID.BANKMAIN_FINISHBUILDING};
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
        Widget wornItems = client.getWidget(InterfaceID.Bankmain.WORNITEMS_CONTAINER);
        Widget menu = client.getWidget(InterfaceID.Bankmain.MENU_CONTAINER);

        // Disable interface if worn items or the menu is shown
        return (wornItems != null && !wornItems.isHidden()) || (menu != null && !menu.isHidden());
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
