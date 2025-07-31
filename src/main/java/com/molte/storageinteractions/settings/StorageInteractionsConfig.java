package com.molte.storageinteractions.settings;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup(StorageInteractionsConfig.CONFIG_GROUP)
public interface StorageInteractionsConfig extends Config
{
	public static final String CONFIG_GROUP = "storageinteractions";

	@ConfigItem(
			keyName = "fontSize",
			name = "Font Size",
			description = "Size of the font in the overlay"
	)
	default FontSize fontSize() { return FontSize.MEDIUM; }

	@ConfigItem(
			keyName = "fontColour",
			name = "Colour",
			description = "Colour of the font in the overlay"
	)
	default Colour fontColor() { return Colour.ORANGE; }

	@ConfigItem(
			keyName = "fontOutlineColour",
			name = "Outline Colour",
			description = "Outline colour of the font in the overlay"
	)
	default Colour fontOutLineColor() { return Colour.BLACK; }
}
