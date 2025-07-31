package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.KeyCode;
import net.runelite.api.ScriptID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarbitID;

import java.util.Random;

public class BankHandler extends WidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.BANKMAIN;
    }

    @Override
    public int[] getScriptIDsThatForceUpdate() {
        return new int[] {ScriptID.BANKMAIN_FINISHBUILDING};
    }

    @Override
    public String getTooltipText(Client client, IMenuSwapperConfigLoader menuSwapperConfigLoader, String hoverMenuItemText, boolean shiftHeld) {
        if (hoverMenuItemText != null  && !hoverMenuItemText.isEmpty()) {
            return formatHoverMenuItemText(hoverMenuItemText);
        }

        if (shiftHeld){
            return menuSwapperConfigLoader.getBankShiftWithdrawAmount();
        }

        return getQuantitySelected(client);
    }

    private String formatHoverMenuItemText(String text){
        String quantity = text.replaceFirst("Withdraw-|Deposit-", "");

        return quantity + " yes";
    }

    private String getQuantitySelected(Client client){
        int quantityType = client.getVarbitValue(VarbitID.BANK_QUANTITY_TYPE);

        switch (quantityType){
            case 0:
                return "1";
            case 1:
                return "5";
            case 2:
                return "10";
            case 3:
                return getBankXValue(client);
            case 4:
                return "All";
            default:
                return null;
        }
    }

    private String getBankXValue(Client client){
        int requestQuantity = client.getVarbitValue(VarbitID.BANK_REQUESTEDQUANTITY);
        return String.valueOf(requestQuantity);
    }
}
