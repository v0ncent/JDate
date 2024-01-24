package JDate.PaintableElements;

import JDate.JDateFramework.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class DialogueBox implements PaintableElement {
    private final Logger LOGGER = LoggerFactory.getLogger(DialogueBox.class);

    @Override
    public Logger getLogger() {
        return LOGGER;
    }

    @Override
    public ELEMENT_TYPE getElementType() {
        return ELEMENT_TYPE.LABEL;
    }

    @Override
    public void addSprites() {
        sprites.put("someSprite", new ImageIcon(Constants.DEFAULT_ICON_PATH).getImage());
    }

}
