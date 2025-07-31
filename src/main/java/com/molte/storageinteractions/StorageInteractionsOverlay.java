package com.molte.storageinteractions;

import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.*;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import java.awt.*;


public class StorageInteractionsOverlay extends OverlayPanel
{
    private static final Point TOOLTIP_OFFSET = new Point(-40,  5);
    private static final Font FONT = new Font("Arial", Font.PLAIN, 9);

    private final Client _client;
    private String _renderText = null;


    @Inject
    StorageInteractionsOverlay(Client client){
        _client = client;

        setPriority(OverlayPriority.HIGHEST);
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ALWAYS_ON_TOP);
    }


    @Override
    public Dimension render(Graphics2D graphics2D) {
        final Point mousePosition = _client.getMouseCanvasPosition();

        if (_renderText == null){
            return super.render(graphics2D);
        }

        panelComponent.setPreferredLocation(new java.awt.Point(mousePosition.getX() + TOOLTIP_OFFSET.getX(), mousePosition.getY() + TOOLTIP_OFFSET.getY()));
        panelComponent.setPreferredSize(new Dimension(25, 15));


        panelComponent.getChildren().add(LineComponent.builder()
                .left(_renderText)
                .leftFont(FONT)
                .build());

        return super.render(graphics2D);
    }

    public void setRenderText(String text){
        _renderText = text;
    }
}
