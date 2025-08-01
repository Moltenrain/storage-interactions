package com.molte.storageinteractions;

import com.molte.storageinteractions.settings.StorageInteractionsConfig;
import net.runelite.api.Client;
import net.runelite.api.Point;
import net.runelite.client.ui.overlay.*;

import javax.inject.Inject;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;


public class StorageInteractionsOverlay extends Overlay
{
    private static Font FONT = new Font("Arial", Font.BOLD, 14);

    private final Client _client;
    private String _renderText = null;
    private BufferedImage _bankNoteImage;
    private BufferedImage _baseBankNoteImage;
    private boolean _showBankNote = false;

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

        if (_showBankNote){
            graphics2D.drawImage(
                    _bankNoteImage,
                    mousePosition.getX() + _config.overlaySize().getBankNoteXOffset(),
                    mousePosition.getY() + _config.overlaySize().getBankNoteYOffset(),
                    null);
        }

        if (_renderText == null){
            return null;
        }

        AffineTransform transform = graphics2D.getTransform();
        transform.translate(
                mousePosition.getX() + _config.overlaySize().getFontXOffset(),
                mousePosition.getY() + _config.overlaySize().getFontYOffset());
        graphics2D.transform(transform);
        graphics2D.setColor(_config.fontOutLineColor().getColour());
        FontRenderContext frc = graphics2D.getFontRenderContext();
        TextLayout tl = new TextLayout(_renderText, FONT, frc);
        Shape shape = tl.getOutline(null);
        graphics2D.setStroke(new BasicStroke(_config.overlaySize().getFontOutlineThickness()));
        graphics2D.draw(shape);
        graphics2D.setColor(_config.fontColor().getColour());
        graphics2D.fill(shape);

        return null;
    }

    public void setRenderText(String text){
        _renderText = text;
    }

    public void setBankNoteImage(BufferedImage bankNoteImage)
    {
        if (bankNoteImage != null){
            _baseBankNoteImage = bankNoteImage;
        }

        double scaleX = (double) _config.overlaySize().getBankNoteWidth() / _baseBankNoteImage.getWidth();
        double scaleY = (double) _config.overlaySize().getBankNoteHeight() / _baseBankNoteImage.getHeight();

        AffineTransform tx = AffineTransform.getScaleInstance(scaleX, scaleY);
        BufferedImageOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);

        _bankNoteImage = op.filter(_baseBankNoteImage, null);
    }

    public void setShowBankNoteImage(boolean state)
    { _showBankNote = state; }

    public void updateConfig(){
        FONT = new Font("Arial", Font.BOLD, _config.overlaySize().getFontSize());
        setBankNoteImage(null);
    }
}
