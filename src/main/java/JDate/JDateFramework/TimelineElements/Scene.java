package JDate.JDateFramework.TimelineElements;

import JDate.PaintableElements.PaintableElement;

import java.util.ArrayList;

public abstract class Scene extends TimelineElement {

    public Scene(String sceneName) {
        super(sceneName);
    }

    @Override
    public ArrayList<PaintableElement> getPaintableElements() {
        return null;
    }
}
