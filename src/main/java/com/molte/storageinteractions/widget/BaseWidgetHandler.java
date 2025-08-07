package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;

import java.awt.*;

public abstract class BaseWidgetHandler {

    public abstract int getInterfaceID();
    public abstract int getDepositInterfaceID();
    public abstract Rectangle getDepositInterfaceOffset();
    public abstract String getShiftDepositAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader, String selectedQuantity);
    public abstract String getShiftWithdrawAmount(IMenuSwapperConfigLoader menuSwapperConfigLoader, String selectedQuantity);
    public abstract int[] getScriptIDsThatForceUpdate();
    public abstract String getSelectedQuantity(Client client);
    public abstract String getSelectedXValue(Client client);
    public abstract boolean forceReturnNoTooltip(Client client);
    public abstract boolean isNotedModeActive(Client client);
    public abstract boolean showPlaceholderDisableOverlay(Client client);

    public String getTooltipText(Client client, IMenuSwapperConfigLoader menuSwapperConfigLoader, String hoverMenuItemText, boolean shiftHeld, boolean mouseOverDepositInterface) {
        if (forceReturnNoTooltip(client)){
            return null;
        }

        String selectedQuantity = getSelectedQuantity(client);

        if (hoverMenuItemText != null  && !hoverMenuItemText.isEmpty()) {
            String hoveredText = formatMenuText(hoverMenuItemText, client);
            if (hoveredText != null){
                return hoveredText;
            }
        }

        if (shiftHeld){
            String menuSwappedValue = mouseOverDepositInterface ?
                    getShiftDepositAmount(menuSwapperConfigLoader, selectedQuantity) :
                    getShiftWithdrawAmount(menuSwapperConfigLoader, selectedQuantity);

            if (menuSwappedValue != null && !menuSwappedValue.isEmpty()){
                menuSwappedValue = formatMenuText(menuSwappedValue, client);
            }

            if (menuSwappedValue != null && !menuSwappedValue.isEmpty()){
                return menuSwappedValue;
            }
        }

        return selectedQuantity;
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
                return getSelectedXValue(client);
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
