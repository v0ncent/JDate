package JDate.JDateFramework.TimelineElements;

import JDate.PaintableElements.PaintableElement;

import java.util.ArrayList;

public abstract class Cutscene extends TimelineElement {

    public Cutscene(String sceneName) {
        super(sceneName);
    }

    @Override
    public ArrayList<PaintableElement> getPaintableElements() {
        return null;
    }
}
