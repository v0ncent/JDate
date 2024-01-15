package JDate.JDateFramework;

import JDate.Exceptions.NoScenesException;
import JDate.PaintableElements.PaintableElement;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public final class JDateFramework {
    @Setter
    private static Scene intro = null;

    @Getter
    private static final ArrayList<Scene> script = new ArrayList<>();

    private static void logPaintableElements(PaintableElement element) {
        element.getLogger().info("Loaded: {}", element.getElementName());
    }

    public static void addScene(Scene scene) {
        script.add(scene);
    }

    public static void run() throws NoScenesException {
        addScene(intro);

        if (script.size() == 1 && script.get(0) == null) {
            throw new NoScenesException();
        }

        // play each scene in order passed
        for (Scene scene : script) {

            for (PaintableElement e : scene.paintableElements) {
                logPaintableElements(e);
            }

            scene.playScene();
        }

    }
}
