package JDate.JDateFramework.TimelinePlayer;

import JDate.JDateFramework.TimelineElements.TimelineElement;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * Singleton Timeline Object.
 * @implNote Timeline's script attribute will always be populated by at least 1 scene. The intro.
 */
public final class Timeline {
    /**
     * List of scenes to play in order
     */
    @Getter
    private final LinkedList<TimelineElement> script = new LinkedList<>();

    /**
     * Singleton instance of timeline so there is only one timeline. THE TIMELINE!
     */
    private static Timeline timeline = null;

    private Timeline() {
        // add first element to intro
        this.addToScript(this.getIntro());
    }

    /**
     * Intro scene is the first scene that gets played within the JDate Framework
     */
    @Getter
    private TimelineElement intro = null;

    public static synchronized Timeline getInstance() {
        if (timeline == null) {
            timeline = new Timeline();
        }

        return timeline;
    }

    /**
     * Sets very first scene played by TimelinePlayer.
     *
     * @param timelineElement Scene to be the intro.
     */
    public void setIntro(TimelineElement timelineElement) {
        this.intro = timelineElement;
        this.getScript().set(0, intro); // set scripts first element (always intro scene) to new intro.
    }

    /**
     * Adds a scene to the script list
     *
     * @param timelineElement Scene OBJ to add to script list
     * @implNote The order of scenes in which they are added determines when they play
     * @see TimelineElement
     */
    public void addToScript(@NotNull TimelineElement timelineElement) {
        script.add(timelineElement);
    }
}
