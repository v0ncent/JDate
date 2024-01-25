package JDate.JDateFramework.TimelineElements;

import JDate.PaintableElements.PaintableElement;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;

public abstract class TimelineElement {
    public final ArrayList<PaintableElement> paintableElements = new ArrayList<>();

    @Getter
    @Setter
    public String sceneName;

    public TimelineElement(String sceneName) {
        this.sceneName = sceneName;
    }

    public void playOutElement() {
        // placeholder code right now so my machine doesnt break with constant gc calls.
        try {
            Thread.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public boolean hasPaintableElements() {
        return this.getPaintableElements() != null;
    }

    @Nullable
    public abstract ArrayList<PaintableElement> getPaintableElements();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TimelineElement timelineElement)) return false;
        return Objects.equals(getPaintableElements(), timelineElement.getPaintableElements()) && getSceneName().equals(timelineElement.getSceneName());
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
