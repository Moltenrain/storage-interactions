package com.molte.storageinteractions.settings;

import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup(StorageInteractionsConfig.CONFIG_GROUP)
public interface StorageInteractionsConfig extends Config
{
	public static final String CONFIG_GROUP = "storageinteractions";

	@ConfigSection(
			name = "Overlay",
			description = "All options that configure the mouse overlay",
			position = 0,
			closedByDefault = false
	)
	String overlaySection = "overlay";

	@ConfigItem(
			keyName = "overlaySize",
			name = "Size",
			description = "Size of the font/bank note in overlay",
			position = 0,
			section = overlaySection
	)
	default OverlaySize overlaySize() { return OverlaySize.MEDIUM; }

	@ConfigItem(
			keyName = "fontColour",
			name = "Colour",
			description = "Colour of the font in the overlay",
			position = 1,
			section = overlaySection
	)
	default Color fontColor() { return Color.ORANGE; }

	@ConfigItem(
			keyName = "fontOutlineColour",
			name = "Outline Colour",
			description = "Outline colour of the font in the overlay",
			position = 2,
			section = overlaySection
	)
	default Color fontOutLineColor() { return Color.BLACK; }

	@Range(
			min = -50000,
			max = 50000
	)
	@ConfigItem(
			keyName = "overlayOffsetX",
			name = "X",
			description = "X offset position of overlay",
			position = 3,
			section = overlaySection
	)
	default int overlayOffsetX() { return -0; }

	@Range(
			min = -50000,
			max = 50000
	)
	@ConfigItem(
			keyName = "overlayOffsetY",
			name = "Y",
			description = "Y offset position of overlay",
			position = 4,
			section = overlaySection
	)
	default int overlayOffsetY() { return -0; }
}
