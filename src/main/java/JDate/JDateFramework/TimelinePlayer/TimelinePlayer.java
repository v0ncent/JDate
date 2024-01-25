package JDate.JDateFramework.TimelinePlayer;

import JDate.Exceptions.NoScenesException;
import JDate.JDateFramework.TimelineElements.TimelineElement;
import JDate.PaintableElements.PaintableElement;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.LinkedList;

public class TimelinePlayer extends Timeline {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimelinePlayer.class);

    @Getter
    private final Timeline timeline = new Timeline();

    /**
     * If window should be rendering elements
     */
    @Getter
    @Setter
    private boolean shouldRender = true;

    /**
     * Logs all elements in a scene
     *
     * @param elements List of paintable elements in a scene
     * @see PaintableElement
     * @see TimelineElement
     */
    protected void logPaintableElements(@NotNull ArrayList<PaintableElement> elements) {
        for (int i = 0; i < elements.size(); i++) {
            final PaintableElement element = elements.get(i);
            element.getLogger().debug("Loaded: {}", element.getElementName());
        }
    }

    /**
     * JDate timeline player method. Paints scenes in order in which they are passed into JFrame window
     *
     * @throws NoScenesException When no scenes have been added to script list
     * @implNote The timeline player plays scenes and transitions in the order in which they are added to the script list
     * @see TimelineElement
     */
    protected void runPlayer() throws NoScenesException {
        LinkedList<TimelineElement> script = timeline.getScript();

        if (script.size() == 0) {
            throw new NoScenesException();
        }

        // game loop to play each scene in order of passage, when all scenes played go back to the beginning.
        int i = 0;
        while (this.isShouldRender()) {
            if (i == script.size()) {
                LOGGER.info("TimelinePlayer reached the end of current timeline: Clearing heap of garbage.");
                System.gc(); // force garbage collection after a full timeline run through. Need this to clear out heap.
                i = 0;
            }

            final TimelineElement timelineElement = script.get(i);

            if (timelineElement.hasPaintableElements() && timelineElement.getPaintableElements() != null) {
                this.logPaintableElements(timelineElement.getPaintableElements()); // uncomment when scene.playscene takes longer so we dont spam this log
            }

            timelineElement.playOutElement();
            i++;
        }
    }

}
