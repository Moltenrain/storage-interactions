package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.gameval.VarbitID;

public abstract class BaseWidgetHandler {

    public abstract int getInterfaceID();
    public abstract int getDepositInterfaceID();
    public abstract String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader);
    public abstract String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader);
    public abstract int[] getScriptIDsThatForceUpdate();

    public String getTooltipText(Client client, IMenuSwapperConfigLoader menuSwapperConfigLoader, String hoverMenuItemText, boolean shiftHeld, boolean mouseOverDepositInterface) {
        if (hoverMenuItemText != null  && !hoverMenuItemText.isEmpty()) {
            String hoveredText = formatMenuText(hoverMenuItemText, client);
            if (hoveredText != null){
                return hoveredText + " Hovered";
            }
        }

        if (shiftHeld){
            return formatMenuText(mouseOverDepositInterface ?
                        getShiftDepositAmount(menuSwapperConfigLoader) :
                        getShiftWithdrawAmount(menuSwapperConfigLoader),
                    client);
        }

        return getQuantitySelected(client);
    }

    protected String getFormatMenuRegex(){
        return "Withdraw-|Deposit-|WITHDRAW_|DEPOSIT_";
    }

    private String formatMenuText(String text, Client client)
    {
        String formattedString = text.replaceAll(getFormatMenuRegex(), "");

        System.out.println(formattedString + " | " + text);

        // If nothing got replaced it safe to assume it's a bad string
        if (text.equals(formattedString)){
            return null;
        }

        switch (formattedString.toLowerCase())
        {
            case "x":
                return getBankXValue(client);
            case "all":
                return "All";
            default:
                return formattedString;
        }
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
