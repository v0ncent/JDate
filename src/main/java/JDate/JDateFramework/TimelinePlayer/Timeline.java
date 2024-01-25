package JDate.JDateFramework.TimelinePlayer;

import JDate.JDateFramework.TimelineElements.TimelineElement;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedList;

/**
 * Singleton Timeline Object.
 * @implNote Timeline's script attribute will always be populated by at least 1 scene. The intro.
 */
public class Timeline {
    /**
     * List of scenes to play in order
     */
    protected final LinkedList<TimelineElement> script = new LinkedList<>();

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

    protected LinkedList<TimelineElement> getScript() {
        return this.script;
    }
}
