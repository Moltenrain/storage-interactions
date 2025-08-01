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
    public static final String CONFIG_GROUP = "menuentryswapper";

    private final String SHIFT_BANK_WITHDRAW_KEY = "bankWithdrawShiftClick";
    private final String SHIFT_BANK_DEPOSIT_KEY = "bankDepositShiftClick";
    private final String SHIFT_SHOP_BUY_KEY = "shopBuy";
    private final String SHIFT_SHOP_SELL_KEY = "shopSell";

    @Inject
    private ConfigManager _configManager;

    @Override
    public String getBankShiftWithdrawAmount()
    {
        return getValue(SHIFT_BANK_WITHDRAW_KEY);
    }

    @Override
    public String getBankShiftDepositAmount() {
        return getValue(SHIFT_BANK_DEPOSIT_KEY);
    }

    @Override
    public String getShopBuyAmount()
    {
        return getValue(SHIFT_SHOP_BUY_KEY);
    }

    @Override
    public String getShopSellAmount()
    {
        return getValue(SHIFT_SHOP_SELL_KEY);
    }

    private String getValue(String key){
        return _configManager.getConfiguration(CONFIG_GROUP, key);
    }
}
