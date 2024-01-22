package JDate.PaintableElements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
