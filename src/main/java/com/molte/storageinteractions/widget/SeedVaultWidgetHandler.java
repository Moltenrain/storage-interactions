package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.Client;
import net.runelite.api.ScriptID;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.VarPlayerID;

import java.awt.*;

public class SeedVaultWidgetHandler extends BaseWidgetHandler {

    @Override
    public int getInterfaceID() {
        return InterfaceID.SEED_VAULT;
    }

    @Override
    public int getDepositInterfaceID() {
        return InterfaceID.SeedVaultDeposit.UNIVERSE;
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
        return new int[] { ScriptID.SEED_VAULT_BUILD };
    }

    @Override
    public String GetSelectedQuantity(Client client) {
        int selectedQuantity = client.getVarpValue(VarPlayerID.SEED_VAULT_SELECTED_QTY);

        return selectedQuantity == Integer.MAX_VALUE ? "All" : String.valueOf(selectedQuantity);
    }

    @Override
    public String GetSelectedXValue(Client client) {
        return String.valueOf(client.getVarpValue(VarPlayerID.SEED_VAULT_REQUESTED_QTY));
    }

    @Override
    public boolean ForceReturnNoTooltip(Client client) {
        return false;
    }

    @Override
    public boolean IsNotedModeActive(Client client) {
        return false;
    }

}
