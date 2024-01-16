package JDate.JDateFramework;

import JDate.PaintableElements.PaintableElement;

import java.util.ArrayList;

public abstract class Scene {
    public ArrayList<PaintableElement> paintableElements = new ArrayList<>();

    public void playScene() {

    }

    protected abstract ArrayList<PaintableElement> getPaintableElements();
}
