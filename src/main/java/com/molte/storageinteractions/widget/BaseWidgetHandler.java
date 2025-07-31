package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;

import java.awt.*;

public abstract class BaseWidgetHandler {

    public abstract int getInterfaceID();
    public abstract int getDepositInterfaceID();
    public abstract Rectangle getDepositInterfaceOffset();
    public abstract String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader);
    public abstract String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader);
    public abstract int[] getScriptIDsThatForceUpdate();
    public abstract String GetSelectedQuantity(Client client);
    public abstract String GetSelectedXValue(Client client);
    public abstract boolean ForceReturnNoTooltip(Client client);

    public String getTooltipText(Client client, IMenuSwapperConfigLoader menuSwapperConfigLoader, String hoverMenuItemText, boolean shiftHeld, boolean mouseOverDepositInterface) {
        if (ForceReturnNoTooltip(client)){
            return null;
        }

        if (hoverMenuItemText != null  && !hoverMenuItemText.isEmpty()) {
            String hoveredText = formatMenuText(hoverMenuItemText, client);
            if (hoveredText != null){
                return hoveredText;
            }
        }

        if (shiftHeld){
            String menuSwappedValue = mouseOverDepositInterface ?
                    getShiftDepositAmount(menuSwapperConfigLoader) :
                    getShiftWithdrawAmount(menuSwapperConfigLoader);
            System.out.println(menuSwappedValue + " | " + mouseOverDepositInterface);
            if (menuSwappedValue != null && !menuSwappedValue.isEmpty()){
                menuSwappedValue = formatMenuText(menuSwappedValue, client);
            }

            if (menuSwappedValue != null && !menuSwappedValue.isEmpty()){
                return menuSwappedValue;
            }
        }

        return GetSelectedQuantity(client);
    }

    protected String getFormatMenuRegex(){
        return "Withdraw-|Deposit-|WITHDRAW_|DEPOSIT_";
    }

    private String formatMenuText(String text, Client client)
    {
        String sanitisedString = sanitiseMenuString(text);
        String formattedString = sanitisedString.replaceAll(getFormatMenuRegex(), "");

        // If nothing got replaced it safe to assume it's a bad string
        if (sanitisedString.equals(formattedString)){
            return null;
        }

        switch (formattedString.toLowerCase())
        {
            case "x":
                return GetSelectedXValue(client);
            case "all":
                return "All";
            default:
                return formattedString;
        }
    }

    private String sanitiseMenuString(String menuString){
        return menuString.replaceAll("<col=[^>]+>", "");
    }
}
