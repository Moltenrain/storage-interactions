package com.molte.storageinteractions;

import com.molte.storageinteractions.settings.FontSize;
import com.molte.storageinteractions.settings.StorageInteractionsConfig;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.ui.overlay.*;
import net.runelite.client.ui.overlay.components.LineComponent;

import javax.inject.Inject;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.util.UUID;


public class StorageInteractionsOverlay extends Overlay
{
    private static final Point TOOLTIP_OFFSET = new Point(10,  20);
    private static Font FONT = new Font("Arial", Font.BOLD, 14);

    private final Client _client;
    private String _renderText = null;

    @Inject
    private StorageInteractionsConfig _config;

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
            return null;
        }

        AffineTransform transform = graphics2D.getTransform();
        transform.translate(mousePosition.getX() + TOOLTIP_OFFSET.getX() + _config.fontSize().getXOffset(), mousePosition.getY() + TOOLTIP_OFFSET.getY() + _config.fontSize().getYOffset());
        graphics2D.transform(transform);
        graphics2D.setColor(_config.fontOutLineColor().getColour());
        FontRenderContext frc = graphics2D.getFontRenderContext();
        TextLayout tl = new TextLayout(_renderText, FONT, frc);
        Shape shape = tl.getOutline(null);
        graphics2D.setStroke(new BasicStroke(_config.fontSize().getOutlineThickness()));
        graphics2D.draw(shape);
        graphics2D.setColor(_config.fontColor().getColour());
        graphics2D.fill(shape);

        return null;
    }

    public void setRenderText(String text){
        _renderText = text;
    }

    public void updateConfig(){
        FONT = new Font("Arial", Font.BOLD, _config.fontSize().getSize());
    }
}
