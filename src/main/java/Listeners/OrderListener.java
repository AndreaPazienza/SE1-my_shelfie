package Listeners;

import VIEW.GraphicObjects.TilesToOrder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderListener extends MouseAdapter {

    TilesToOrder dragLabel;

    @Override
    public void mousePressed(MouseEvent e) {
        dragLabel = (TilesToOrder) e.getSource();
        dragLabel.getLabel().setBorder(BorderFactory.createLineBorder(Color.RED));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(dragLabel != null){
            dragLabel.getLabel().setBorder(null);
            dragLabel = null;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(dragLabel != null){
            TransferHandler transferHandler = dragLabel.getLabel().getTransferHandler();
            transferHandler.exportAsDrag(dragLabel.getLabel(), e, TransferHandler.COPY);
        }
    }
}
