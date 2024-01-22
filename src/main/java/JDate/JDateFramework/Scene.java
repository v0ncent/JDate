package JDate.JDateFramework;

import JDate.PaintableElements.PaintableElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Scene {
    public final ArrayList<PaintableElement> paintableElements = new ArrayList<>();

    @Getter
    @Setter
    public String sceneName;

    public Scene(String sceneName) {
        this.sceneName = sceneName;
    }

    public void playScene() {

    }

    protected abstract ArrayList<PaintableElement> getPaintableElements();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Scene)) return false;
        Scene scene = (Scene) o;
        return getPaintableElements().equals(scene.getPaintableElements()) && getSceneName().equals(scene.getSceneName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPaintableElements(), getSceneName());
    }

    @Override
    public String toString() {
        return "Scene{" +
                "paintableElements=" + paintableElements +
                ", sceneName='" + sceneName + '\'' +
                '}';
    }
}
