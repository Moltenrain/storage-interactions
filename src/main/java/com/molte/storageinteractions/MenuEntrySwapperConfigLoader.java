/*
 * NOTICE:
 * Credit to Adam <Adam@sigterm.info> for his creation of the MenuEntrySwapper plugin.
 * StorageInteractions doesn't use his code - just his saved config in RuneLite Configuration manager.
 *
 */

package com.molte.storageinteractions;

import net.runelite.client.config.ConfigManager;

import javax.inject.Inject;

public class MenuEntrySwapperConfigLoader implements IMenuSwapperConfigLoader
{
    private final String CONFIG_GROUP = "menuentryswapper";
    private final String SHIFT_BANK_WITHDRAW_KEY = "bankWithdrawShiftClick";
    private final String SHIFT_BANK_DEPOSIT_KEY = "bankDepositShiftClick";

    @Inject
    private ConfigManager _configManager;

    @Override
    public String getBankShiftWithdrawAmount() {
        return getValue(SHIFT_BANK_WITHDRAW_KEY);
    }

    private String getValue(String key){
        return _configManager.getConfiguration(CONFIG_GROUP, key);
    }
}
