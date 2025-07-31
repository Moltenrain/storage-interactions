package com.molte.storageinteractions.widget;

import com.molte.storageinteractions.IMenuSwapperConfigLoader;
import net.runelite.api.ScriptID;
import net.runelite.api.gameval.InterfaceID;

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
}
