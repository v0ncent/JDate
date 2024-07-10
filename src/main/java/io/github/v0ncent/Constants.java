package io.github.v0ncent;

import java.awt.*;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Constants {
    public static final class JFrameConstants {
        public static final String WINDOW_NAME = "JDate";
        public static final Dimension WINDOW_SIZE = new Dimension(800, 800);
        public static final int CLOSE_OPERATION = EXIT_ON_CLOSE;
    }

    public static final class ProjectGeneratorConstants {
        public static final String PROJECT_NAME_FIELD_PLACEHOLDER_TEXT = "Enter Project Location";
        public static final String PROJECT_NAME_LABEL_TEXT = "Project Name";
        public static final String PROJECT_LOCATION_LABEL_TEXT = "Project Location";
        public static final String PROJECT_LOCATION_BUTTON_TEXT = "Choose Project Location";
    }

    public static final class FileContent {
        public static final String GAMEJSON_CONTENT = "{\n" +
                "    \"starting_script\":\"\",\n" +
                "    \"name\":\"\"\n" +
                "}";
    }

}
