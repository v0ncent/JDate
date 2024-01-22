package JDate.JDateFramework.TimelinePlayer;

import JDate.Exceptions.NoScenesException;
import JDate.JDateFramework.Scene;
import JDate.PaintableElements.PaintableElement;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public abstract class TimelinePlayer {
    public TimelinePlayer() {
        // add first element to intro
        this.addScene(this.getIntro());
    }

    /** If window should be rendering elements */
    @Getter
    @Setter
    private boolean shouldRender = true;

    /** Intro scene is the first scene that gets played within the JDate Framework*/
    @Getter
    private Scene intro = null;

    /** List of scenes to play in order*/
    @Getter
    private final ArrayList<Scene> script = new ArrayList<>();

    /**
     * Sets very first scene played by TimelinePlayer.
     * @param scene Scene to be the intro.
     */
    public void setIntro(Scene scene) {
        this.intro = scene;
        this.getScript().set(0, intro); // set scripts first element (always intro scene) to new intro.
    }

    /**
     * Adds a scene to the script list
     * @implNote The order of scenes in which they are added determines when they play
     * @param scene Scene OBJ to add to script list
     * @see Scene
     */
    public void addScene(@NotNull Scene scene) {
        script.add(scene);
    }

    /**
     * Logs all elements in a scene
     * @param elements List of paintable elements in a scene
     * @see PaintableElement
     * @see Scene
     */
    protected void logPaintableElements(@NotNull ArrayList<PaintableElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            final PaintableElement element = elements.get(i);
            element.getLogger().debug("Loaded: {}", element.getElementName());
        }
    }

    protected abstract void runPlayer(ArrayList<Scene> script) throws NoScenesException;
}
