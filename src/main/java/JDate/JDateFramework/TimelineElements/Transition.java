package JDate.JDateFramework.TimelineElements;

import JDate.PaintableElements.PaintableElement;

import java.util.ArrayList;

public abstract class Transition extends TimelineElement {

    public Transition(String sceneName) {
        super(sceneName);
    }

    @Override
    public ArrayList<PaintableElement> getPaintableElements() {
        return null;
    }
}
